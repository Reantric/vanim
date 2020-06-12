package vanim.root;

import vanim.planes.Plane;
import vanim.storage.FVector;
import vanim.storage.Scale;
import vanim.util.Color;

import static vanim.planar.*;

/**
 * @author protonlaser91
 */
public abstract class VObject extends CanvasObject{

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
    public VObject(Plane p, FVector pos, FVector dimensions, Color color){ // Plane constructor!
        super(p.getProcessingInstance(),p.getCanvas(),pos,dimensions);
        absScale = p.getScale();
        pos.multiplyAll(scale.getX(),scale.getY()); //just PVec(x,y) works!
        dimensions.multiplyAll(scale.getX(),scale.getY()); // Starting dimensions!
        this.color = color;
    }

    public VObject(Plane p, FVector pos, Color color){
        this(p,pos, null,color);
    }

    public VObject(Plane p, FVector pos,float s, Color color) {
        this(p,pos,new FVector(s,s,s),color);
    }

    public VObject(Plane p, FVector pos, FVector dimensions) {
        this(p,pos,dimensions,new Color());
    }

    /**
     *
     * @param newWidth New width of VObject.
     * If the VObject has been displayed, this may cause errors.
     */
    public void setWidth(float newWidth){
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
     * @param newWidth New width of VObject.
     * @param newHeight New height of VObject.
     * If the VObject has been displayed, this may cause errors.
     */
    public void setWidthHeight(float newWidth, float newHeight){
        dimensions.setX(newWidth);
        dimensions.setY(newHeight);
    }

    /**
     * Create background rectangle around VObject with 125 alpha
     TODO: Add color options and alpha options
     */
    public void backgroundRect(){
        canvas.noStroke();
        canvas.fill(0,0,0,125); //125
        if (canvas.textAlign == LEFT){
            canvas.rectMode(CORNER);
            canvas.rect(pos.getX(),pos.getY()-dimensions.getY() + 14,dimensions.getX(),dimensions.getY());
        }
        else {
            canvas.rectMode(CENTER);
            canvas.rect(pos.getX(),pos.getY() - 14,dimensions.getX(),dimensions.getY());
        }

    }

    /**
     * TODO: Add transform, rotate, and other methods.
     * @param s The new Scale object that will replace the original Scale object.
     * @return If the operation was a success.
     */
    public boolean scale(Scale s) {
        this.scale = s;
        return true;
    }

}