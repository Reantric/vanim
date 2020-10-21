package vanim.shapes;

import org.apache.commons.math3.analysis.UnivariateFunction;
import vanim.root.builder.ParametricBuilder;
import vanim.root.builder.VObjectBuilder;
import vanim.root.modular.Graphable;
import vanim.root.modular.display.TwoArgDisplay;
import vanim.root.vobjects.VObject;
import vanim.storage.Bounds;
import vanim.storage.Point;
import vanim.storage.color.Color;
import vanim.util.Mapper;
import vanim.util.Useful;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PConstants.EPSILON;
import static processing.core.PConstants.MAX_FLOAT;
import static vanim.util.map.MapEase.EASE_IN;
import static vanim.util.map.MapEase.EASE_IN_OUT;
import static vanim.util.map.MapType.EXPONENTIAL;
import static vanim.util.map.MapType.QUADRATIC;

public abstract class Shape extends VObject implements Graphable, TwoArgDisplay<UnivariateFunction, UnivariateFunction> {

    protected List<Point> points = new ArrayList<>(); //[x,y]
    protected long maxPoints, newInterpVal = 0, prevInterpVal = 0;
    protected int speed, pointsSize;
    protected float secondMapInterp = MAX_FLOAT;
    protected float dividend; //  aka pointDistance
    protected Bounds boundT;

    /**
     * @param builder VObject builder needed to create this object
     * @param speed   How fast to draw this shape
     * @param bounds  The xy bounds of this shape (can be inferred if omitted given Points[])
     */
    public Shape(VObjectBuilder builder, int speed, Bounds bounds) {
        super(builder);
        this.speed = speed;
        dividend = 3 / geometricSpace.getScale().getX();
        this.boundT = bounds;
        maxPoints = (long) Math.ceil(boundT.getMag() / dividend) + 1;
        // Add 1 for the 0 case (if boundT.getLength() == 0) then there must be at least 1 point!
        /*
        Formula is: MaxPoints = Length/Dividend
         */
    } // One day add a constructor that takes in an SVG shape and converts to points

    public Shape(ParametricBuilder builder) {
        this(builder, builder.getSpeed(), builder.getBounds());
    }

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
        x *= geometricSpace.getScale().getX();
        y *= geometricSpace.getScale().getY();
        if (pointsSize < maxPoints) { // Optimization check originally delVal
            points.add(new Point(dimensions.getX() * x, -dimensions.getY() * y, color));
            pointsSize++;
        }
        return pointsSize < maxPoints;
    }

    public boolean addPoint(double x, double y) {
        return this.addPoint((float) x, (float) y, new Color());
    }

    public boolean addPoint(double x, double y, Color color) {
        return this.addPoint((float) x, (float) y, color);
    }


    public Shape setMaxPoints(long maxPoints) {
        this.maxPoints = maxPoints;
        this.dividend = boundT.getMag() / maxPoints;
        return this;
    }

    public Shape setDividend(float dividend) {
        this.dividend = dividend;
        this.maxPoints = (long) Math.ceil(boundT.getMag() / dividend) + 1;
        return this;
    }

    /**
     * TODO Fix this shit (fixed, i think)
     *
     * @param xt Parametric x equation
     * @param yt Parametric y equation
     * @return When the operation has completed showing
     */
    @Override
    public boolean display(UnivariateFunction xt, UnivariateFunction yt) {
        prevInterpVal = newInterpVal;
        newInterpVal = (long) Math.min(Math.ceil(Mapper.map2(incrementor, 0, maxPoints, 0, maxPoints, EXPONENTIAL, EASE_IN)), maxPoints);
        for (long i = prevInterpVal; i < newInterpVal; i++) { // +=speed
            addPoint(xt.value(i * dividend), yt.value(i * dividend), new Color(Useful.getColor(i, 0, maxPoints), color.getSaturation().getValue(),
                    color.getBrightness().getValue(), color.getAlpha().getValue())); //TODO: i/divider needs to be fixed!
        }

        if (secondMapInterp > 1 + EPSILON)
            secondMapInterp = Mapper.map2(incrementor, 0, 515f / 630 * maxPoints, 6.4f, 1, QUADRATIC, EASE_IN_OUT);
        else
            secondMapInterp = 1;
        incrementor += speed * secondMapInterp;

        if (incrementor >= maxPoints) {
            incrementor = maxPoints;
        }

        //  System.out.println(new FVector(incrementor,(float) newInterpVal,secondMapInterp));
        return graph();
    }

    /**
     * Graph the points in the coordinate array via iteration
     *
     * @return When the operation has completed
     */
    public boolean graph() {
        float xMult = scale.getX(), yMult = scale.getY();

        //TODO: Add color class to ClosedShape and its children and have Useful.getColor(i,0,delVal)
        // be a change to the hue and canvas.stroke(color);
        for (int i = 0; i < pointsSize - 1; i++) {
            canvas.stroke(points.get(i).getColor());
            canvas.strokeWeight(5);
            canvas.line(points.get(i).getX() * xMult, points.get(i).getY() * yMult, points.get(i + 1).getX() * xMult, points.get(i + 1).getY() * yMult);
        }

        return pointsSize == maxPoints; // might not be necessary because of addPoints() bool result!
    }

    @Override
    public boolean fadeOut() {
        boolean isCompleted = false;
        for (Point p : points) {
            if (p.fadeOut())
                isCompleted = true;
        }
        return isCompleted & super.fadeOut();//points.forEach(Colorable::fadeOut);
    }

    @Override
    public boolean fadeIn() {
        boolean isCompleted = false;
        for (Point p : points) {
            if (p.fadeIn())
                isCompleted = true;
        }
        return isCompleted & super.fadeIn(); //points.forEach(Colorable::fadeIn);
    }
}
