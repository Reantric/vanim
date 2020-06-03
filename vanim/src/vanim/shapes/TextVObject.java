package vanim.shapes;

import org.w3c.dom.Text;
import vanim.misc.*;
import static vanim.misc.Mapper.*;
import static vanim.planar.*;
import processing.core.*;

public class TextVObject extends VObject {
    public String str;
    float tSize;
    float transp = 0;
    int align = CENTER;
    boolean displayRect = true;

    public TextVObject(PGraphics c, String s, float x, float y, float size, float colHue, float colSat, float colBright){
        super(c,x,y,colHue,colSat,colBright);
        c.textSize(size);
        width = c.textWidth(s);
        height = size;
        tSize = size;
        str = s;
    }

    public TextVObject(PGraphics c, String s, float x, float y, float colHue,float colSat,float colBright){
        this(c,s,x,y,c.textSize,colHue, colSat,colBright);
    }

    public TextVObject(PGraphics c, String s, float x, float y, float size, float colHue){
        this(c,s,x,y,size,colHue, 255,255);
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

    public boolean display(Object... obj){
        if (displayRect)
            this.backgroundRect();

        canvas.textSize(tSize);
        canvas.fill(hue,sat,bri,map2(transp,0,255,0,255,QUADRATIC,EASE_IN));

        if (canvas.textAlign != align)
            canvas.textAlign(align);

        canvas.text(str,pos.x,pos.y);
        if (transp < 255)
            transp += 4;

        return transp > 255;

    }

}