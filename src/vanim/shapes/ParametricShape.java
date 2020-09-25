package vanim.shapes;

import vanim.root.builder.VObjectBuilder;
import vanim.storage.Bounds;
import vanim.util.function.ParametricFunction;

/**
 * @author protonlaser91
 */
public abstract class ParametricShape extends Shape implements Parameterizable { // Maybe in the far far future when i add 3D call this ClosedShape2D

    protected float incrementTangentLine = 0;
    protected float distance; // Soon make this Line tangentLine;
    protected ParametricFunction function;


    /**
     * @param builder VObject builder needed to create this object
     * @param speed   How fast to draw this shape
     * @param bounds  The xy bounds of this shape (can be inferred if omitted given Points[])
     */
    public ParametricShape(VObjectBuilder builder, int speed, Bounds bounds, ParametricFunction function) {
        super(builder, speed, bounds);
        this.function = function;
    }

    //Constructor with Points[], Bounds.createExplicitBounds(points.min,points.max);
}
