package vanim.shapes.functional.graph;

import org.apache.commons.math3.analysis.UnivariateFunction;
import vanim.planes.Plane;
import vanim.root.exception.ImpossibleBoundsException;
import vanim.root.exception.IncorrectBoundsException;
import vanim.shapes.OpenShape;
import vanim.storage.Bounds;
import vanim.storage.Color;
import vanim.storage.Point;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

import java.util.ArrayList;
import java.util.List;

public class Graph2D extends OpenShape {
    UnivariateFunction xt, yt;
    Plane p;
    List<Point> points = new ArrayList<>();
    float pointDistance; // I suppose, it depends doesn't it?
    float incrementor;
    Bounds boundX, boundY;

    // Convert x bounds to "t" bounds!
    // Usually bounds is axes
    public Graph2D(Plane p, FVector boundsX, UnivariateFunction xt, UnivariateFunction yt, int speed, Color color, Reason reasonCreated) throws IncorrectBoundsException, ImpossibleBoundsException {
        super(p, new FVector(), boundsX, speed, color, reasonCreated);
        this.xt = xt;
        this.yt = yt;
        this.p = p;
        if (boundsX.getX() > boundsX.getY())
            throw new IncorrectBoundsException("First bound must be less than second");

        boundX = new Bounds(boundsX, xt);
        boundY = new Bounds(-4 * p.getAxes().getY(), 4 * p.getAxes().getY(), yt);

        FVector vec = p.getDimensions();
        pointDistance = (vec.getX() * vec.getY()) / 50; // idk about this one brudda
        incrementor = dimensions.getX(); // get lowerbound
    }

    public Graph2D(Plane p, UnivariateFunction xt, UnivariateFunction yt, int speed, Color color) throws IncorrectBoundsException, ImpossibleBoundsException {
        this(p, new FVector(-4 * p.getAxes().getX(), 4 * p.getAxes().getX()), xt, yt, speed, color, Reason.USER_CREATED);
    }

    @Override
    public boolean display(Object... obj) {
        //  for (long i = (long) Math.ceil(prevInterpVal); i <= maxPoints && i < newInterpVal; i++) { // +=speed
        //        super.addPoint(cos(i / 100f), sin(i / 100f));
        //        // div by 100 because incrementor is of type long
        //    }

        return graph();
    }
}