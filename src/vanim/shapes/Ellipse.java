package vanim.shapes;

import processing.core.*;
import vanim.misc.Mapper;
import vanim.misc.Useful;

import static processing.core.PApplet.*;
import static vanim.planar.*;

public class Ellipse extends ClosedShape { // Make ellipse inherit from Closed Shape later

    public Ellipse(PGraphics c, float x, float y, float xAxis, float yAxis, int speed) {
        super(c, x, y, xAxis, yAxis);
        this.speed = speed;
        distance = (plane.sY * yAxis + plane.sX * xAxis) * 0.9f;
        //300*0.9 = 270 which IS the distance
        delVal = 100; //Change default later using formula!
    }

    public Ellipse(PGraphics c, float x, float y, float xAxis, float yAxis, int speed, int delVal) {
        this(c, x, y, xAxis, yAxis, speed);
        this.delVal = delVal;
    }

    public void addPoint() {
        if (coordsSize < delVal) {
            coords.add(new float[]{width * cos(incrementor / 100f), -height * sin(incrementor / 100f)});
            original.add(new float[]{width * cos(incrementor / 100f), -height * sin(incrementor / 100f)});
            coordsSize++;
        }
    }

    public boolean drawTangentLine(float x, float y) {
        return this.drawTangentLine(x, y, false);
    }

    public boolean drawTangentLine(float x, float y, boolean isMoving) {
        // x *= 1.007f;
        // y *= 1.007f;
        float newDist = Mapper.map2(incrementTangentLine, 0, distance, 0, distance, Mapper.QUADRATIC, Mapper.EASE_IN);
        if (newDist > distance)
            newDist = distance;

        float common = newDist / (2 * sqrt((float) (1 + (Math.pow(height, 4) * plane.sX * x * plane.sX * x) / (Math.pow(width, 4) * plane.sY * y * plane.sY * y))));
        float x1 = common + (x * plane.sX); //+
        float y1 = (-x * height * height) / (y * width * width) * common + (y * plane.sY); //+
        float x2 = -common + (x * plane.sX); //-
        float y2 = (x * height * height) / (y * width * width) * common + (y * plane.sY); //-
        //PGraphics c, float x1, float y1, float x2, float y2, float weight, float colHue
        canvas.strokeWeight(5);
        canvas.stroke(30, 255, 255);
        if (Float.isNaN(y1) || Float.isNaN(y2)) {
            y1 = distance / 2;
            y2 = -y1;
        }
        // System.out.printf("(%.4f,%.4f),(%.4f,%.4f), inc: %.4f\n",x1,y1,x2,y2, Directions.inc);
        canvas.line(x1, y1, x2, y2);
        incrementTangentLine += distance / 50.0f;
        //System.out.println(newDist);
        return isMoving || (newDist / distance > 0.99 && newDist / distance < 1.01);
    }

    @Override
    public boolean scale(float... obj) { //Instant scaling!, is Absolute! Not relative!
        super.scale(obj);
        distance = Math.min((width / scale[0] + height / scale[1]), (width + height) * 0.9f);
        return true;
    }

    @Override
    public boolean display(Object... obj) {
        addPoint();
       // boolean b = graph();
        incrementor += speed;
        return graph();
    }
}
