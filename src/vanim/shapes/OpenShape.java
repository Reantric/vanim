package vanim.shapes;

import vanim.planes.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

public class OpenShape extends Shape { // maybe later ill make this openSHape2d :D
    int speed;

    /**
     * @param p             Plane that is to be drawn on
     * @param pos           The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions    The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param speed         How fast the circle should be drawn. 1 is 1/TAU points every tick
     * @param reasonCreated The reason this object was created
     */
    public OpenShape(Plane p, FVector pos, FVector dimensions, int speed, Color color, Reason reasonCreated) {
        super(p, pos, dimensions, speed, color, reasonCreated);
    }

}
