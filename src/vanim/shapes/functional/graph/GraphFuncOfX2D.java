package vanim.shapes.functional.graph;

import org.apache.commons.math3.analysis.UnivariateFunction;
import vanim.planes.Plane;
import vanim.root.exception.ImpossibleBoundsException;
import vanim.root.exception.IncorrectBoundsException;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

public class GraphFuncOfX2D extends Graph2D {

    public GraphFuncOfX2D(Plane p, FVector bounds, UnivariateFunction y, int speed, Color color, Reason reasonCreated) throws IncorrectBoundsException, ImpossibleBoundsException {
        super(p, bounds, t -> t, y, speed, color, reasonCreated);
    }

    public GraphFuncOfX2D(Plane p, FVector bounds, UnivariateFunction y) throws IncorrectBoundsException, ImpossibleBoundsException {
        super(p, bounds, t -> t, y, 3, new Color(0), Reason.USER_CREATED);
    }
}
