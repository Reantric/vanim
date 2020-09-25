package vanim.root.builder;

import vanim.geometry.twodim.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;


public class VObjectBuilder {
    Plane plane;
    FVector pos, dimensions;
    Color color;
    Reason reason;

    /**
     * @param p             Plane that is to be drawn on
     * @param pos           The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions    The width, height (and depth) of the object (in scaled coordinates, not absolute)
     *                      but will become absolute after passing this class up the hierarchy
     * @param color         The color of the object, in HSB
     * @param reasonCreated The reason this object was created
     */
    public VObjectBuilder(Plane p, FVector pos, FVector dimensions, Color color, Reason reasonCreated) {
        this.plane = p;
        this.pos = pos;
        this.dimensions = dimensions;
        this.color = color;
        this.reason = reasonCreated;
    }

    public VObjectBuilder(Plane p, FVector pos, FVector dimensions, Color color) {
        this(p, pos, dimensions, color, Reason.USER_CREATED);
    }

    public VObjectBuilder(Plane p, FVector pos, Color color, Reason reasonCreated) {
        this(p, pos, new FVector(), color, reasonCreated);
    }

    // Circle Builder?
    public VObjectBuilder(Plane p, FVector pos, float radius, Color color) {
        this(p, pos, new FVector(radius, radius), color, Reason.USER_CREATED);
    }

    public Color getColor() {
        return color;
    }

    public Plane getPlane() {
        return plane;
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
