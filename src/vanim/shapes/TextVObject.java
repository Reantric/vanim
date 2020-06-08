package vanim.shapes;

import static vanim.misc.Mapper.*;
import static vanim.planar.*;
import processing.core.*;
import vanim.Planes.Plane;
import vanim.misc.Color;
import vanim.storage.Scale;
import vanim.root.VObject;
import vanim.storage.Vector;

public class TextVObject extends VObject {
    public String str;
    float tSize;
    float transp = 0;
    int align = CENTER;
    boolean displayRect = true;

    public TextVObject(Plane p, String s, Vector<Float> pos, float tSize, Color color){
        super(p,pos,color);
        str = s;
        this.tSize = tSize;
        canvas.textSize(this.tSize);
        dimensions.setXY(canvas.textWidth(str),this.tSize);
    }

    public TextVObject(Plane p, String s, Vector<Float> pos, Color color){
        this(p,s,pos,p.getCanvas().textSize,color);
    }

    public void setTextAlign(int ALIGN) {
        align = ALIGN;
    }

    public void setDisplayRect(boolean tf){
        displayRect = tf;
    }

    @Override
    public boolean scale(float... obj) {
        return false;
    }

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