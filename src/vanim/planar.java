package vanim;

import processing.core.PApplet;
import processing.core.PFont;
import processing.event.MouseEvent;
import vanim.core.Applet;
import vanim.directions.Directions;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import static vanim.directions.Directions.destinationOnCircleLabel;
import static vanim.directions.Directions.directions;
import static vanim.directions.subscene.Scene1.plane;

public class planar extends Applet {

    public static boolean startSavingFrames = false;
    public static DecimalFormat df = new DecimalFormat("###.#");
    public static float e = 1;
    public static float angle;
    public static PFont myFont, italics;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    public void setup() {
        String commonPath = "src\\vanim\\data\\";
        myFont = createFont(commonPath + "cmunbmr.ttf", 150, true);
        italics = createFont(commonPath + "cmunbmo.ttf", 150, true);
        textFont(myFont, 64);

        try {
            Directions.init(this);
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
            System.exit(1);
        }

        colorMode(HSB);
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
        directions();
      //  plane.display();
        if (startSavingFrames)
            saveFrame("test/line-######.png");
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
