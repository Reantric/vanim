package vanim.shapes;

import vanim.planes.Plane;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

/**
 * @author protonlaser91
 */
public class Circle extends Ellipse {

    /**
     * @param p      Plane that is to be drawn on
     * @param pos    The position of the object on that plane (in scaled coordinates, not absolute)
     * @param radius The radius of the circle (in scaled coordinates)
     * @param speed  How fast the circle should be drawn. 1 is 1/TAU points every tick
     */
    public Circle(Plane p, FVector pos, float radius, int speed, Reason reasonCreated) {
        super(p, pos, new FVector(radius, radius), speed, reasonCreated);
    }

    public Circle(Plane p, FVector pos, float radius, int speed) {
        this(p, pos, radius, speed, Reason.USER_CREATED);
    }

    public Circle(Plane p, FVector pos, float radius) {
        this(p, pos, radius, 4, Reason.USER_CREATED);
    }

    /**
     * @return Radius of the circle, in scaled coordinates.
     */
    public float getRadius() {
        //width and height are same in Circle, depends on sX and sY
        return dimensions.getX() * scale.getX() / absScale.getX();
    }
}

