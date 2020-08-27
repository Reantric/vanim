package vanim.storage;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import vanim.root.exception.ImpossibleBoundsException;
import vanim.storage.vector.FVector;

public class Bounds extends Vector<Float> {
    static BrentSolver solver = new BrentSolver();

    public Bounds(float bound1, float bound2, UnivariateFunction function) throws ImpossibleBoundsException {
        super((Float) null);
        try {
            x = (float) solver.solve(Integer.MAX_VALUE, t -> bound1 - function.value(t), -Integer.MAX_VALUE, Integer.MAX_VALUE);
            y = (float) solver.solve(Integer.MAX_VALUE, t -> bound2 - function.value(t), -Integer.MAX_VALUE, Integer.MAX_VALUE);
        } catch (NoBracketingException e) {
            throw new ImpossibleBoundsException(e.toString());
        }
    }

    public Bounds(FVector boundsX, UnivariateFunction xt) throws ImpossibleBoundsException {
        this(boundsX.getX(), boundsX.getY(), xt);
    }
}
