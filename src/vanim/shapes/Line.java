package vanim.shapes;

import vanim.planes.Plane;
import vanim.root.vobjects.VObject;
import vanim.storage.vector.FVector;
import vanim.storage.Color;

import static vanim.util.Mapper.EASE_IN_OUT;
import static vanim.util.Mapper.map3;

public class Line extends VObject {

    float weight;
    FVector start, end, amtPush = new FVector(), inc = new FVector();
    int strokeNum;

    public Line(Plane p, FVector start, FVector end, float weight, Color color) { //p not used here...
        super(p, start, new FVector(), color);
        this.weight = weight;
        this.end = new FVector(absScale.getX() * end.getX(), absScale.getY() * end.getY());
        this.start = new FVector(pos); // Copy constructor
    }

    public Line(Plane p, FVector start, FVector end){
        this(p, start, end,4,new Color());
    }

    public void setEnd(float x, float y) {
        end.setXY(absScale.getX()*x,absScale.getY()*y);
    }

    public void setStrokeCap(int WHAT){
        strokeNum = WHAT;
    }

    public void setMapPower(float jj){
        mapPower = jj;
    }

    public void setStart(float x, float y) {
        start.setXY(x,y);
    }

    //float value, float start1, float stop1, float start2, float stop2, int type, int when
    public void push(float dividend) {
        amtPush.setX((end.getX() - start.getX()) / dividend);
        amtPush.setY((end.getY() - start.getY()) / dividend);

        if (amtPush.getX() > 0) {
            if (pos.getX() < end.getX()) {
                inc.addX(amtPush);
                pos.setX(map3(inc.getX(), start.getX(), end.getX(), start.getX(), end.getX(), mapPower, EASE_IN_OUT));
            }
            if (pos.getX() > end.getX())
                pos.setX(end.getX());
        }

        else {
            if (pos.getX() > end.getX()) {
                inc.addX(amtPush);
                pos.setX(map3(inc.getX(), start.getX(), end.getX(), start.getX(), end.getX(), mapPower, EASE_IN_OUT));
            }
            if (pos.getX() < end.getX())
                pos.setX(end.getX());
        }

        if (amtPush.getY() > 0){
            if (pos.getY() < end.getY()){
                inc.addY(amtPush);
                pos.setY(map3(inc.getY(), start.getY(), end.getY(), start.getY(), end.getY(), mapPower, EASE_IN_OUT));
            }
            if (pos.getY() > end.getY())
                pos.setY(end.getY());
        } else {
            if (pos.getY() > end.getY()){
                inc.addY(amtPush);
                pos.setY(map3(inc.getY(), start.getY(), end.getY(), start.getY(), end.getY(), mapPower, EASE_IN_OUT));
            }
            if (pos.getY() < end.getY())
                pos.setY(end.getY());
        }


        //println("x1: " + pos.getX() + " y1: " + pos.getY());
       // println("x2: " + pos.getX() + " y2: " + pos.getY());
    }


    public boolean display(Object... obj) {
        canvas.strokeCap(strokeNum);
        push(50);
        if (weight != 0)
            canvas.strokeWeight(weight);

        canvas.stroke(color.getHue(), color.hue255() ? 0 : 255, 255);
        canvas.line(start.getX(), start.getY(), pos.getX(), pos.getY());
        return pos.equals(end);
    }
}