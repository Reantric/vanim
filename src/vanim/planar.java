package vanim;
import vanim.mfunc.*;
import vanim.misc.*;
import vanim.shapes.*;
import processing.core.*;
import processing.event.*;


import java.text.DecimalFormat;
import java.lang.*;
import java.util.*;

public class planar extends PApplet {

public static DecimalFormat df = new DecimalFormat("###.#");
public static float e = 1;
public static CartesianPlane plane;
public static boolean directionCompleted = true;
public static float incTrack = 0, inc = 0;
public static float angle;
public static PFont myFont, italics;
public static PGraphics canvas;
public static float sinus = 1;
public static Arrow arr;
public static PImage narrator, vectorV;
public static final int WIDTH = 1920;
public static final int HEIGHT = 1080;
public static Narrator n;
public static boolean[] directions = new boolean[100];
/* sprites */
public static VObject crLine;
public static Ellipse b;
/* sprites */

public void setup() {
  
  myFont = createFont("vanim\\data\\cmunbmr.ttf", 150, true);
  italics = createFont("vanim\\data\\cmunbmo.ttf",150,true);
  textFont(myFont, 64);
  canvas = createGraphics(1920, 1080, P2D);
  plane = new CartesianPlane(this,canvas,1,1);

  //arr = new Arrow(canvas,cos(inc), sin(inc));
  
  canvas.shapeMode(CENTER);
  rectMode(CENTER);
  narrator = loadImage("vanim\\data\\cM3.png");
  vectorV = loadImage("vanim\\data\\vectorV.png");
  vectorV.resize(100,100);
  n = new Narrator(canvas);

  /* Sprites */
  //crLine = new Line(canvas,0,0,cos(0.3f),sin(0.5f),7,255);
  b = new Circle(canvas,0,0,1,0.04f,plane.delVal);
 //
  /* Sprites */

  /* Directions setup*/
  /* Directions setup*/
}

public void directions() {
  plane.rotatePlane(angle);
 // arr.setVector(cos(inc),sin(inc));



  /* Maybe think about a MObject[] or ArrayList<MObject> where display can be called on everyone */
  if (directions[0])
    directions[1] = b.display();

  if (directions[1]) {
    inc += 0.01;
    directions[2] = b.drawTangentLine(sinus*cos(inc), sinus*sin(inc)) && inc > PI;
  }

  if (directions[2]) {
    directions[3] = n.narrate();
  }

  if (directions[3]) {
    if (incTrack == 0)
      incTrack = inc;
    sinus = Mapper.map2(inc - incTrack,0.5f,1.5f,0.5f,1.5f,Mapper.SINUSOIDAL,Mapper.EASE_IN_OUT); // maybe add scaleMethod to BigCircle/MObject abstract class
  }

}


public void mouseWheel(MouseEvent event) {
  e += -0.07f*event.getCount();
}

public void keyPressed() {
  switch (key) {
  case 'v':
    angle = PI/2;
    break;
  case 'c':
    angle = 0;
    break;
  case 'd':
    plane.restrictDomain(-PI/2, PI/2);
    break;
  case 's':
    plane.sY *= 1.1f;
    break;
  case 'x':
    frameRate(2);
    break;
  }
}

public void draw() {
  background(0);
  scale(e);
  plane.run(canvas);
  directions[0] = plane.generatePlane();
  // plane.graph();
  directions();
  plane.display();
  /* debug space */
  // bruv();
  /* debug space */
  //saveFrame("test/line-######.png");
  // directions();
}

  public void settings() {  size(1920, 1080, P2D);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] {  planar.class.getCanonicalName() };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}