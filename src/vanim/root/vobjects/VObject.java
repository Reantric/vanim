package vanim.root.vobjects;

import vanim.planes.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;

public abstract class VObject extends AbsoluteVObject {

    /**
     * @param p          Plane that is to be drawn on
     * @param pos        The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param color      The color of the object, in HSB
     */
    public VObject(Plane p, FVector pos, FVector dimensions, Color color) {
        super(p, pos, dimensions, color);
        pos.multiplyAll(absScale.getX(), absScale.getY()); //just PVec(x,y) works!
        dimensions.multiplyAll(absScale.getX(), absScale.getY()); // Starting dimensions!
    }

    public VObject(Plane p, FVector pos, Color color) {
        this(p,pos,new FVector(),color);
    }

    public VObject(Plane p, FVector pos, float s, Color color) {
        this(p, pos, new FVector(s,s,s), color);
    }

    public VObject(Plane p, FVector pos, FVector dimensions) {
        this(p, pos, dimensions,new Color());
    }

    /**
     *
     * @param newWidth New width of VObject.
     * If the VObject has been displayed, this may cause errors.
     */
    public void setWidth(float newWidth){
        super.setWidth(absScale.getX()*newWidth);
    }

    /**
     *
     * @param newHeight New height of VObject.
     * If the VObject has been displayed, this may cause errors.
     */
    public void setHeight(float newHeight){
        super.setHeight(absScale.getY()*newHeight);
    }

    /**
     * @param newWidth New width of VObject.
     * @param newHeight New height of VObject.
     * If the VObject has been displayed, this may cause errors.
     */
    public void setWidthHeight(float newWidth, float newHeight){
        super.setWidthHeight(absScale.getX()*newWidth,absScale.getY()*newHeight);
    }

}
