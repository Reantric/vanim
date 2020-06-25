package vanim.text;

import vanim.planes.Plane;
import vanim.root.vobjects.AbsoluteVObject;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;

import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.LEFT;
import static vanim.planar.CENTER;
import static vanim.util.Mapper.*;

/**
 * @author protonlaser91
 */
public class TextVObject extends AbsoluteVObject {
    public String str;
    float tSize;
    float transp = 0;
    int align = CENTER;
    boolean displayRect = true;
    protected Color color;

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
        this.color = color;
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
    public void scale(Scale s) {

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
        canvas.fill(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), map2(transp, 0, 255, 0, 255, QUADRATIC, EASE_IN));

        if (canvas.textAlign != align)
            canvas.textAlign(align);

        canvas.text(str,pos.getX(),pos.getY());
        if (transp < 255)
            transp += 4;

        return transp > 255;

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

}