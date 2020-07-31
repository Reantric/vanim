package vanim.root.vobjects;

import vanim.planes.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

public abstract class VObject extends AbsoluteVObject {

    /**
     * @param p          Plane that is to be drawn on
     * @param pos        The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param color      The color of the object, in HSB
     */
    public VObject(Plane p, FVector pos, FVector dimensions, Color color, Reason reasonCreated) {
        super(p, pos, dimensions, color, reasonCreated);
        pos.multiplyAll(absScale.getX(), absScale.getY()); //just PVec(x,y) works!
        dimensions.multiplyAll(absScale.getX(), absScale.getY()); // Starting dimensions!
    }

    public VObject(Plane p, FVector pos, FVector dimensions, Color color) {
        this(p, pos, dimensions, color, Reason.USER_CREATED);
    }

    public VObject(Plane p, FVector pos, Color color) {
        this(p, pos, new FVector(), color);
    }

    public VObject(Plane p, FVector pos, float s, Color color) {
        this(p, pos, new FVector(s, s, s), color);
    }

    public VObject(Plane p, FVector pos, FVector dimensions) {
        this(p, pos, dimensions,new Color());
    }

    /**
     * @param newWidth New width of VObject.
     *                 If the VObject has been displayed, this may cause errors.
     */
    @Override
    public VObject setWidth(float newWidth) {
        return (VObject) super.setWidth(absScale.getX() * newWidth);
    }

    /**
     * @param newHeight New height of VObject.
     *                  If the VObject has been displayed, this may cause errors.
     */
    @Override
    public VObject setHeight(float newHeight) {
        return (VObject) super.setHeight(absScale.getY() * newHeight);
    }

    /**
     * @param newWidth  New width of VObject.
     * @param newHeight New height of VObject.
     *                  If the VObject has been displayed, this may cause errors.
     */
    @Override
    public VObject setWidthHeight(float newWidth, float newHeight) {
        return (VObject) super.setWidthHeight(absScale.getX() * newWidth, absScale.getY()*newHeight);
    }

}
