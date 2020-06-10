package vanim.planes;
import static vanim.planar.*;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import vanim.storage.*;
import vanim.util.*;
import vanim.storage.Scale;
import vanim.storage.Vector;
import vanim.root.VObject;
import vanim.shapes.*;
import processing.core.*;
import java.util.*;
import java.lang.*;


public class CartesianPlane extends Plane { // Work on mouseDrag after!
    
    Vector<Float> startingValues;
    Vector<Float> rescale;
    float scaleFactor;
    float max; //counter
    float currentColor = 0;
    boolean restrictDomain = false;
    boolean gridDrawn, textDrawn = false;
    float restrictedDomainX1, restrictedDomainX2;
    Vector<Float> restrictedDomain;
    List<Double> randArr = new ArrayList<Double>();
    float aspectRatio;

    /* Object initializations */  // Create color object soon for animateVector();
    public VObject xAxis,yAxis;
    public VObject[] xLines, yLines;
    public TextVObject[] xText, yText;
    List<PVector> points = new ArrayList<PVector>();
    private static final Multiset<CartesianPlane> allObjects = HashMultiset.create();
    /* Object initializations */

    /**
     *
     * @param p Reference to the processing instance created.
     * @param pos Vector holding the position (x,y,z) of the plane.
     * @param dimensions Vector holding the dimensions (x,y,z) of the plane in resolution pixels.
     */
    public CartesianPlane(PApplet p, FVector pos, IVector dimensions, FVector ticks){
        super(p,pos,dimensions,ticks);
        rescale.setXY((float) canvas.width/WIDTH,(float) canvas.height/HEIGHT);
        scale.setX(300/ticks.getX() * rescale.getX()); // 200 def
        scale.setY(300/ticks.getY() * rescale.getY()); // 200 def
        scaleFactor = 6/5.0f * ticks.getX()/100.0f;
        scaleFactor = 0.06f; // debug
        startingValues.setX((float) Useful.floorAny(-canvas.width/(2*scale.getX()),ticks.getX())); // <---- Issues here when resizing canvas
        startingValues.setY((float) Useful.floorAny(-canvas.height/(2*scale.getY()),ticks.getY())); // <---- Issues here when resizing canvas
        max = startingValues.getX() - ticks.getX()/5; // should start at 25
        aspectRatio = (WIDTH/1080.0f)/(rescale.getX()/rescale.getY());
        //canvas.line(-scale.getX()*startingValues.getX(),0,scale.getX()*startingValues.getX(),0);
        xAxis = new DoubleLine(canvas,startingValues.getX(),0,-startingValues.getX(),0,4,new Color(255,0,255));
        yAxis = new DoubleLine(canvas,0,startingValues.getY(),0,-startingValues.getY(),4,new Color(255,0,255));
        frameCountInit = processing.frameCount;
        frameCountBuffer = 15;

        /* VObject[] inits */
        xLines = new VObject[(int) (-4*startingValues.getX()/ticks.getX())];
        yLines = new VObject[(int) (-4*startingValues.getY()/ticks.getY())];
        xText = new TextVObject[xLines.length/2]; // skip over 0
        yText = new TextVObject[yLines.length/2]; // skip over 0
        println("xLength: " + xText.length + " yLength: " + yText.length); //9, 5 or 8, 4 :(
        // Assuming xLines.length > yLines.length

        canvas.textSize(42);

        for (int i = 0; i < xLines.length; i++){

            if (i != yLines.length/2 && i < yLines.length) { // Assumption takes place here
                if ((startingValues.getY() + ticks.getY() * i / 2) % ticks.getY() == 0) {
                    yLines[i] = new DoubleLine(canvas, startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2, -startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2, 4, new Color(150, 200, 255));
                    yText[i / 2] = new TextVObject(canvas, df.format(-startingValues.getY() - ticks.getY() * i / 2), -12, scale.getY() * (startingValues.getY() + ticks.getY() * i / 2) - 12, new Color(255, 0, 255));
                    yText[i / 2].setTextAlign(RIGHT);
                    yText[i / 2].setDisplayRect(false);
                } else
                    yLines[i] = new DoubleLine(canvas, startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2, -startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2, 1.5f, new Color(150, 200, 255));
            }

            if (i != xLines.length/2) {
                if ((startingValues.getX() + ticks.getX() * i / 2) % ticks.getX() == 0) {
                    xLines[i] = new DoubleLine(canvas, startingValues.getX() + ticks.getX() * i / 2, startingValues.getY(), startingValues.getX() + ticks.getX() * i / 2, -startingValues.getY(),4,new Color(150,200,255));
                    xText[i / 2] = new TextVObject(canvas, df.format(startingValues.getX() + ticks.getX() * i / 2), startingValues.getX() + ticks.getX() * i / 2 > 0 ? scale.getX() * (startingValues.getX() + ticks.getX() * i / 2) : scale.getX() * (startingValues.getX() + ticks.getX() * i / 2) - 8, 44, new Color(255, 0, 255));
                } else
                    xLines[i] = new DoubleLine(canvas, startingValues.getX() + ticks.getX() * i / 2, startingValues.getY(), startingValues.getX() + ticks.getX() * i / 2, -startingValues.getY(),1.5f,new Color(150,200,255));
            }
        }

        allObjects.add(this);
    }

    /**
     *
     * Creates a 2D Cartesian Plane with an x axis and a y axis
     * @return
     */
    public boolean generatePlane(){
        gridDrawn = true;
        currentColor = Useful.getColor(max,startingValues.getX(),-startingValues.getX());
        canvas.beginDraw();
        canvas.translate(canvas.width/2.0f,canvas.height/2.0f);
        canvas.background(0);
        canvas.textFont(myFont);
        canvas.textAlign(CENTER);
        canvas.rectMode(CENTER);
        canvas.textSize(38);
        canvas.colorMode(HSB);
        canvas.stroke(150,200,255);
        canvas.strokeWeight(4);

        //  pushMatrix();
        //canvas.rotate(slowRotate.incrementor); //-processing.PI/2
       // incrementor = 0;

        for (int j = 0; j < yLines.length; j++){
            if (j != yLines.length/2 && !yLines[j].display())
                gridDrawn = false;
        }
        //Cant make this loop more efficient because of line below...
        if (processing.frameCount < frameCountInit + frameCountBuffer) return false;

        for (int i = 0; i < xLines.length; i++){

            if (i != xLines.length/2 && !xLines[i].display())
                gridDrawn = false;

            if (i % 2 == 0) {
                if (i != xText.length)
                    xText[i/2].setWidthHeight(60 + (xText[i/2].str.length()-3)*10,56);
                if ((i != xText.length && !textDrawn && xText[i / 2].display()) | (i != yText.length && i < 2*yText.length && !textDrawn && yText[i / 2].display()))
                    textDrawn = true;

            }
        }

        canvas.stroke(0,0,255);
        canvas.strokeWeight(4);

        //>= optimalDelVal

        return textDrawn & xAxis.display() & yAxis.display();
        // return xAxisR.display() & yAxisU.display() & xAxisL.display() & yAxisD.display();
    }

    /**
     * Label all axes
     */
    public boolean labelAxes(){
        canvas.textSize(42);
        canvas.textAlign(CENTER);
        canvas.rectMode(CENTER);
        for (float x = startingValues.getX(); x < -startingValues.getX(); x += ticks.getX()){ //-width/(2*scale.getX()) - ticks.getX()/5 + ticks.getX(), starting 0,0 at width/2, height/2

            if (x == 0) continue;

            String tX = df.format(x);
            canvas.fill(0,0,0,125);
            canvas.noStroke();
            canvas.rect(scale.getX()*x,30,60 + (tX.length()-3)*10,56);
            canvas.fill(255);

            if (x > 0)
                canvas.text(tX,scale.getX()*x,44);
            else
                canvas.text(tX,scale.getX()*x-8,44);
        }

        canvas.textAlign(RIGHT);
        for (float y = startingValues.getY(); y < -startingValues.getY(); y += ticks.getY()){
            if (y == 0) continue;
            canvas.text(df.format(-y),-12,scale.getY()*y-12);
        }

        return true;

    }

    @Override
    public boolean scale(Scale s) {
        return false;
    }

    public boolean display(Object... obj){
        //Is Object... obj because it can be default called or with 2 position args.

        if (textDrawn) {
            gridDrawn = true;
            labelAxes();
        }

        canvas.endDraw();

        processing.stroke(currentColor,255,255);
        processing.stroke(233);
        processing.strokeWeight(7);
        processing.noFill();
        processing.rect(pos.x,pos.y,canvas.width,canvas.height);
        processing.image(canvas,pos.x,pos.y);
        return true;
        // popMatrix();
    }

    /**
     * Graph any function
     */
    public void graph(){
        float sMax, endG;
        if (restrictDomain){
            sMax = restrictedDomainX1;
            endG = restrictedDomainX2;
        } else {
            sMax = startingValues.getX();
            endG = max;
        }

        //   loadRandArr();

        //  traceGraphF.vector.set(max,f(max+scaleFactor) * aspectRatio); // just the aspect ratios!
        //  traceGraphG.vector.set(max,g(max+scaleFactor) * aspectRatio);

        canvas.strokeWeight(5);
        canvas.strokeCap(processing.ROUND);
        for (float i = startingValues.getX(); i < max; i+=scaleFactor){
            if (i < sMax || i > endG){
                canvas.stroke(125,255,255,fadeGraph.fadeOut(0.01f));
            } else
                canvas.stroke(Useful.getColor(i,startingValues.getX(),-startingValues.getX()),255,255);

            /* Optimize graph, only use if no autoscale! */

        /*
        for (int t = 0; t < 5 && f(i) > -startingValues.getY(); t++){
          float jumpDistance = 5*scaleFactor;
          if (f(i+jumpDistance) < -startingValues.getY()){
            finalValue = jumpDistance;
          }
        }
        max = finalValue - 5*scaleFactor;
        */

            /* Optimize graph, only use if no autoscale! */

            canvas.line(scale.getX()*i,-scale.getY()*f(i),scale.getX()*(i+scaleFactor),-scale.getY()*(f(i+scaleFactor)));
            canvas.line(scale.getX()*i,-scale.getY()*g(i),scale.getX()*(i+scaleFactor),-scale.getY()*(g(i+scaleFactor)));


        }

        if (max < -startingValues.getX()) max+=scaleFactor;



        if (max > -2.88f && max < 2.88f) max -= 0.058f;
        //  println("x: " + max + " y: " + f(max));
        //   drawVector(traceGraphF);
        //  drawVector(traceGraphG);
    }


    public long factorial(int number) {
        var result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }



    /**
     *
     * @param x Input to the function (x-coord)
     * @return Output to the function (y-coord)
     */
    public float f(float x){

        return 0.2f*x;
        //(float) (double) randArr.get((int) x + 25);
    }

    public float g(float x){
        return x < 0 ? -4* sin(x): -log(x);
    }

    public void loadRandArr(){
        randArr.add(10*Math.random() - 5);
    }
    /**
     *
     * @param x1 Left side of domain restriction
     * @param x2 Rigt side of domain restriction
     * Restrict the domain
     */
    public void restrictDomain(float x1, float x2){
        restrictedDomainX1 = x1;
        restrictedDomainX2 = x2;
        restrictDomain = true;
    }

    /**
     *
     * @param theta Angle to be inputted
     * Rotates the plane by theta degrees
     */
    public void rotatePlane(float theta) { // To be changed with Easing class!

        slowRotate.setChange(theta);
        slowRotate.incEase(1.045f);
        slowRotate.doStuff();

      /* if (slowRotate.easing < 0.05 && !slowRotate.isEqual())
          slowRotate.incEase(1.045);


      if (!slowRotate.isEqual())
          slowRotate.incValue();


        if (slowRotate.isEqual())
          slowRotate.reset(); */


    }

    /**
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     * Creates a visible point at that location
     */
    public void createPoint(float x, float y){

        points.add(new PVector(x,y));

        canvas.stroke(255,scaler.fadeIn(9));
        canvas.fill(255,scaler.getTransp());
        canvas.circle(x,y,20);
    }

    /**
     * @param arrow  Vector to be drawn
     * Draws vector in Cartesian Space
     */
    public void drawVector(Arrow arrow){ //no need to graph and that stuff, just show vector!
        PVector v = arrow.vector; // aliases
        arrow.drawArc(canvas);
        canvas.colorMode(HSB);
        //float triangleSize;
        canvas.pushMatrix();


        arrow.doStuff(1.045f);


        float triangleSize = arrow.triangleSize; // VAR CAN BE USED!
        canvas.strokeWeight(arrow.triangleSize * 10.0f/12);


        float rotationAngle = v.heading();
        //should draw Ellipse but is drawing circle (FIX FOR OTHER RES OF CANVAS)
        float magnitude = arrow.getMag(scale.getX(),aspectRatio); // max < 0 ? scale.getX()*v.mag() - 16 : scale.getX()*v.mag(); //arrow.vectorMag; // 6 works...?! apply ease to this v.mag() - 6

        canvas.stroke(currentColor,255,255);
        canvas.strokeCap(processing.ROUND);



        /* text */
        canvas.fill(Useful.getColor(arrow.coordsSize,0,delVal),255,255);

        if (processing.TAU + rotationAngle > 3*processing.PI/2 && rotationAngle < 0)
            Useful.rotatedText(df.format(degrees(rotationAngle > 0 ? rotationAngle : processing.TAU + rotationAngle))+"°",canvas,scale.getX()*v.x/4,-scale.getY()*v.y/4,processing.PI-rotationAngle);
        else
            Useful.rotatedText(df.format(degrees(rotationAngle > 0 ? rotationAngle : processing.TAU + rotationAngle))+"°",canvas,scale.getX()*v.x/4.75f,-scale.getY()*v.y/4.75f,processing.PI-rotationAngle);

        canvas.textSize(80);
        canvas.fill(255,255,255);
        Useful.rotatedText(Useful.propFormat(v.mag()),canvas,scale.getX()*v.x/2 ,-scale.getY()*v.y/2,processing.PI-rotationAngle);
        /* text */

        canvas.rotate(-rotationAngle);

        canvas.line(0,0,magnitude,0);

        canvas.beginShape(processing.TRIANGLES);
        canvas.vertex(magnitude - triangleSize*1.5f + 8,-triangleSize);
        canvas.vertex(magnitude + 8, 0);
        canvas.vertex(magnitude - triangleSize*1.5f + 8,triangleSize);
        canvas.endShape();
        // canvas.triangle(magnitude-triangleSize*1.6,triangleSize,magnitude-triangleSize*1.6,-triangleSize,magnitude,0);

        canvas.popMatrix();

        arrow.addPoint(scale.getX()*v.x,-scale.getY()*v.y);
        arrow.graph(canvas,delVal); //delVAL
        /* overlaying text */
        canvas.textSize(42);
        canvas.fill(128,255,255);
        if (processing.PI-rotationAngle < 3*processing.PI/2 && processing.PI-rotationAngle > processing.PI/2)
            canvas.textAlign(LEFT,CENTER);
        else
            canvas.textAlign(RIGHT,CENTER);
        canvas.text(String.format("[cos(%s),sin(%s)]",df.format(v.x),df.format(v.y)),1.09f*scale.getX()*v.x,1.09f*-scale.getY()*v.y);
        /* overlaying text */

        /* cherry on top */
        canvas.noStroke();
        canvas.fill(Useful.getColor(arrow.coordsSize,0,delVal),255,255);
        canvas.circle(scale.getX()*v.x,-scale.getY()*v.y,8);

    }

    /**
     * @param initial PVector's initial position (Starting)
     * @param output PVector's ending position (Ending)
     * Animates using an ease function the PVector moving to its output
     */
    public void moveVector(PVector initial, PVector output){ // check implementAV.png
        // To be implemented
    }

    public void run(PGraphics p){
        // To be implemented
    }

}