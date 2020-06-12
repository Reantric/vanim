package vanim.shapes;

import vanim.planes.Plane;
import vanim.root.VObject;
import vanim.storage.FVector;
import vanim.storage.Scale;
import vanim.util.Color;

import static vanim.planar.CENTER;
import static vanim.util.Mapper.*;

/**
 * @author protonlaser91
 */
public class TextVObject extends VObject {
    public String str;
    float tSize;
    float transp = 0;
    int align = CENTER;
    boolean displayRect = true;

    /**
     *
     * @param p Plane that is to be drawn on
     * @param s String to be displayed
     * @param pos The position of the object on the canvas (in absolute coordinates)
     * @param tSize The size of the text (can be omitted)
     * @param color The color of the object, in HSB
     */
    public TextVObject(Plane p, String s, FVector pos, float tSize, Color color){
        super(p,pos,color);
        str = s;
        this.tSize = tSize;
        canvas.textSize(this.tSize);
        dimensions.setXY(canvas.textWidth(str),this.tSize);
    }

    public TextVObject(Plane p, String s,FVector pos, Color color){
        this(p,s,pos,p.getCanvas().textSize,color);
    }

    /**
     *
     * @param ALIGN Set alignment of text using Processing's textAlign modes
     */
    public void setTextAlign(int ALIGN) {
        align = ALIGN;
    }

    /**
     * gonna be honest i really dont know what this does, hence the parameter name
     * @param tf
     */
    public void setDisplayRect(boolean tf){
        displayRect = tf;
    }

    @Override
    public boolean scale(Scale s) {
        return false;
    }

    /**
     * Display the text along with a background rectangle to allow the text to be seen more easily
     * @param obj Varargs to display the object at coordinates
     * @return When the text is fully visible (transparency > 255)
     */
    @Override
    public boolean display(Object... obj){
        if (displayRect)
            this.backgroundRect();

        canvas.textSize(tSize);
        canvas.fill(color.getHue(),color.getSaturation(),color.getBrightness(),map2(transp,0,255,0,255,QUADRATIC,EASE_IN));

        if (canvas.textAlign != align)
            canvas.textAlign(align);

        canvas.text(str,pos.getX(),pos.getY());
        if (transp < 255)
            transp += 4;

        return transp > 255;

    }

}