package vanim.root.vobjects;

import vanim.planes.Plane;
import vanim.root.CanvasObject;
import vanim.root.modular.Scalable;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

import static vanim.util.Reason.USER_CREATED;

/**
 * @author protonlaser91
 */
public abstract class AbsoluteVObject extends CanvasObject implements Scalable<CanvasObject> {

    protected long incrementor = 0;
    protected float mapPower = 1;
    protected int coordsSize = 0;
    protected Scale absScale;
    protected Plane plane;
    Reason reasonCreated;

    /**
     * @param p             Plane that is to be drawn on
     * @param pos           The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions    The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param color         The color of the object, in HSB
     * @param reasonCreated The reason this object was created
     */
    public AbsoluteVObject(Plane p, FVector pos, FVector dimensions, Color color, Reason reasonCreated) { // Plane constructor!
        super(p.getProcessingInstance(), p.getCanvas(), pos, dimensions, color);
        plane = p;
        absScale = p.getScale();
        this.reasonCreated = reasonCreated;
    }

    public AbsoluteVObject(Plane p, FVector pos, FVector dimensions, Color color) {
        this(p, pos, dimensions, color, USER_CREATED);
    }

    public AbsoluteVObject(Plane p, FVector pos, Color color) {
        this(p, pos, new FVector(), color);
    }

    public AbsoluteVObject(Plane p, FVector pos, float s, Color color) {
        this(p, pos, new FVector(s, s, s), color);
    }

    public AbsoluteVObject(Plane p, FVector pos, FVector dimensions) {
        this(p, pos, dimensions, new Color());
    }

    public AbsoluteVObject(Plane p, FVector pos, Color color, Reason reasonCreated) {
        this(p, pos, new FVector(), new Color(), reasonCreated);
    }

    /**
     * @return The reason this object was created.
     */
    public Reason getReasonCreated() {
        return this.reasonCreated;
    }

    /**
     * @param newReason This must be declared before display
     */
    public AbsoluteVObject setReason(Reason newReason) {
        this.reasonCreated = newReason;
        return this;
    }

    /**
     * @param newWidth New width of VObject.
     *                 If the VObject has been displayed, this may cause errors.
     */
    public AbsoluteVObject setWidth(float newWidth) {
        dimensions.setX(newWidth);
        return this;
    }

    /**
     * @param newHeight New height of VObject.
     *                  If the VObject has been displayed, this may cause errors.
     */
    public AbsoluteVObject setHeight(float newHeight) {
        dimensions.setY(newHeight);
        return this;
    }

    /**
     * @param newWidth  New width of VObject.
     * @param newHeight New height of VObject.
     *                  If the VObject has been displayed, this may cause errors.
     */
    public AbsoluteVObject setWidthHeight(float newWidth, float newHeight) {
        dimensions.setXY(newWidth, newHeight);
        return this;
    }

    /**
     * TODO: Add transform, rotate, and other methods.
     *
     * @param s The new Scale object that will replace the original Scale object.
     */

    public AbsoluteVObject scale(Scale s) {
        this.scale = s;
        return this;
    }

    public AbsoluteVObject scale(float s) {
        scale.setAll(s);
        return this;
    }

    /**
     * @return The scale of the Object that extends CanvasObject
     */
    @Override
    public Scale getScale() {
        return this.scale;
    }

}