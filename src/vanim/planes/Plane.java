package vanim.planes;


import processing.core.PVector;
import vanim.core.Applet;
import vanim.root.CanvasObject;
import vanim.root.modular.Scalable;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;
import vanim.storage.vector.IVector;
import vanim.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * @author protonlaser91
 */
public abstract class Plane extends CanvasObject implements Scalable<Plane> {

    protected FVector ticks;
    protected int frameCountInit;
    protected int frameCountBuffer;
    protected boolean textInit = true;
    protected Color textColor;
    protected Map<Text, Boolean> texts = new HashMap<>(); // Text, hasDisplayed

    /**
     * @param p          Processing instance that is to be used.
     * @param pos        The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param ticks      The distance between each tick in vector form [x,y,(z)]
     */
    protected Plane(Applet p, FVector pos, IVector dimensions, FVector ticks, Color color) {
        super(p, p.createGraphics2D(dimensions.getX(), dimensions.getY()), pos, new FVector(dimensions), color);
        this.ticks = ticks;
    }

    /**
     * Generate a plane with correct tick marks, grid lines, and data attributes.
     * @return When the operation has finished
     */
    protected abstract boolean generatePlane();

    /**
     * TODO
     *
     * @param theta
     */
    protected abstract Plane rotatePlane(float theta);

    /**
     * TODO
     *
     * @param initial
     * @param output
     */
    protected abstract void moveVector(PVector initial, PVector output);

    /**
     * @return The color of the text marking the axes
     */
    public Color getTextColor() {
        return this.textColor;
    }

    /**
     * @return The scale the plane uses.
     */
    @Override
    public Scale getScale() {
        return this.scale;
    }

    /**
     * @return The processing instance.
     */
    public Applet getProcessingInstance() {
        return this.processing;
    }

    /**
     * Add text to Map<Text,Boolean> where Text is the text to be added
     * and Boolean is whether it has displayed (doesn't call display)
     *
     * @param text The text to be added
     * @return If that text object has been displayed
     */
    public boolean displayText(Text text) {
        texts.putIfAbsent(text, false);
        return texts.get(text);
    }

    /**
     * Interpolate alpha to 0 (fade to black) for the text and the lines
     *
     * @return When the operation has completed
     */

    @Override
    public boolean fadeOut() {
        return textColor.getAlpha().interpolate(0) & super.fadeOut();
    }
}
