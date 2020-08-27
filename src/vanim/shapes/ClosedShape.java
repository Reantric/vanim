package vanim.shapes;

import vanim.planes.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

/**
 * @author protonlaser91
 */
public class ClosedShape extends Shape { // Maybe in the far far future when i add 3D call this ClosedShape2D

    protected float incrementTangentLine = 0;
    protected float distance; // Soon make this Line tangentLine;


    /**
     * @param p             Plane that is to be drawn on
     * @param pos           The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions    The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param speed         How fast the circle should be drawn. 1 is 1/TAU points every tick
     * @param reasonCreated The reason this object was created
     */
    public ClosedShape(Plane p, FVector pos, FVector dimensions, int speed, Color color, Reason reasonCreated) {
        super(p, pos, dimensions, speed, color, reasonCreated);
    }

    public ClosedShape(Plane p, FVector pos, FVector dimensions, int speed, Reason reasonCreated) {
        super(p, pos, dimensions, speed, new Color(0), reasonCreated);
        this.speed = speed;
    }

    public ClosedShape(Plane p, FVector pos, FVector dimensions, int speed) {
        this(p, pos, dimensions, speed, new Color(0), Reason.USER_CREATED);
    }

    /**
     * Graph the points in the coordinate array via iteration
     * @return When the operation has completed
     */
    @Override
    public boolean graph() {

        /* Works for all closed shapes
        boolean epsX = coords.get(0)[0] / coords.get(coordsSize - 1)[0] > 0.99f && coords.get(0)[0] / coords.get(coordsSize - 1)[0] < 1.01f; // x does not start/end at 0 !
        boolean epsY = coordsSize > 1 && coords.get(coordsSize - 2)[1] > coords.get(0)[1] && coords.get(coordsSize - 1)[1] <= coords.get(0)[1];


         if (epsX && epsY) {
            optimalDelVal = coordsSize;
            println("Optimal delVal: " + optimalDelVal); //<-- reenable
        } */

        return super.graph();
    }

}
