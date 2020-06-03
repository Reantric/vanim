package vanim;

import processing.core.PApplet;
import vanim.misc.Mapper;
import vanim.misc.Useful;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static vanim.planar.*;

public class Directions {
    public static boolean[] step = new boolean[100];
    public static float sinus = 1;
    public static int destinationInc = 1; // be consistent with mapInc initialization!
    // ^^ Also the reason that destinationInc-1 is used and not +1
    public static final Float[] destinationOnCircle = {-PI, 0f, PI / 4, PI / 2}; // A sad necessity
    // ^^ See if this could become a circular linked list? Along with destinationOnCircleLabel
    public static float incTrack = 0, inc = 0, mapInc = Mapper.map2(inc, 0f, 2f, -destinationOnCircle[0], -destinationOnCircle[1], Mapper.SINUSOIDAL, Mapper.EASE_IN_OUT);
    public static float globalIncrementor = 0.01f;
    public static int frameCountTrack = 0;
   // public static Line d = new Line(2);
    //Cosmetics
    public static final List<String> destinationOnCircleLabel = Arrays.stream(destinationOnCircle).map(x -> {
        if (String.valueOf(x).length() > 3)
            return switch (String.valueOf(x).substring(0, 4)) {
                case "3.14", "-3.1" -> "PI";
                case "1.57" -> "PI/2";
                case "-1.5" -> "-PI/2";
                case "0.78" -> "PI/4";
                case "-0.7" -> "-PI/4";
                default -> String.valueOf(x).substring(0, 4);
            };
        return String.valueOf(x);
    }).collect(Collectors.toList());

    public static void directions(PApplet window) {
        plane.rotatePlane(angle);
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

    }
}
