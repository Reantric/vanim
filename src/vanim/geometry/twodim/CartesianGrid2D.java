package vanim.geometry.twodim;

import processing.core.PVector;
import vanim.root.builder.GeometricSpaceBuilder;
import vanim.root.builder.LineBuilder;
import vanim.root.builder.TextBuilder;
import vanim.shapes.DoubleLine;
import vanim.shapes.Line;
import vanim.storage.Scale;
import vanim.storage.color.Color;
import vanim.storage.color.Subcolor;
import vanim.storage.vector.FVector;
import vanim.text.Text;
import vanim.util.Reason;
import vanim.util.Useful;
import vanim.util.map.MapType;

import static vanim.planar.*;


public final class CartesianGrid2D extends Grid2D { // Work on mouseDrag after!

    FVector startingValues = new FVector();
    float max; //counter
    float aspectRatio;
    boolean frameWait;
    /* Object initializations */  // Create color object soon for animateVector();
    public Line xAxis, yAxis;
    public Line[] xLines, yLines;
    public Text[] xText, yText;
    /* Object initializations */

    /**
     * @param builder The necessary builder to construct this Plane
     *                Check the GeometricSpaceBuilder to know the params involved
     */
    public CartesianGrid2D(GeometricSpaceBuilder builder) {
        super(builder);
        startingValues.setX((float) Useful.floorAny(-canvas.width / (2 * scale.getX()), ticks.getX())); // <---- Issues here when resizing canvas
        startingValues.setY((float) Useful.floorAny(-canvas.height / (2 * scale.getY()), ticks.getY())); // <---- Issues here when resizing canvas

        max = startingValues.getX() - ticks.getX() / 5; // should start at 25
        aspectRatio = (WIDTH / 1080.0f) / (rescale.getX() / rescale.getY());

        xAxis = new DoubleLine(new LineBuilder(this, new FVector(startingValues.getX(), 0), new FVector(-startingValues.getX(), 0), 4, new Color(new Subcolor(255), new Subcolor(), new Subcolor(255), color.getAlpha()), Reason.PLANE_GENERATED));
        yAxis = new DoubleLine(new LineBuilder(this, new FVector(0, startingValues.getY()), new FVector(0, -startingValues.getY()), 4, new Color(new Subcolor(255), new Subcolor(), new Subcolor(255), color.getAlpha()), Reason.PLANE_GENERATED));

        frameCountInit = processing.frameCount;
        frameCountBuffer = 15;

        textColor = new Color(255, 0, 255, 0); // start from black
        /* List<VObject> inits */
        xLines = new Line[(int) (-4 * startingValues.getX() / ticks.getX())];
        yLines = new Line[(int) (-4 * startingValues.getY() / ticks.getY())];
        xText = new Text[xLines.length / 2]; // skip over 0
        yText = new Text[yLines.length / 2]; // skip over 0
        println("xLength: " + xText.length + " yLength: " + yText.length); //9, 5 or 8, 4 :(
        // Assuming xLines.length > yLines.length

        canvas.textSize(42);

        for (int i = 0; i < xLines.length; i++) {

            if (i != yLines.length / 2 && i < yLines.length) { // Assumption takes place here
                if ((startingValues.getY() + ticks.getY() * i / 2) % ticks.getY() == 0) {
                    yLines[i] = new DoubleLine(new LineBuilder(this, new FVector(startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), new FVector(-startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), 4, color, Reason.PLANE_GENERATED)); // putting in the reference?
                    yText[i / 2] = new Text(new TextBuilder(this, df.format(-startingValues.getY() - ticks.getY() * i / 2), new FVector(-12, scale.getY() * (startingValues.getY() + ticks.getY() * i / 2) - 12), textColor, Reason.PLANE_GENERATED))
                            .setTextAlign(RIGHT).setDisplayRect(false).setInit(false);
                } else
                    yLines[i] = new DoubleLine(new LineBuilder(this, new FVector(startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), new FVector(-startingValues.getX(), startingValues.getY() + ticks.getY() * i / 2), 1.5f, color, Reason.PLANE_GENERATED));
            }

            if (i != xLines.length / 2) {
                if ((startingValues.getX() + ticks.getX() * i / 2) % ticks.getX() == 0) {
                    xLines[i] = new DoubleLine(new LineBuilder(this, new FVector(startingValues.getX() + ticks.getX() * i / 2, startingValues.getY()), new FVector(startingValues.getX() + ticks.getX() * i / 2, -startingValues.getY()), 4, color, Reason.PLANE_GENERATED));
                    xText[i / 2] = new Text(new TextBuilder(this, df.format(startingValues.getX() + ticks.getX() * i / 2), new FVector(startingValues.getX() + ticks.getX() * i / 2 > 0 ? scale.getX() * (startingValues.getX() + ticks.getX() * i / 2) : scale.getX() * (startingValues.getX() + ticks.getX() * i / 2) - 8, 44), textColor, Reason.PLANE_GENERATED)).setInit(false);
                } else
                    xLines[i] = new DoubleLine(new LineBuilder(this, new FVector(startingValues.getX() + ticks.getX() * i / 2, startingValues.getY()), new FVector(startingValues.getX() + ticks.getX() * i / 2, -startingValues.getY()), 1.5f, color, Reason.PLANE_GENERATED));
            }
        }
    }

    /**
     * Creates a 2D Cartesian Plane with an x axis and a y axis
     *
     * @return When the plane is fully generated
     */
    @Override
    public boolean generateSpace() {
        boolean gridInit = true;
        frameWait = processing.frameCount > frameCountInit + frameCountBuffer;
        super.generateSpace();
        // maybe move all of this to the plane.super() ?
        canvas.stroke(150, 200, 255);
        canvas.strokeWeight(4);

        for (int j = 0; j < yLines.length; j++) {
            if (j != yLines.length / 2 && !yLines[j].display())
                gridInit = false;
        }

        //Cant make this loop more efficient because of line below...
        if (!frameWait) return false;

        for (int i = 0; i < xLines.length; i++) {
            if (i != xLines.length / 2 && !xLines[i].display())
                gridInit = false;
        }

        canvas.stroke(0, 0, 255);
        canvas.strokeWeight(4);

        return gridInit & xAxis.display() & yAxis.display();
        // return xAxisR.display() & yAxisU.display() & xAxisL.display() & yAxisD.display();
    }

    /**
     * TODO
     *
     * @param theta
     */
    @Override
    protected CartesianGrid2D rotatePlane(float theta) {
        return null;
    }

    /**
     * TODO
     *
     * @param initial
     * @param output
     */
    @Override
    protected boolean moveVector(PVector initial, PVector output) {
        return false;
    }

    /**
     * Label all axes of the CartesianPlane by instantiating TextVObjects
     *
     * @return When the TextVObjects have completed displaying
     */
    public boolean labelAxes() {
        boolean hasCompleted = true;

        if (textInit) { // Interpolating done here instead of Text class!
            textColor.getAlpha().interpolate(255, MapType.QUADRATIC, 0.3f);
            textInit = !textColor.getAlpha().is255();
        }

        for (int i = 0; i < xText.length; i++) {
            if (i != xText.length / 2 && !xText[i].display()) {
                xText[i].setDimensions(60 + (xText[i].str.length() - 3) * 10, 56);
                hasCompleted = false;
            }

            if (i < yText.length && i != yText.length / 2 && !yText[i].display())
                hasCompleted = false;
        }
        return textInit & hasCompleted;
    }

    /**
     * TODO: Scale cartesian!
     *
     * @param s The new Scale object that will replace the original Scale object.
     * @return
     */
    @Override
    public CartesianGrid2D scale(Scale s) {
        return this;
    }

    @Override
    public boolean displayHelper(Object... obj) {
        //   generatePlane();
        //Is Object... obj because it can be default called or with 2 position args.
        // System.out.println("Color: " + color);
        if (frameWait)
            labelAxes();

        canvas.stroke(color.getHue().getValue(), 255, 255);
        canvas.stroke(233);
        canvas.strokeWeight(7);
        canvas.noFill(); // <--- might not be necessary
        canvas.rect(pos.getX(), pos.getY(), canvas.width, canvas.height);
        canvas.endDraw();
        //processing.image(canvas, pos.getX(), pos.getY()); // <--- Do all method!
        //texts.forEach((k, v) -> v = k.display());
        return true;
        // popMatrix();
    }

}