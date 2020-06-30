package vanim.planes;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import vanim.root.vobjects.VObject;
import vanim.shapes.DoubleLine;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.Subcolor;
import vanim.storage.vector.FVector;
import vanim.storage.vector.IVector;
import vanim.text.TextVObject;
import vanim.util.Useful;

import java.util.ArrayList;
import java.util.List;

import static vanim.planar.*;


public final class CartesianPlane extends Plane { // Work on mouseDrag after!

    FVector startingValues = new FVector(), rescale, restrictedDomain;
    float scaleFactor;
    float max; //counter
    float currentColor = 0;
    boolean restrictDomain = false;
    boolean gridDrawn; // useless?
    List<Double> randArr = new ArrayList<Double>();
    float aspectRatio;
    /* Object initializations */  // Create color object soon for animateVector();
    Color textColor;
    public VObject xAxis, yAxis;
    public VObject[] xLines, yLines;
    public TextVObject[] xText, yText;
    //  List<PVector> points = new ArrayList<PVector>();
    /* Object initializations */

    /**
     * @param p          Reference to the processing instance created.
     * @param pos        Vector holding the position (x,y,z) of the plane.
     * @param dimensions Vector holding the dimensions (x,y,z) of the plane in resolution pixels.
     */
    public CartesianPlane(PApplet p, FVector pos, IVector dimensions, FVector ticks, Color color) {
        super(p, pos, dimensions, ticks, color);
        rescale = new FVector((float) canvas.width / WIDTH, (float) canvas.height / HEIGHT);

        scale.setX(300 / ticks.getX() * rescale.getX()); // 200 def
        scale.setY(300 / ticks.getY() * rescale.getY()); // 200 def

        startingValues.setX((float) Useful.floorAny(-canvas.width / (2 * scale.getX()), ticks.getX())); // <---- Issues here when resizing canvas
        startingValues.setY((float) Useful.floorAny(-canvas.height / (2 * scale.getY()), ticks.getY())); // <---- Issues here when resizing canvas

        max = startingValues.getX() - ticks.getX() / 5; // should start at 25
        aspectRatio = (WIDTH / 1080.0f) / (rescale.getX() / rescale.getY());

        xAxis = new DoubleLine(this, new FVector(startingValues.getX(), 0), new FVector(-startingValues.getX(), 0), 4, new Color(new Subcolor(255), new Subcolor(), new Subcolor(255), color.getAlpha()));
        yAxis = new DoubleLine(this, new FVector(0, startingValues.getY()), new FVector(0, -startingValues.getY()), 4, new Color(new Subcolor(255), new Subcolor(), new Subcolor(255), color.getAlpha()));

        frameCountInit = processing.frameCount;
        frameCountBuffer = 15;

        textColor = new Color(255, 0, 0); // start from black
        /* List<VObject> inits */
        xLines = new VObject[(int) (-4 * startingValues.getX() / ticks.getX())];
        yLines = new VObject[(int) (-4 * startingValues.getY() / ticks.getY())];
        xText = new TextVObject[xLines.length / 2]; // skip over 0
        yText = new TextVObject[yLines.length / 2]; // skip over 0
        println("xLength: " + xText.length + " yLength: " + yText.length); //9, 5 or 8, 4 :(
        // Assuming xLines.length > yLines.length

        canvas.textSize(42);

        for (int i = 0; i < xLines.length; i++){

            if (i != yLines.length/2 && i < yLines.length) { // Assumption takes place here
                if ((startingValues.getY() + ticks.getY() * i / 2) % ticks.getY() == 0) {
                    yLines[i] = new DoubleLine(this, new FVector(startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), new FVector(-startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), 4, color); // putting in the reference?
                    yText[i / 2] = new TextVObject(this, df.format(-startingValues.getY() - ticks.getY() * i / 2), new FVector(-12, scale.getY() * (startingValues.getY() + ticks.getY() * i / 2) - 12), textColor);
                    yText[i / 2].setTextAlign(RIGHT);
                    yText[i / 2].setDisplayRect(false);
                } else
                    yLines[i] = new DoubleLine(this, new FVector(startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), new FVector(-startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), 1.5f, color);
            }

            if (i != xLines.length / 2) {
                if ((startingValues.getX() + ticks.getX() * i / 2) % ticks.getX() == 0) {
                    xLines[i] = new DoubleLine(this, new FVector(startingValues.getX() + ticks.getX() * i / 2, startingValues.getY()), new FVector(startingValues.getX() + ticks.getX() * i / 2, -startingValues.getY()), 4, color);
                    xText[i / 2] = new TextVObject(this, df.format(startingValues.getX() + ticks.getX() * i / 2), new FVector(startingValues.getX() + ticks.getX() * i / 2 > 0 ? scale.getX() * (startingValues.getX() + ticks.getX() * i / 2) : scale.getX() * (startingValues.getX() + ticks.getX() * i / 2) - 8, 44), textColor);
                } else
                    xLines[i] = new DoubleLine(this, new FVector(startingValues.getX() + ticks.getX() * i / 2, startingValues.getY()), new FVector(startingValues.getX() + ticks.getX() * i / 2, -startingValues.getY()), 1.5f, color);
            }
        }
    }

    public CartesianPlane(PApplet p, FVector pos, IVector dimensions, FVector ticks) {
        this(p, pos, dimensions, ticks, new Color(150, 200, 255));
    }

    /**
     * Creates a 2D Cartesian Plane with an x axis and a y axis
     *
     * @return
     */
    public boolean generatePlane() {
        gridDrawn = true;
        currentColor = Useful.getColor(max, startingValues.getX(), -startingValues.getX());
        canvas.beginDraw();
        canvas.translate(canvas.width / 2.0f, canvas.height / 2.0f);
        canvas.background(0);
        canvas.textFont(myFont);
        canvas.textSize(38);
        canvas.textAlign(CENTER);
        canvas.rectMode(CENTER);
        canvas.colorMode(HSB);
        canvas.stroke(150, 200, 255);
        canvas.strokeWeight(4);

        for (int j = 0; j < yLines.length; j++) {
            if (j != yLines.length / 2)
                yLines[j].display();

        }

        //Cant make this loop more efficient because of line below...
        if (processing.frameCount < frameCountInit + frameCountBuffer) return false;

        for (int i = 0; i < xLines.length; i++) {
            if (i != xLines.length / 2)
                xLines[i].display();
        }

        canvas.stroke(0,0,255);
        canvas.strokeWeight(4);

        return xAxis.display() & yAxis.display();
        // return xAxisR.display() & yAxisU.display() & xAxisL.display() & yAxisD.display();
    }

    /**
     * Label all axes of the CartesianPlane by instantiating TextVObjects
     *
     * @return When the TextVObjects have completed displaying
     */
    public boolean labelAxes() {
        boolean hasCompleted = true;
        for (int i = 0; i < xText.length; i++) {
            if (i != xText.length / 2 && !xText[i].display()) {
                xText[i].setWidthHeight(60 + (xText[i].str.length() - 3) * 10, 56);
                hasCompleted = false;
            }

            if (i < yText.length && i != yText.length / 2 && !yText[i].display())
                hasCompleted = false;
        }
        return hasCompleted;
    }

    @Override
    public boolean scale(Scale s) {
        return false;
    }

    @Override
    public boolean display(Object... obj){
        //Is Object... obj because it can be default called or with 2 position args.
        System.out.println("Color: " + color);
        labelAxes();
        canvas.endDraw();

        processing.stroke(currentColor, 255, 255);
        processing.stroke(233);
        processing.strokeWeight(7);
        processing.noFill();
        processing.rect(pos.getX(), pos.getY(), canvas.width, canvas.height);
        processing.image(canvas, pos.getX(), pos.getY());
        return true;
        // popMatrix();
    }

    /**
     * Graph any function
     */
    public boolean graph() {
        return true;
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
        restrictedDomain.setXY(x1, x2);
        restrictDomain = true;
    }

    /**
     * @param theta Angle to be inputted
     *              Rotates the plane by theta degrees
     */
    public void rotatePlane(float theta) { // fix this shit later

        //    slowRotate.setChange(theta);
        //  slowRotate.incEase(1.045f); fix this shit later
        //slowRotate.doStuff();

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
     * TODO: Fix this shit
     */
    public void createPoint(float x, float y) {

        //points.add(new PVector(x, y));

        //   canvas.stroke(255,scaler.fadeIn(9));
        //  canvas.fill(255,scaler.getTransp());
        canvas.circle(x, y, 20);
    }

    /**
     * @param arrow Vector to be drawn
     *              Draws vector in Cartesian Space
     *              TODO: Fix this shit
     */
    public void drawVector(float arrow) { //no need to graph and that stuff, just show vector! (Used to be Arrow arrow)
        //  PVector v = arrow.vector; // aliases
        // arrow.drawArc(canvas);
        canvas.colorMode(HSB);
        //float triangleSize;
        canvas.pushMatrix();


        //    arrow.doStuff(1.045f);


        //    float triangleSize = arrow.triangleSize; // VAR CAN BE USED!
        //     canvas.strokeWeight(arrow.triangleSize * 10.0f/12);


        //   float rotationAngle = v.heading();
        //should draw Ellipse but is drawing circle (FIX FOR OTHER RES OF CANVAS)
        //   float magnitude = arrow.getMag(scale.getX(),aspectRatio); // max < 0 ? scale.getX()*v.mag() - 16 : scale.getX()*v.mag(); //arrow.vectorMag; // 6 works...?! apply ease to this v.mag() - 6

        canvas.stroke(currentColor, 255, 255);
        canvas.strokeCap(processing.ROUND);



        /* text */
        //   canvas.fill(Useful.getColor(arrow.coordsSize,0,delVal),255,255);

        //   if (processing.TAU + rotationAngle > 3*processing.PI/2 && rotationAngle < 0)
        //       Useful.rotatedText(df.format(degrees(rotationAngle > 0 ? rotationAngle : processing.TAU + rotationAngle))+"°",canvas,scale.getX()*v.x/4,-scale.getY()*v.y/4,processing.PI-rotationAngle);
        //    else
        //       Useful.rotatedText(df.format(degrees(rotationAngle > 0 ? rotationAngle : processing.TAU + rotationAngle))+"°",canvas,scale.getX()*v.x/4.75f,-scale.getY()*v.y/4.75f,processing.PI-rotationAngle);

        canvas.textSize(80);
        canvas.fill(255, 255, 255);
        //     Useful.rotatedText(Useful.propFormat(v.mag()),canvas,scale.getX()*v.x/2 ,-scale.getY()*v.y/2,processing.PI-rotationAngle);
        /* text */

        //   canvas.rotate(-rotationAngle);

        //    canvas.line(0,0,magnitude,0);

        canvas.beginShape(processing.TRIANGLES);
        //   canvas.vertex(magnitude - triangleSize*1.5f + 8,-triangleSize);
        //    canvas.vertex(magnitude + 8, 0);
        //    canvas.vertex(magnitude - triangleSize*1.5f + 8,triangleSize);
        //    canvas.endShape();
        // canvas.triangle(magnitude-triangleSize*1.6,triangleSize,magnitude-triangleSize*1.6,-triangleSize,magnitude,0);

        canvas.popMatrix();

        //    arrow.addPoint(scale.getX()*v.x,-scale.getY()*v.y);
        //   arrow.graph(canvas,delVal); //delVAL
        /* overlaying text */
        canvas.textSize(42);
        canvas.fill(128, 255, 255);
        //  if (processing.PI-rotationAngle < 3*processing.PI/2 && processing.PI-rotationAngle > processing.PI/2)
        canvas.textAlign(LEFT, CENTER);
        //   else
        canvas.textAlign(RIGHT, CENTER);
        //   canvas.text(String.format("[cos(%s),sin(%s)]",df.format(v.x),df.format(v.y)),1.09f*scale.getX()*v.x,1.09f*-scale.getY()*v.y);
        /* overlaying text */

        /* cherry on top */
        canvas.noStroke();
        //   canvas.fill(Useful.getColor(arrow.coordsSize,0,delVal),255,255);
        //    canvas.circle(scale.getX()*v.x,-scale.getY()*v.y,8);

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