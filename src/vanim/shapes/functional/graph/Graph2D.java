package vanim.shapes.functional.graph;

import org.apache.commons.math3.analysis.UnivariateFunction;
import vanim.root.builder.ParametricBuilder;
import vanim.root.exception.ImpossibleBoundsException;
import vanim.root.exception.IncorrectBoundsException;
import vanim.shapes.Parameterizable;
import vanim.shapes.Shape;
import vanim.storage.vector.FVector;

public class Graph2D extends Shape implements Parameterizable {
    UnivariateFunction xt, yt;

    // Convert x bounds to "t" bounds!
    // Usually bounds is axes
    public Graph2D(ParametricBuilder builder) throws IncorrectBoundsException, ImpossibleBoundsException {
        super(builder);
        this.xt = builder.getParametricX();
        this.yt = builder.getParametricY();

        //boundY = new Bounds(-4 * p.getAxes().getY(), 4 * p.getAxes().getY(), yt);
        FVector vec = plane.getAxes();
        super.setDividend((vec.getX() * vec.getY()) / 50.0f); // idk about this one brudda FIX (boundasTX.getLength()/maxPoints)
        System.out.println(new FVector(maxPoints, dividend));
        System.out.println("bounds: " + boundT);
    }


    /**
     * Graph the following parametric equations over the respective interval
     *
     * @return When the operation has completed showing
     */
    @Override
    public boolean display() {
        return super.display(xt, yt);
    }
}