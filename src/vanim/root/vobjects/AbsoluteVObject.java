package vanim.root.vobjects;

import vanim.planes.Plane;
import vanim.root.CanvasObject;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;

/**
 * @author protonlaser91
 */
public abstract class AbsoluteVObject extends CanvasObject {

    protected Color color;
    protected long incrementor = 0;
    protected float mapPower = 1;
    protected int coordsSize = 0;
    protected Scale absScale;

    /**
     *
     * @param p Plane that is to be drawn on
     * @param pos The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param color The color of the object, in HSB
     */
    public AbsoluteVObject(Plane p, FVector pos, FVector dimensions, Color color) { // Plane constructor!
        super(p.getProcessingInstance(), p.getCanvas(), pos, dimensions);
        absScale = p.getScale();
        this.color = color;
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

    /**
     * @return Color object of the current VObject
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @param newWidth New width of VObject.
     *                 If the VObject has been displayed, this may cause errors.
     */
    public void setWidth(float newWidth) {
        dimensions.setX(newWidth);
    }

    /**
     *
     * @param newHeight New height of VObject.
     * If the VObject has been displayed, this may cause errors.
     */
    public void setHeight(float newHeight){
        dimensions.setY(newHeight);
    }

    /**
     * @param newWidth  New width of VObject.
     * @param newHeight New height of VObject.
     *                  If the VObject has been displayed, this may cause errors.
     */
    public void setWidthHeight(float newWidth, float newHeight) {
        dimensions.setXY(newWidth, newHeight);
    }

    /**
     * TODO: Add transform, rotate, and other methods.
     *
     * @param s The new Scale object that will replace the original Scale object.
     */
    public void scale(Scale s) {
        this.scale = s;
    }

    public boolean fadeOut() {
        return color.getAlpha().interp(0);
    }

}