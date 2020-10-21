package vanim.root.vobjects;

import vanim.core.Graphics2D;
import vanim.geometry.GeometricSpace;
import vanim.root.CanvasObject;
import vanim.root.builder.GeometricSpaceBuilder;
import vanim.root.builder.VObjectBuilder;
import vanim.root.modular.Scalable;
import vanim.storage.Scale;
import vanim.util.Reason;

/**
 * @author protonlaser91
 */
public abstract class AbsoluteVObject extends CanvasObject implements Scalable<CanvasObject> {

    protected long incrementor = 0;
    protected float mapPower = 2;
    protected GeometricSpace geometricSpace;
    Reason reasonCreated;

    /**
     * @param builder The VObject builder needed to build this VObject
     *                Contains Plane p, FVector pos, FVector dimensions, Color color, Reason reasonCreated
     */
    public AbsoluteVObject(VObjectBuilder builder) { // Plane constructor!
        super(builder.getGeometricSpace().getProcessingInstance(), (Graphics2D) builder.getGeometricSpace().getCanvas(), builder.getPos(), builder.getDimensions(), builder.getColor());
        geometricSpace = builder.getGeometricSpace();
        this.reasonCreated = builder.getReasonCreated();
    }

    public AbsoluteVObject(GeometricSpaceBuilder builder) {
        super(builder);
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
    public AbsoluteVObject setDimensions(float newWidth, float newHeight) {
        dimensions.setXY(newWidth, newHeight);
        return this;
    }

    /**
     * TODO: Add transform, rotate, and other methods.
     *
     * @param s The new Scale object that will replace the original Scale object.
     */

    @Override
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