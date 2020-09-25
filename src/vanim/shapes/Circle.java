package vanim.shapes;

import vanim.root.builder.VObjectBuilder;

/**
 * @author protonlaser91
 */
public class Circle extends Ellipse {

    /**
     * Circle constructor: Bounds are given as [0,2PI]
     *
     * @param builder VObject builder needed to create this object
     * @param speed   How fast to draw this shape
     */
    public Circle(VObjectBuilder builder, int speed) {
        super(builder, speed);
    }

    /**
     * @return Radius of the circle, in scaled coordinates.
     */
    public float getRadius() {
        //width and height are same in Circle
        return dimensions.getX() * scale.getX();
    }
}

