/*
 * Copyright (c) 2005-2019 Radiance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.neon.internal.font;

import org.pushingpixels.neon.font.*;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

/**
 * The default font policy for Gnome OS.
 * 
 * @author Kirill Grouchnikov
 */
public class DefaultGnomeFontPolicy implements FontPolicy {
	/**
	 * Font scale.
	 */
	private static double fontScale;
	private static double nativeScale;

	static {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice device = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice()
				.getDefaultConfiguration();
		AffineTransform at = gc.getNormalizingTransform();
		fontScale = at.getScaleY();

		try {
			// Attempt to get the native scale factor from the X11GraphicsDevice instance
			// Unfortunately, this has to be done via reflection as the class is not
			// exposed by the java.desktop module
			Method getNativeScale = device.getClass().getDeclaredMethod("getNativeScale");

			// note that the method currently returns an int, but we treat it as a double
			// because the underlying OS uses a double and so if Java changes to respect
			// this in future versions we will start to get the right value automagically
			
			// an unfortunate side effect of the value being returned as an int is that
			// the scaling won't always match the OS accurate. For example, under Ubuntu
			// the scaling can be set to a double with two decimal places. Most people
			// seem to set the value to 2 which isn't a problem, however, 1.5 is also
			// a common option. In that case the method rounds the number up to 2 meaning
			// that the fonts are shown slightly larger than necessary. While not perfect
			// this is still better than not adjusting for the native scaling at all.
			nativeScale = ((Number)getNativeScale.invoke(device)).doubleValue();
			if (nativeScale < 1) {
				nativeScale = 1;
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			// if we can't get the scale then just assume there isn't one
			nativeScale = 1;
		}
	}

	@Override
	public FontSet getFontSet() {
		Object defaultGtkFontName = Toolkit.getDefaultToolkit()
				.getDesktopProperty("gnome.Gtk/FontName");
		String family = "";
		int style = Font.PLAIN;
		int size = 10;
		if (defaultGtkFontName instanceof String) {
			String pangoName = (String) defaultGtkFontName;
			StringTokenizer tok = new StringTokenizer(pangoName);
			while (tok.hasMoreTokens()) {
				String word = tok.nextToken();
				boolean allDigits = true;
				for (int i = 0; i < word.length(); i++) {
					if (!Character.isDigit(word.charAt(i))) {
						allDigits = false;
						break;
					}
				}

				if (word.equalsIgnoreCase("italic")) {
					style |= Font.ITALIC;
				} else if (word.equalsIgnoreCase("bold")) {
					style |= Font.BOLD;
				} else if (allDigits) {
					try {
						size = Integer.parseInt(word);
					} catch (NumberFormatException nfe) {
						size = 10;
					}
				} else {
					if (family.length() > 0) {
						family += " ";
					}
					family += word;
				}
			}
		}

		// adjust the font size to take into account any native scaling being
		// performed by the underlying OS. This fix comes from a proposed
		// fix to PangoFonts on which this class was originally based.
		// Bug Report: https://bugs.openjdk.java.net/browse/JDK-8058742
		// Proposed Fix: http://cr.openjdk.java.net/~ssadetsky/8058742/webrev.01/
		double dsize = (size * getPointsToPixelsRatio()) / nativeScale;

		size = (int) (dsize + 0.5);
		if (size < 1) {
			size = 1;
		}

		if (family.length() == 0)
			family = "sans";
		// Font controlFont = new Font(family, style, size);

		Font controlFont = null;
		// make some black magic with sun-private classes
		// to better map the logical font name (such as sans)
		// to an actual font (such as DejaVu Sans).
		String fcFamilyLC = family.toLowerCase();
		try {
			Class<?> fontManagerClass = Class.forName("sun.font.FontManager");
			Method mapFcMethod = fontManagerClass.getMethod("mapFcName",
					String.class);
			Object mapFcMethodResult = mapFcMethod.invoke(null, fcFamilyLC);
			if (mapFcMethodResult != null) {
				Method getFontConfigFUIRMethod = fontManagerClass.getMethod(
						"getFontConfigFUIR", String.class, int.class, int.class);
				controlFont = (Font) getFontConfigFUIRMethod.invoke(null,
						fcFamilyLC, style, size);
			} else {
				Font font = new FontUIResource(family, style, size);
				Method getCompositeFontUIResourceMethod = fontManagerClass
						.getMethod("getCompositeFontUIResource", Font.class);
				controlFont = (Font) getCompositeFontUIResourceMethod.invoke(
						null, font);
			}
		} catch (Throwable t) {
			controlFont = new Font(family, style, size);
		}

		return FontSets.createDefaultFontSet(controlFont);
	}

	public static double getPointsToPixelsRatio() {
		// for details behind the computations, look in
		// com.sun.java.swing.plaf.gtk.PangoFonts
		int dpi = 96;
		Object value = Toolkit.getDefaultToolkit().getDesktopProperty(
				"gnome.Xft/DPI");
		if (value instanceof Integer) {
			dpi = ((Integer) value).intValue() / 1024;
			if (dpi == -1) {
				dpi = 96;
			}
			if (dpi < 50) {
				dpi = 50;
			}
			return dpi / 72.0;
		} else {
			return fontScale;
		}
	}
}
