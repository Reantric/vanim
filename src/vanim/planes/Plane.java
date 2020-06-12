package vanim.planes;


import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import vanim.root.CanvasObject;
import vanim.storage.Scale;
import vanim.storage.Vector;

import static processing.core.PApplet.P2D;

/**
 * @author protonlaser91
 */
public abstract class Plane extends CanvasObject {

    protected Vector<Float> ticks;
    protected int frameCountInit;
    protected int frameCountBuffer;

    /**
     *
     * @param p Processing instance that is to be used.
     * @param pos The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param ticks The distance between each tick in vector form [x,y,(z)]
     */
    public Plane(PApplet p, Vector<Float> pos, Vector<Integer> dimensions, Vector<Float> ticks) {
        super(p, p.createGraphics(dimensions.getX(),dimensions.getY(),P2D), pos, new Vector<>(dimensions));
        this.ticks = ticks;
    }

    /**
     * @param s The new Scale object that will replace the original Scale object.
     * @return If the operation was a success.
     */
    protected abstract boolean scale(Scale s);

    /**
     * Generate a plane with correct tick marks, grid lines, and data attributes.
     * @return When the operation has finished
     */
    protected abstract boolean generatePlane();

    /**
     * TODO
     * @param theta
     */
    protected abstract void rotatePlane(float theta);

    /**
     * TODO
     * @param initial
     * @param output
     */
    protected abstract void moveVector(PVector initial, PVector output);

    /**
     *
     * @return The canvas the plane uses to draw on
     */
    public PGraphics getCanvas(){
        return this.canvas;
    }

    /**
     *
     * @return The scale the plane uses.
     */
    public Scale getScale(){
        return this.scale;
    }

    /**
     *
     * @return The processing instance.
     */
    public PApplet getProcessingInstance(){
        return this.processing;
    }
}
