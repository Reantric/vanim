package vanim;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.event.MouseEvent;
import vanim.mfunc.Narrator;
import vanim.planes.CartesianPlane;
import vanim.shapes.Circle;
import vanim.storage.FVector;
import vanim.storage.IVector;

import java.text.DecimalFormat;

import static vanim.Directions.*;

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
        plane = new CartesianPlane(this, new FVector(), new IVector(1920, 1080), new FVector(1, 1));
        b = new Circle(plane, new FVector(0, 0), 1, 4);
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
            case 's' -> plane.getScale().setXY(1.1f, 1.1f);
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
