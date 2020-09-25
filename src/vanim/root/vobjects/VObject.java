package vanim.root.vobjects;

import vanim.root.builder.VObjectBuilder;

public abstract class VObject extends AbsoluteVObject {

    /**
     * @param builder The VObject builder needed to build this VObject
     *                Contains Plane p, FVector pos, FVector dimensions, Color color, Reason reasonCreated
     */
    public VObject(VObjectBuilder builder) {
        super(builder);
      /*  pos.scale(plane.getScale()); //just PVec(x,y) works!
        dimensions.scale(plane.getScale()); // Starting dimensions! */
    }

    /**
     * @param newWidth New width of VObject.
     *                 If the VObject has been displayed, this may cause errors.
     */
    @Override
    public VObject setWidth(float newWidth) {
        return (VObject) super.setWidth(plane.getScale().getX() * newWidth);
    }

    /**
     * @param newHeight New height of VObject.
     *                  If the VObject has been displayed, this may cause errors.
     */
    @Override
    public VObject setHeight(float newHeight) {
        return (VObject) super.setHeight(plane.getScale().getY() * newHeight);
    }

    /**
     * @param newWidth  New width of VObject.
     * @param newHeight New height of VObject.
     *                  If the VObject has been displayed, this may cause errors.
     */
    @Override
    public VObject setDimensions(float newWidth, float newHeight) {
        return (VObject) super.setDimensions(plane.getScale().getX() * newWidth, plane.getScale().getY() * newHeight);
    }

}
