package org.pushingpixels.substance.internal.svg;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.plaf.UIResource;

import org.pushingpixels.neon.icon.IsHiDpiAware;
import org.pushingpixels.neon.icon.ResizableIcon;
import org.pushingpixels.neon.icon.NeonIcon;
import org.pushingpixels.neon.icon.NeonIconUIResource;

/**
 * This class has been automatically generated using <a
 * href="https://github.com/kirill-grouchnikov/radiance">Photon SVG transcoder</a>.
 */
public class ic_content_paste_black_24px implements ResizableIcon, IsHiDpiAware {
    @SuppressWarnings("unused")
	private void innerPaint(Graphics2D g) {
        Shape shape = null;
        Paint paint = null;
        Stroke stroke = null;
         
        float origAlpha = 1.0f;
        Composite origComposite = g.getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = 
                (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
	    AffineTransform defaultTransform_ = g.getTransform();
// 
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
paint = new Color(0, 0, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(19.0, 2.0);
((GeneralPath)shape).lineTo(14.82, 2.0);
((GeneralPath)shape).curveTo(14.4, 0.84, 13.3, 0.0, 12.0, 0.0);
((GeneralPath)shape).curveTo(10.7, 0.0, 9.6, 0.84, 9.18, 2.0);
((GeneralPath)shape).lineTo(5.0, 2.0);
((GeneralPath)shape).curveTo(3.9, 2.0, 3.0, 2.9, 3.0, 4.0);
((GeneralPath)shape).lineTo(3.0, 20.0);
((GeneralPath)shape).curveTo(3.0, 21.1, 3.9, 22.0, 5.0, 22.0);
((GeneralPath)shape).lineTo(19.0, 22.0);
((GeneralPath)shape).curveTo(20.1, 22.0, 21.0, 21.1, 21.0, 20.0);
((GeneralPath)shape).lineTo(21.0, 4.0);
((GeneralPath)shape).curveTo(21.0, 2.9, 20.1, 2.0, 19.0, 2.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(12.0, 2.0);
((GeneralPath)shape).curveTo(12.55, 2.0, 13.0, 2.45, 13.0, 3.0);
((GeneralPath)shape).curveTo(13.0, 3.55, 12.55, 4.0, 12.0, 4.0);
((GeneralPath)shape).curveTo(11.45, 4.0, 11.0, 3.55, 11.0, 3.0);
((GeneralPath)shape).curveTo(11.0, 2.45, 11.45, 2.0, 12.0, 2.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(19.0, 20.0);
((GeneralPath)shape).lineTo(5.0, 20.0);
((GeneralPath)shape).lineTo(5.0, 4.0);
((GeneralPath)shape).lineTo(7.0, 4.0);
((GeneralPath)shape).lineTo(7.0, 7.0);
((GeneralPath)shape).lineTo(17.0, 7.0);
((GeneralPath)shape).lineTo(17.0, 4.0);
((GeneralPath)shape).lineTo(19.0, 4.0);
((GeneralPath)shape).lineTo(19.0, 20.0);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1
g.setTransform(defaultTransform__0_1);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static double getOrigX() {
        return 3.0;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static double getOrigY() {
        return 0.0;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static double getOrigWidth() {
		return 18.0;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static double getOrigHeight() {
		return 22.0;
	}

	/** The current width of this resizable icon. */
	private int width;

    /** The current height of this resizable icon. */
	private int height;

	/**
	 * Creates a new transcoded SVG image. It is recommended to use the 
	 * {@link #of(int, int)} method to obtain a pre-configured instance.
	 */
	public ic_content_paste_black_24px() {
        this.width = (int) getOrigWidth();
        this.height = (int) getOrigHeight();
	}

    @Override
	public int getIconHeight() {
		return height;
	}

    @Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public void setDimension(Dimension newDimension) {
		this.width = newDimension.width;
		this.height = newDimension.height;
	}

    @Override
    public boolean isHiDpiAware() {
        return true;
    }

    @Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(x, y);

        double coef1 = (double) this.width / getOrigWidth();
        double coef2 = (double) this.height / getOrigHeight();
        double coef = Math.min(coef1, coef2);
        g2d.clipRect(0, 0, this.width, this.height);
        g2d.scale(coef, coef);
        g2d.translate(-getOrigX(), -getOrigY());
        if (coef1 != coef2) {
            if (coef1 < coef2) {
               int extraDy = (int) ((getOrigWidth() - getOrigHeight()) / 2.0);
               g2d.translate(0, extraDy);
            } else {
               int extraDx = (int) ((getOrigHeight() - getOrigWidth()) / 2.0);
               g2d.translate(extraDx, 0);
            }
        }
        Graphics2D g2ForInner = (Graphics2D) g2d.create();
        innerPaint(g2ForInner);
        g2ForInner.dispose();
        g2d.dispose();
	}
    
    /**
     * Returns an instance of this icon with specified dimensions.
     */
    public static NeonIcon of(int width, int height) {
       ic_content_paste_black_24px base = new ic_content_paste_black_24px();
       base.width = width;
       base.height = height;
       return new NeonIcon(base);
    }

    /**
     * Returns a {@link UIResource} instance of this icon with specified dimensions.
     */
    public static NeonIconUIResource uiResourceOf(int width, int height) {
       ic_content_paste_black_24px base = new ic_content_paste_black_24px();
       base.width = width;
       base.height = height;
       return new NeonIconUIResource(base);
    }
}

