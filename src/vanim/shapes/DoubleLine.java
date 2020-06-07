package vanim.shapes;

import processing.core.PApplet;
import processing.core.PGraphics;
import vanim.misc.Color;
import vanim.misc.Scale;
import vanim.planar;

public class DoubleLine extends Line{

    float xAverage;
    float yAverage;

    public DoubleLine(PApplet p, PGraphics c, float x1, float y1, float x2, float y2, float weight,Color color, Scale scale) {
            super(p, c, x1, y1, x2, y2,weight,color,scale);
            xAverage = (startX + finalX)/2.0f;
            yAverage = (startY + finalY)/2.0f;
        }
    public DoubleLine(PApplet p, PGraphics c, float x1, float y1, float x2, float y2, float weight,Color color) {
        this(p,c,x1,y1,x2,y2,weight,color, planar.absScale);
    }

    public DoubleLine(PGraphics c, float x1, float y1, float x2, float y2) {
        this(null,c,x1,y1,x2,y2,4,new Color(),planar.absScale);
    }

    public DoubleLine(PGraphics c, float x1, float y1, float x2, float y2, float weight) {
        this(null,c,x1,y1,x2,y2,weight,new Color(),planar.absScale);
    }

    public DoubleLine(PGraphics canvas, float x1, float y1, float x2, float y2, float weight, Color color) {
        this(null,canvas,x1,y1,x2,y2,weight,color,planar.absScale);
    }

    public DoubleLine(PGraphics canvas, float x1, float y1, float x2, float y2,float weight, Color color, Scale relScale) {
        this(null,canvas,x1,y1,x2,y2,weight,color,relScale);
    }

    // public DoubleLine(PGraphics canvas, float v, float v1, float v2, float v3, float v4, int i, int i1) {
 //       super();
   // }

    @Override
    public boolean display(Object... obj){
        canvas.stroke(color.getHue(),color.getSaturation(),color.getBrightness());
        canvas.strokeCap(strokeNum);
        setMapPower(3);
        push(100);
        if (weight != 0)
            canvas.strokeWeight(weight);


        canvas.line(xAverage,yAverage,pos.x,pos.y);
        canvas.line(xAverage,yAverage,2*xAverage - pos.x,2*yAverage - pos.y);

        return pos.x == finalX && pos.y == finalY;
    }
}
