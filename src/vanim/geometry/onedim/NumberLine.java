package vanim.geometry.onedim;

import vanim.core.Applet;
import vanim.root.CanvasObject;
import vanim.root.modular.Scalable;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;

public class NumberLine extends CanvasObject implements Scalable<NumberLine> {
    float ticks;

    /**
     * @param p          Processing instance that is to be used.
     * @param pos        The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param ticks      The distance between each tick in vector form [x,y,(z)]
     */
    protected NumberLine(Applet p, FVector pos, Integer dimension, float ticks, Color color) {
        super(p, p.createGraphics2D(dimension, dimension), pos, new FVector(dimension), color);
        this.ticks = ticks;
    }

    /**
     * @param args Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    @Override
    public boolean display(Object... args) {
        return false;
    }

    /**
     * @param s The new Scale object that will replace the original Scale object.
     * @return If the operation was a success.
     */
    @Override
    public NumberLine scale(Scale s) {
        return null;
    }

    /**
     * @return The scale of the current object
     */
    @Override
    public Scale getScale() {
        return null;
    }
}
