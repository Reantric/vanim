package vanim.shapes;

import processing.core.PApplet;
import processing.core.PGraphics;
import vanim.planes.Plane;
import vanim.storage.FVector;
import vanim.storage.Vector;
import vanim.util.Color;
import vanim.storage.Scale;
import vanim.planar;

public class DoubleLine extends Line{

    FVector average;

    public DoubleLine(Plane p, FVector start, FVector end, float weight, Color color) {
            super(p,start,end,weight,color);
            average.setX((start.getX() + end.getX())/2.0f);
            average.setY((start.getX() + end.getY())/2.0f);
        }

    public DoubleLine(Plane p, FVector start, FVector end) {
        this(p,start,end,4,new Color());
    }

    public DoubleLine(Plane p, FVector start, FVector end, float weight) {
        this(p,start,end,weight,new Color());
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
