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

    @Override
    public boolean display(Object... obj){
        canvas.stroke(color.getHue(),color.getSaturation(),color.getBrightness());
        canvas.strokeCap(strokeNum);
        setMapPower(3);
        push(100);
        if (weight != 0)
            canvas.strokeWeight(weight);


        canvas.line(average.getX(),average.getY(),pos.getX(),pos.getY());
        canvas.line(average.getX(),average.getY(),2*average.getX() - pos.getX(),2*average.getY() - pos.getY());

        return pos.getX().equals(end.getX()) && pos.getY().equals(end.getY());
    }
}
