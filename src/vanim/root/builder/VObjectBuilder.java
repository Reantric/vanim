package vanim.root.builder;

import vanim.geometry.GeometricSpace;
import vanim.storage.color.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;


public class VObjectBuilder {
    GeometricSpace geometricSpace;
    FVector pos, dimensions;
    Color color;
    Reason reason;

    /**
     * @param g             GeometricSpace that is to be drawn on
     * @param pos           The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions    The width, height (and depth) of the object (in scaled coordinates, not absolute)
     *                      but will become absolute after passing this class up the hierarchy
     * @param color         The color of the object, in HSB
     * @param reasonCreated The reason this object was created
     *                      TODO: Change GeometricSpace to Canvas, and fix Scale issues!
     */
    public VObjectBuilder(GeometricSpace g, FVector pos, FVector dimensions, Color color, Reason reasonCreated) {
        this.geometricSpace = g;
        this.pos = pos;
        this.dimensions = dimensions;
        this.color = color;
        this.reason = reasonCreated;
    }

    public VObjectBuilder(GeometricSpace g, FVector pos, FVector dimensions, Color color) {
        this(g, pos, dimensions, color, Reason.USER_CREATED);
    }

    public VObjectBuilder(GeometricSpace g, FVector pos, Color color, Reason reasonCreated) {
        this(g, pos, new FVector(), color, reasonCreated);
    }

    // Circle Builder?
    public VObjectBuilder(GeometricSpace g, FVector pos, float radius, Color color) {
        this(g, pos, new FVector(radius, radius), color, Reason.USER_CREATED);
    }

    public Color getColor() {
        return color;
    }

    public GeometricSpace getGeometricSpace() {
        return geometricSpace;
    }

    public FVector getPos() {
        return pos;
    }

    public FVector getDimensions() {
        return dimensions;
    }

    public Reason getReasonCreated() {
        return reason;
    }
}
