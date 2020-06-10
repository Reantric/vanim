package vanim.directions;

import processing.core.PApplet;
import vanim.util.Mapper;
import vanim.util.Useful;

import static processing.core.PApplet.*;
import static vanim.Directions.*;
import static vanim.planar.*;

public class Scene1 extends Scene{

    public Scene1(PApplet window) {
        super(window);
    }

    @Override
    public boolean scene() {
        //plane.rotatePlane(angle);
        // arr.setVector(cos(inc),sin(inc));



        /* Maybe think about a MObject[] or ArrayList<MObject> where display can be called on everyone */
        if (step[0]) {
            b.scale(sinus);
            step[1] = b.display();
        }

        if (step[1])
            step[2] = b.drawTangentLine(sinus * cos(mapInc), sinus * sin(mapInc), true);


        if (step[2]) {
            canvas.text("Radius: " + b.getRadius(), 300, -330);
            inc += globalIncrementor;
            canvas.textSize(40);
            /* Call to waste time
            int[] useless = RandomDebug.generateRandomIntArr(8);
            Arrays.sort(useless);
            canvas.text(Arrays.toString(useless),400,-270); //String.format("(%s) - (%s)",destinationOnCircleLabel.get(destinationInc == 0 ? destinationOnCircle.length - 1 : destinationInc-1),destinationOnCircleLabel.get(destinationInc))
            Call to waste time */
            float min = destinationOnCircle[destinationInc == 0 ? destinationOnCircle.length - 1 : destinationInc - 1];
            float max = destinationOnCircle[destinationInc];
            //System.out.println(window.frameCount - frameCountTrack);
            mapInc = Mapper.map2(inc % 2, 0f, 2f, -min, -max, Mapper.SINUSOIDAL, Mapper.EASE_IN_OUT);

            if (inc % 2 > 2 - globalIncrementor) {
                destinationInc = (destinationInc + 1) % destinationOnCircle.length;
                inc = (float) Useful.ceilAny(inc, globalIncrementor);
                System.out.println("CHANGED!: " + destinationInc);
            }

            if (!step[3])
                step[3] = destinationInc == 0; //Advance to dir[3]
        }

        if (step[3]) {
            if (incTrack == 0) {
                incTrack = inc;
                frameCountTrack = window.frameCount;
            }

            if (!step[4]) {
                sinus = Mapper.map2(inc, incTrack, incTrack + 1.5f, 1f, 1.5f, Mapper.SINUSOIDAL, Mapper.EASE_IN_OUT);
                step[4] = Math.abs(sinus - 1.5) < 0.01;
            }
        }

        if (!step[5] && step[4]) { // Stop running once step 5 is true
            sinus = Mapper.map2(inc, incTrack - 0.5f, incTrack + 1.5f, 0.5f, 1.5f, Mapper.SINUSOIDAL, Mapper.EASE_IN_OUT);
            step[5] = window.frameCount - frameCountTrack > 850 && Math.abs(sinus - 1) < 0.01;
        }

        if (step[5]) {
            sinus = 1;

        }
        return step[6];
    }
}
