package vanim;
import vanim.planes.CartesianPlane;
import vanim.mfunc.*;
import vanim.shapes.*;

import static vanim.Directions.*;

import processing.core.*;
import processing.event.*;
import java.text.DecimalFormat;
import java.lang.*;

public class planar extends PApplet {

    public static boolean startSavingFrames = false;
    public static DecimalFormat df = new DecimalFormat("###.#");
    public static float e = 1;
    public static float angle;
    public static PFont myFont, italics;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static PGraphics canvas;
    public static CartesianPlane plane;
    public static Circle b;
    public static Narrator n;

    public void setup() {
        myFont = createFont("vanim\\data\\cmunbmr.ttf", 150, true);
        italics = createFont("vanim\\data\\cmunbmo.ttf ", 150, true);
        textFont(myFont, 64);
        plane = new CartesianPlane(this, canvas, 1, 1);
        b = new Circle(canvas, 0, 0, 1, 4);
        n = new Narrator(canvas);
        System.out.println(destinationOnCircleLabel);
    }

    public void mouseWheel(MouseEvent event) {
        e += -0.07f * event.getCount();
    }

    public void keyPressed() {
        switch (key) {
            case 'v' -> angle = PI / 2;
            case 'c' -> angle = 0;
            case 'd' -> plane.restrictDomain(-PI / 2, PI / 2);
            case 's' -> absScale.multiplyScaleY(1.1f);
            case 'x' -> startSavingFrames = true;//frameRate(2);
        }
    }

    public void draw() {
        background(0);
        scale(e);
        plane.run(canvas);
        step[0] = plane.generatePlane();
        // plane.graph();
        directions(this);
        plane.display();
        /* debug space */
        // bruv();
        /* debug space */
        if (startSavingFrames)
            saveFrame("test/line-######.png");
        // directions();
    }

    public void settings() {
        size(1920, 1080, P2D);
        smooth(8);
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{planar.class.getCanonicalName()};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
