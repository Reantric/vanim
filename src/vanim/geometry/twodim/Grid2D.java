package vanim.geometry.twodim;


import processing.core.PVector;
import vanim.core.Graphics2D;
import vanim.root.builder.GeometricSpaceBuilder;
import vanim.storage.color.Color;
import vanim.util.map.MapEase;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.HSB;
import static vanim.planar.e;
import static vanim.planar.myFont;

/**
 * @author protonlaser91
 */
public abstract class Grid2D extends Grid {

    protected int frameCountInit;
    protected int frameCountBuffer;
    protected boolean textInit = true;
    protected Color textColor;

    /**
     * @param builder The necessary builder to construct this Plane
     *                Check the GeometricSpaceBuilder to know the params involved
     */
    protected Grid2D(GeometricSpaceBuilder builder) {
        super(builder);
    }

    /**
     * TODO
     *
     * @param theta
     */
    protected abstract Grid2D rotatePlane(float theta);

    /**
     * TODO
     *
     * @param initial
     * @param output
     */
    protected abstract boolean moveVector(PVector initial, PVector output);

    /**
     * Settings
     *
     * @return
     */
    @Override
    public boolean generateSpace() {
        canvas.beginDraw();
        canvas.translate(canvas.width / 2.0f, canvas.height / 2.0f);
        processing.scale(e); // TODO: think about ScreenX and mainProcessingTranslate
        canvas.background(0, 0, 0, 0);
        canvas.textFont(myFont);
        canvas.textSize(38);
        canvas.textAlign(CENTER);
        canvas.rectMode(CENTER);
        canvas.colorMode(HSB); // TODO: transfer this to GeometricSpace
        return true;
    }

    /**
     * Interpolate alpha to 0 (fade to black) for the text and the lines
     *
     * @return When the operation has completed
     */

    @Override
    public boolean fadeOut() {
        return textColor.getAlpha().interpolate(0, MapEase.EASE_IN) & super.fadeOut();
    }

    /**
     * @return The reference to the canvas the host plane uses to draw on
     */
    @Override
    public Graphics2D getCanvas() {
        return canvas;
    }
}
