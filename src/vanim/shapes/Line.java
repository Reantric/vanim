package vanim.shapes;

import org.jetbrains.annotations.NotNull;
import vanim.planes.CartesianPlane;
import vanim.planes.Plane;
import vanim.root.vobjects.VObject;
import vanim.storage.Color;
import vanim.storage.Subcolor;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

import static vanim.util.MapConstant.EASE_IN_OUT;
import static vanim.util.Mapper.map3;


public class Line extends VObject {

    float weight;
    FVector start, end, amtPush = new FVector(), inc = new FVector();
    int strokeNum;
    float dividend = 100;

    public Line(Plane p, FVector start, @NotNull FVector end, float weight, Color color, Reason reasonCreated) { //p not used here...
        super(p, start, new FVector(), color, reasonCreated);
        this.weight = weight;
        this.end = new FVector(absScale.getX() * end.getX(), absScale.getY() * end.getY());
        this.start = new FVector(pos); // Copy constructor
    }

    public Line(Plane p, FVector start, @NotNull FVector end, float weight, Color color) {
        this(p, start, end, weight, color, Reason.USER_CREATED);
    }

    public Line(Plane p, FVector start, FVector end) {
        this(p, start, end, 4, new Color());
    }

    public Line(CartesianPlane plane, FVector start, FVector end, Color color) {
        this(plane, start, end, 4, color);
    }

    public Line setEnd(float x, float y) {
        end.setXY(absScale.getX() * x, absScale.getY() * y);
        return this;
    }

    public Line setStartEnd(FVector start, FVector end) {
        this.start = start;
        this.end = end;
        return this;
    }

    public Line setStrokeCap(int WHAT) {
        strokeNum = WHAT;
        return this;
    }

    public Line setMapPower(float jj) {
        mapPower = jj;
        return this;
    }

    public Line setStart(float x, float y) {
        start.setXY(x, y);
        return this;
    }

    public Line setDividend(float dividend) {
        this.dividend = dividend;
        return this;
    }

    //float value, float start1, float stop1, float start2, float stop2, int type, int when
    protected void push(float dividend) {
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
        push(dividend / 2);
        if (weight != 0)
            canvas.strokeWeight(weight);

        if (color.getHue().is255())
            canvas.stroke(color.getHue(), new Subcolor(), color.getBrightness(), color.getAlpha());
        else
            canvas.stroke(color);

        canvas.line(start, pos);
        return pos.equals(end);
    }
}