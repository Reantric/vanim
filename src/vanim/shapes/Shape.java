package vanim.shapes;

import org.apache.commons.math3.analysis.UnivariateFunction;
import vanim.planes.Plane;
import vanim.root.modular.Graphable;
import vanim.root.modular.display.TwoArgDisplay;
import vanim.root.vobjects.VObject;
import vanim.storage.Color;
import vanim.storage.Point;
import vanim.storage.vector.FVector;
import vanim.util.Mapper;
import vanim.util.Reason;
import vanim.util.Useful;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;
import static processing.core.PConstants.*;
import static vanim.util.map.MapEase.EASE_IN;
import static vanim.util.map.MapEase.EASE_IN_OUT;
import static vanim.util.map.MapType.EXPONENTIAL;
import static vanim.util.map.MapType.QUADRATIC;

public abstract class Shape extends VObject implements Graphable, TwoArgDisplay<UnivariateFunction, UnivariateFunction> {

    protected List<Point> coords = new ArrayList<>(); //[x,y]
    protected long maxPoints;
    protected int speed;
    protected float secondMapInterp = MAX_FLOAT;
    protected double newInterpVal = 0, prevInterpVal = 0, divider;

    /**
     * @param p             Plane that is to be drawn on
     * @param pos           The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions    The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param color         The color of the object, in HSB
     * @param reasonCreated
     */
    public Shape(Plane p, FVector pos, FVector dimensions, int speed, Color color, Reason reasonCreated) {
        super(p, pos, dimensions, color, reasonCreated);
        this.speed = speed;
        divider = plane.getScale().getX() / 3;
        maxPoints = round(TAU * divider / 10) * 10; // rounds to nearest 10
    } // One day add a constructor that takes in an SVG shape and converts to points

    public long getPoints() {
        return this.maxPoints;
    }

    /**
     * @param x X-coordinate of the point to be added to the coordinates list
     * @param y Y-coordinate of the point to be added to the coordinates list
     * @return When the operation has successfully added the required number of points
     */
    @Override
    public boolean addPoint(float x, float y, Color color) {
        if (coordsSize < maxPoints) { // Optimization check originally delVal
            coords.add(new Point(dimensions.getX() * x, -dimensions.getY() * y, color));
            coordsSize++;
        }
        return coordsSize < maxPoints;
    }

    public boolean addPoint(double x, double y) {
        return this.addPoint((float) x, (float) y, new Color());
    }

    public boolean addPoint(double x, double y, Color color) {
        return this.addPoint((float) x, (float) y, color);
    }


    public Shape setMaxPoints(long maxPoints) {
        this.maxPoints = maxPoints;
        return this;
    }

    /**
     * TODO Fix this shit
     *
     * @param xt Parametric x equation
     * @param yt Parametric y equation
     * @return When the operation has completed showing
     */

    @Override
    public boolean display(UnivariateFunction xt, UnivariateFunction yt) {
        prevInterpVal = newInterpVal;
        newInterpVal = Mapper.map2(incrementor, 0, maxPoints, 0, maxPoints, EXPONENTIAL, EASE_IN);
        for (long i = (long) Math.ceil(prevInterpVal); i <= maxPoints && i < newInterpVal; i++) { // +=speed
            addPoint(xt.value(i / divider), yt.value(i / divider), new Color(Useful.getColor(i, 0, maxPoints), color.getSaturation().getValue(),
                    color.getBrightness().getValue(), color.getAlpha().getValue()));
            // div by 100 because incrementor is of type long
        }

        if (secondMapInterp > 1 + EPSILON)
            secondMapInterp = Mapper.map2(incrementor, 0, 515, 6.4f, 1, QUADRATIC, EASE_IN_OUT);
        else
            secondMapInterp = 1;
        incrementor += speed * secondMapInterp;

        if (incrementor >= maxPoints) {
            incrementor = maxPoints;
        }

        //  System.out.println(new FVector(incrementor,(float) newInterpVal,secondMapInterp));
        return graph();
    }

    public boolean graph() {
        float xMult = scale.getX(), yMult = scale.getY();

        //TODO: Add color class to ClosedShape and its children and have Useful.getColor(i,0,delVal)
        // be a change to the hue and canvas.stroke(color);
        for (int i = 0; i < coordsSize - 1; i++) {
            canvas.stroke(coords.get(i).getColor());
            canvas.strokeWeight(5);
            canvas.line(coords.get(i).getX() * xMult, coords.get(i).getY() * yMult, coords.get(i + 1).getX() * xMult, coords.get(i + 1).getY() * yMult);
        }

        return coordsSize == maxPoints; // might not be necessary because of addPoints() bool result!
    }
}
