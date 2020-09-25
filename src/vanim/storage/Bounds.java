package vanim.storage;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import vanim.root.exception.ImpossibleBoundsException;
import vanim.root.exception.IncorrectBoundsException;
import vanim.storage.vector.FVector;

public class Bounds extends FVector {
    static BrentSolver solver = new BrentSolver();

    public Bounds(float bound1, float bound2, UnivariateFunction function) throws ImpossibleBoundsException, IncorrectBoundsException {
        super(0);
        if (bound1 > bound2) {
            throw new IncorrectBoundsException("First bound must be less than second");
        }
        try {
            x = (float) solver.solve(Integer.MAX_VALUE, t -> bound1 - function.value(t), -Integer.MAX_VALUE, Integer.MAX_VALUE);
            y = (float) solver.solve(Integer.MAX_VALUE, t -> bound2 - function.value(t), -Integer.MAX_VALUE, Integer.MAX_VALUE);
        } catch (NoBracketingException e) {
            throw new ImpossibleBoundsException(e.toString());
        }
    }

    public Bounds(FVector bounds, UnivariateFunction xt) throws ImpossibleBoundsException, IncorrectBoundsException {
        this(bounds.getX(), bounds.getY(), xt);
    }

    public static Bounds createExplicitBounds(float bound1, float bound2) {
        try {
            return new Bounds(bound1, bound2, t -> t);
        } catch (ImpossibleBoundsException | IncorrectBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bounds createExplicitBounds(FVector boundX) {
        return createExplicitBounds(boundX.getX(), boundX.getY());
    }

    @Override
    public float getMag() {
        return y - x;
    }
}
