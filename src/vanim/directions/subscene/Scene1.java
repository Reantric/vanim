package vanim.directions.subscene;

import processing.core.PApplet;
import vanim.directions.Scene;
import vanim.mfunc.Narrator;
import vanim.planes.CartesianPlane;
import vanim.shapes.Circle;
import vanim.shapes.Line;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;
import vanim.storage.vector.IVector;
import vanim.util.Mapper;
import vanim.util.Useful;

import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static vanim.Directions.*;
import static vanim.util.Mapper.LINEAR;
import static vanim.util.Mapper.SINUSOIDAL;

public final class Scene1 extends Scene {

    public static Scale sinus = new Scale(1, 1);
    public static CartesianPlane plane;
    public static Circle b;
    public static Narrator n;
    public static Line l;
    public static boolean changeHue = false;

    public Scene1(PApplet window) {
        super(window);
        plane = new CartesianPlane(window, new FVector(), new IVector(1920, 1080), new FVector(1, 1));
        b = new Circle(plane, new FVector(0, 0), 1, 4);
        n = new Narrator(plane.getCanvas());
        l = new Line(plane, new FVector(), new FVector(1, 1), new Color(160, 255, 255));
    }

    @Override
    public boolean execute() {

        step[0] = plane.generatePlane();

        /* Maybe think about a MObject[] or ArrayList<MObject> where display can be called on everyone */

        if (step[0]) {
            plane.getColor().getHue().interp(changeHue ? 0 : 255, LINEAR, 1);
            if (plane.getColor().getHue().is255())
                changeHue = true;
            else if (plane.getColor().getHue().is0())
                changeHue = false;

            b.scale(sinus);
            step[1] = b.display();
        }

        if (step[1])
            step[2] = b.drawTangentLine(cos(mapInc), sin(mapInc), true);


        if (step[2]) {
            plane.getCanvas().text("Radius: " + b.getRadius(), 300, -330);
            inc += globalIncrementor;
            plane.getCanvas().textSize(40);
            /* Call to waste time
            int[] useless = RandomDebug.generateRandomIntArr(8);
            Arrays.sort(useless);
            canvas.text(Arrays.toString(useless),400,-270); //String.format("(%s) - (%s)",destinationOnCircleLabel.get(destinationInc == 0 ? destinationOnCircle.length - 1 : destinationInc-1),destinationOnCircleLabel.get(destinationInc))
            Call to waste time */
            float min = destinationOnCircle[destinationInc == 0 ? destinationOnCircle.length - 1 : destinationInc - 1];
            float max = destinationOnCircle[destinationInc];
            mapInc = Mapper.map2(inc % 2, 0f, 2f, -min, -max, SINUSOIDAL, Mapper.EASE_IN_OUT);

            if (inc % 2 > 2 - globalIncrementor) {
                destinationInc = (destinationInc + 1) % destinationOnCircle.length;
                inc = (float) Useful.ceilAny(inc, globalIncrementor);
            }

            if (!step[3])
                step[3] = destinationInc == 0; //Advance to dir[3]
        }

        if (step[3]) {
            plane.fadeOut();

            if (incTrack == 0) {
                incTrack = inc;
                frameCountTrack = window.frameCount;
            }

            if (!step[4]) {
                sinus.setAll(Mapper.map2(inc, incTrack, incTrack + 1.5f, 1f, 1.5f, SINUSOIDAL, Mapper.EASE_IN_OUT));
                step[4] = Math.abs(sinus.getX() - 1.5) < 0.01;
            }
        }

        if (!step[5] && step[4]) { // Stop running once step 5 is true
            sinus.setAll(Mapper.map2(inc, incTrack - 0.5f, incTrack + 1.5f, 0.5f, 1.5f, SINUSOIDAL, Mapper.EASE_IN_OUT));
            step[5] = window.frameCount - frameCountTrack > 850 && Math.abs(sinus.getX() - 1) < 0.01;
        }

        if (step[5]) {
            sinus.setAll(1);
        }
        return step[6];

    }
}
