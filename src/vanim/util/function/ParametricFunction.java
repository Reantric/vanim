package vanim.util.function;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import vanim.storage.Point;

public class ParametricFunction {
    UnivariateDifferentiableFunction xt, yt;
    FiniteDifferencesDifferentiator d = new FiniteDifferencesDifferentiator(5, 0.01);

    public ParametricFunction(UnivariateFunction xt, UnivariateFunction yt) {
        this.xt = d.differentiate(xt);
        this.yt = d.differentiate(yt);
    }

    public UnivariateDifferentiableFunction getParametricX() {
        return xt;
    }

    public UnivariateDifferentiableFunction getParametricY() {
        return yt;
    }

    public Point value(double t) {
        return new Point(xt.value(t), yt.value(t));
    }
}
