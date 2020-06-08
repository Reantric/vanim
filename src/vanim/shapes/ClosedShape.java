package vanim.shapes;

import vanim.Planes.Plane;
import vanim.misc.Color;
import vanim.misc.Useful;
import vanim.root.VObject;
import vanim.storage.Vector;

import java.util.*;

public class ClosedShape extends VObject { // Maybe in the far far future when i add 3D call this ClosedShape2D
    protected int delVal, optimalDelVal;
    protected float incrementTangentLine = 0;
    protected int speed;
    protected float distance;
    protected List<float[]> original = new ArrayList<>();
    protected List<float[]> coords = new ArrayList<>(); //[x,y]

   // public ClosedShape(PGraphics c, float x, float y, float xAxis, float yAxis) {
     //   this(c,x,y,xAxis,yAxis,1,100,planar.scale);
    //}

    public ClosedShape(Plane p, Vector<Float> pos, Vector<Float> dimensions, int speed, int delVal) {
        super(p, pos,dimensions, new Color(0)); // For now, it does not utilize a color
        this.speed = speed;
        this.delVal = delVal;
    }

    public boolean graph() {

        /* Works for all closed shapes
        boolean epsX = coords.get(0)[0] / coords.get(coordsSize - 1)[0] > 0.99f && coords.get(0)[0] / coords.get(coordsSize - 1)[0] < 1.01f; // x does not start/end at 0 !
        boolean epsY = coordsSize > 1 && coords.get(coordsSize - 2)[1] > coords.get(0)[1] && coords.get(coordsSize - 1)[1] <= coords.get(0)[1];


         if (epsX && epsY) {
            optimalDelVal = coordsSize;
            println("Optimal delVal: " + optimalDelVal); //<-- reenable
        } */

        for (int i = 0; i < coordsSize - 1; i++) {
            canvas.stroke(Useful.getColor(i, 0, delVal), 255, 255);
            canvas.strokeWeight(5);
            canvas.line(coords.get(i)[0], coords.get(i)[1], coords.get(i + 1)[0], coords.get(i + 1)[1]);
        }

        return coordsSize == delVal;
    }

    @Override
    public boolean scale(float... obj) { //Instant scaling!, is Absolute! Not relative!
        int len = obj.length;
        width /= relScale.getScaleX();
        height /= relScale.getScaleY();
        if (len == 1) {
            relScale.setScaleAll(obj[0]);
            width *= relScale.getScaleX();
            height *= relScale.getScaleY();

            for (int i = 0; i < coordsSize; i++) {
                coords.get(i)[0] = original.get(i)[0] * relScale.getScaleX();
                coords.get(i)[1] = original.get(i)[1] * relScale.getScaleX();
            }
        } else if (len == 2) {

            relScale.setScaleX(obj[0]);
            relScale.setScaleY(obj[1]);
            width *= relScale.getScaleX();
            height *= relScale.getScaleY();

            for (int i = 0; i < coordsSize; i++) {
                coords.get(i)[0] = original.get(i)[0] * relScale.getScaleX();
                coords.get(i)[1] = original.get(i)[1] * relScale.getScaleY();
            }
        }

        //distance = Math.max((width/relScale.getScaleX() + height/relScale.getScaleY())*0.7f,(width + height) * 0.9f);
        //distance = Math.min((width / relScale.getScaleX() + height / relScale.getScaleY()), (width + height) * 0.9f);
        return true;
    }

    @Override
    public boolean display(Object... obj) {
        return false;
    }
}
