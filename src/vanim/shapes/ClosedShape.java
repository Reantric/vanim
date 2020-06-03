package vanim.shapes;

import processing.core.PGraphics;
import vanim.misc.Color;
import vanim.misc.Useful;
import vanim.root.VObject;
import vanim.mfunc.CartesianPlane;

import static vanim.planar.plane;

public class ClosedShape extends VObject { // Maybe in the far far future when i add 3D call this ClosedShape2D
    protected int delVal, optimalDelVal;
    protected float incrementTangentLine = 0;
    protected int speed;
    protected float distance;

    public ClosedShape(PGraphics c, float x, float y, float xAxis, float yAxis) {
        super(c, x, y, xAxis * plane.sX, yAxis * plane.sY, new Color(0));
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
        width /= scale[0];
        height /= scale[1];
        if (len == 1) {
            scale[0] = obj[0];
            scale[1] = scale[0];
            scale[2] = scale[0];
            width *= scale[0];
            height *= scale[1];

            for (int i = 0; i < coordsSize; i++) {
                coords.get(i)[0] = original.get(i)[0] * scale[0];
                coords.get(i)[1] = original.get(i)[1] * scale[0];
            }
        } else if (len == 2) {

            scale[0] = obj[0];
            scale[1] = obj[1];
            width *= scale[0];
            height *= scale[1];

            for (int i = 0; i < coordsSize; i++) {
                coords.get(i)[0] = original.get(i)[0] * scale[0];
                coords.get(i)[1] = original.get(i)[1] * scale[1];
            }
        }

        //distance = Math.max((width/scale[0] + height/scale[1])*0.7f,(width + height) * 0.9f);
        //distance = Math.min((width / scale[0] + height / scale[1]), (width + height) * 0.9f);
        return true;
    }

    @Override
    public boolean display(Object... obj) {
        return false;
    }
}
