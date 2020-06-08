package vanim.shapes;
import processing.core.*;
import vanim.misc.Color;
import vanim.storage.Scale;
import vanim.planar;
import vanim.root.VObject;

import static vanim.misc.Mapper.*;

public class Line extends VObject {

    float weight;
    float finalX, finalY, amtPushX, amtPushY;
    float startX, startY;
    int strokeNum;
    float incX, incY;

    public Line(PApplet p, PGraphics c, float x1, float y1, float x2, float y2){
        this(p,c, x1, y1, x2,y2,4,new Color(),planar.absScale);
    }

    public Line(PGraphics c, float x1, float y1, float x2, float y2){
        this(null,c,x1,y1,x2,y2,4,new Color(),planar.absScale);
    }

    public Line(PGraphics c, float x1, float y1, float x2, float y2, float weight, float colHue){
        this(null,c,x1,y1,x2,y2,weight,new Color(colHue), planar.absScale);
    }


    public Line(PApplet p,PGraphics c, float x1, float y1, float x2, float y2, float weight, Color color, Scale scale) { //p not used here...
        super(p,c, x1, y1, 0,color);
        this.weight = weight;
        finalX = scale.getScaleX()*x2;
        finalY = scale.getScaleY()*y2;
        startY = pos.y;
        startX = pos.x;
        incX = 0;
        incY = 0;
       // println("BEGINNING: " + pos.x);
    }


    public void setFinal(float x, float y) {
        finalX = x;
        finalY = y;
    }


    @Override
    public boolean scale(float... obj) {
        return false;
    }

    public void setStrokeCap(int WHAT){
        strokeNum = WHAT;
    }

    public void setMapPower(float jj){
        mapPower = jj;
    }
    public void setStart(float x, float y) {
        startX = x;
        startY = y;
    }

    //float value, float start1, float stop1, float start2, float stop2, int type, int when
    public void push(float dividend) {
        amtPushX = (finalX - startX) / dividend;
        amtPushY = (finalY - startY) / dividend;

        if (amtPushX > 0) {
            if (pos.x < finalX) {
                incX += amtPushX;
                pos.x = map3(incX, startX, finalX, startX, finalX, mapPower, EASE_IN_OUT);
            }
            if (pos.x > finalX)
                pos.x = finalX;
        }

        else {
            if (pos.x > finalX) {
                incX += amtPushX;
                pos.x = map3(incX, startX, finalX, startX, finalX, mapPower, EASE_IN_OUT);
            }
            if (pos.x < finalX)
                pos.x = finalX;
        }

        if (amtPushY > 0){
            if (pos.y < finalY){
                incY += amtPushY;
                pos.y = map3(incY, startY, finalY, startY, finalY, mapPower, EASE_IN_OUT);
            }
            if (pos.y > finalY)
                pos.y = finalY;
        } else {
            if (pos.y > finalY){
                incY += amtPushY;
                pos.y = map3(incY, startY, finalY, startY, finalY, mapPower, EASE_IN_OUT);
            }
            if (pos.y < finalY)
                pos.y = finalY;
        }


        //println("x1: " + pos.x + " y1: " + pos.y);
       // println("x2: " + pos.x + " y2: " + pos.y);
    }


    public boolean display(Object... obj) {
        canvas.strokeCap(strokeNum);
        push(50);
        if (weight != 0)
            canvas.strokeWeight(weight);

        canvas.stroke(color.getHue(), color.hue255() ? 0 : 255, 255);
        canvas.line(startX, startY, pos.x, pos.y);
        return pos.x == finalX && pos.y == finalY;
    }
}