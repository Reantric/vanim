package vanim.root.builder;

import org.apache.commons.math3.analysis.UnivariateFunction;
import vanim.geometry.twodim.Plane;
import vanim.root.exception.ImpossibleBoundsException;
import vanim.root.exception.IncorrectBoundsException;
import vanim.storage.Bounds;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

public class ParametricBuilder extends VObjectBuilder {
    Bounds bounds;
    UnivariateFunction xt, yt;
    int speed;

    public ParametricBuilder(Plane p, FVector boundX, UnivariateFunction xt, UnivariateFunction yt, int speed, Color color, Reason reasonCreated) {
        super(p, new FVector(), boundX, color, reasonCreated);
        try {
            bounds = new Bounds(boundX, xt);
        } catch (ImpossibleBoundsException | IncorrectBoundsException e) {
            e.printStackTrace();
        }
        this.xt = xt;
        this.yt = yt;
        this.speed = speed;
    }

    public ParametricBuilder(Plane p, UnivariateFunction xt, UnivariateFunction yt, int speed, Color color, Reason reasonCreated) {
        this(p, new FVector(p.getAxes()).scale(-2f), xt, yt, speed, color, reasonCreated);
    }

    public ParametricBuilder(Plane p, UnivariateFunction xt, UnivariateFunction yt, int speed, Color color) {
        this(p, new FVector(p.getAxes()).scale(-2f), xt, yt, speed, color, Reason.USER_CREATED);
    }

    public Bounds getBounds() {
        return bounds;
    }

    public UnivariateFunction getParametricX() {
        return xt;
    }

    public UnivariateFunction getParametricY() {
        return yt;
    }

    public int getSpeed() {
        return speed;
    }
}
