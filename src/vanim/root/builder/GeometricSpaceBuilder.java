package vanim.root.builder;

import vanim.core.Applet;
import vanim.storage.color.Color;
import vanim.storage.vector.FVector;
import vanim.storage.vector.IVector;

public class GeometricSpaceBuilder {
    Applet processing;
    FVector pos, ticks;
    IVector dimensions;
    Color color;

    /**
     * @param p          Processing instance that is to be used.
     * @param pos        The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param ticks      The distance between each tick in vector form [x,y,(z)]
     */
    public GeometricSpaceBuilder(Applet p, FVector pos, IVector dimensions, FVector ticks, Color color) {
        this.processing = p;
        this.pos = pos;
        this.dimensions = dimensions;
        this.ticks = ticks;
        this.color = color;
    }

    public GeometricSpaceBuilder(Applet p, FVector pos, IVector dimensions, FVector ticks) {
        this(p, pos, dimensions, ticks, new Color(150, 200, 255));
    }


    public Applet getProcessingInstance() {
        return processing;
    }

    public FVector getPos() {
        return pos;
    }

    public FVector getTicks() {
        return ticks;
    }

    public IVector getDimensions() {
        return dimensions;
    }

    public Color getColor() {
        return color;
    }
}
