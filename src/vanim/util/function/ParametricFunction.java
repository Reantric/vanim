package vanim.util.function;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator;
import vanim.storage.Point;

public class ParametricFunction {
    UnivariateFunction xt, yt;
    FiniteDifferencesDifferentiator differentiator = new FiniteDifferencesDifferentiator(5, 0.01);

    public ParametricFunction(UnivariateFunction xt, UnivariateFunction yt) {
        this.xt = xt;
        this.yt = yt;
    }

    public UnivariateFunction getParametricX() {
        return xt;
    }

    public UnivariateFunction getParametricY() {
        return yt;
    }

    public UnivariateFunction setParametricX(UnivariateFunction xt) {
        this.xt = xt;
        return this.xt;
    }

    public UnivariateFunction setParametricY(UnivariateFunction yt) {
        this.xt = yt;
        return this.yt;
    }

    public UnivariateFunction multiplyParametricX(UnivariateFunction x1t) {
        this.xt = t -> x1t.value(t) * xt.value(t);
        return this.xt;
    }

    public UnivariateFunction multiplyParametricY(UnivariateFunction y1t) {
        this.yt = t -> y1t.value(t) * yt.value(t);
        return this.yt;
    }

    public Point value(double t) {
        return new Point(xt.value(t), yt.value(t));
    }
}
