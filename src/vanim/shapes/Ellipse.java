package vanim.shapes;

import vanim.root.builder.LineBuilder;
import vanim.root.builder.VObjectBuilder;
import vanim.storage.Bounds;
import vanim.storage.Point;
import vanim.storage.Scale;
import vanim.storage.color.Color;
import vanim.storage.vector.FVector;
import vanim.util.Mapper;
import vanim.util.function.ParametricFunction;

import static processing.core.PApplet.sqrt;
import static processing.core.PConstants.PI;
import static vanim.util.map.MapEase.EASE_IN;
import static vanim.util.map.MapType.QUADRATIC;

/**
 * @author protonlaser91
 */
public class Ellipse extends ParametricShape {
    Line tangentLine;

    /**
     * Ellipse constructor: Bounds are given as [0,2PI]
     *
     * @param builder VObject builder needed to create this object
     * @param speed   How fast to draw this shape
     */
    public Ellipse(VObjectBuilder builder, int speed) {
        super(builder, speed, Bounds.createExplicitBounds(0, 2 * PI), new ParametricFunction(t -> builder.getDimensions().getX() * Math.cos(t), t -> builder.getDimensions().getY() * Math.sin(t)));
        distance = (scale.getY() * dimensions.getY() + scale.getX() * dimensions.getX()) * 0.5f;
        System.out.println("STOP: " + distance);
        //300*0.9 = 270 which IS the distance
        tangentLine = new DoubleLine(new LineBuilder(geometricSpace, new FVector(), new FVector(), 5, new Color(30, 255, 255, 255))).setDividend(2);
    }

    /**
     * Draw a tangent line to the ellipse
     *
     * @param t        Parametric value of t to evaluate the functions at
     * @param isMoving If the tangent line return true while it's being created or after it has been fully created
     * @return When the tangent line is fully created or true (isMoving)
     */
    public boolean drawTangentLine(float t, boolean isMoving) { //TODO: try generalizing
        // Maybe add interface to Point that takes care of scale() and add()
        Point point = function.value(t).scale(scale);

        float newDist = Mapper.map2(incrementTangentLine, 0, distance, 0, distance, QUADRATIC, EASE_IN);
        if (newDist > distance)
            newDist = distance;

        float common = newDist / (2 * sqrt((float) (1 + (Math.pow(dimensions.getY(), 4) * point.getX() * point.getX()) / (Math.pow(dimensions.getX(), 4) * point.getY() * point.getY()))));
        // common = distance / (2 * sqrt((float) (1 + (Math.pow(dimensions.getY(), 4) * plane.getScale().getX() * x * plane.getScale().getX() * x) / (Math.pow(dimensions.getX(), 4) * plane.getScale().getY() * y * plane.getScale().getY() * y))));
        float x1 = common + point.getX(); //+
        float y1 = (-point.getX() * dimensions.getY() * dimensions.getY()) / (point.getY() * dimensions.getX() * dimensions.getX()) * common + (point.getY()); //+
        float x2 = -common + (point.getX()); //-
        float y2 = (point.getX() * dimensions.getY() * dimensions.getY()) / (point.getY() * dimensions.getX() * dimensions.getX()) * common + (point.getY()); //-

        // float common = newDist / (2 * sqrt((float) (1 + (Math.pow(dimensions.getY(), 4) * plane.getScale().getX() * point.getX()* plane.getScale().getX() * point.getX()) / (Math.pow(dimensions.getX(), 4) * plane.getScale().getY() * point.getY()* plane.getScale().getY() * point.getY()))));
//        // common = distance / (2 * sqrt((float) (1 + (Math.pow(dimensions.getY(), 4) * plane.getScale().getX() * point.getX()* plane.getScale().getX() * x) / (Math.pow(dimensions.getX(), 4) * plane.getScale().getY() * point.getY()* plane.getScale().getY() * y))));
//        float x1 = common + (point.getX()* plane.getScale().getX()); //+
//        float y1 = (-point.getX()* dimensions.getY() * dimensions.getY()) / (point.getY() * dimensions.getX() * dimensions.getX()) * common + (point.getY()* plane.getScale().getY()); //+
//        float x2 = -common + (point.getX()* plane.getScale().getX()); //-
//        float y2 = (point.getX()* dimensions.getY() * dimensions.getY()) / (point.getY() * dimensions.getX() * dimensions.getX()) * common + (point.getY()* plane.getScale().getY()); //-

        canvas.strokeWeight(5);
        canvas.stroke(30, 255, 255, color.getAlpha().getValue()); // sadness
        if (Float.isNaN(y1) || Float.isNaN(y2)) {
            y1 = distance / 2;
            y2 = -y1;
        }

        //TODO: fix later
        // tangentLine.setStart(x1,y1).setEnd(x2,y2);
        // tangentLine.display();
        //System.out.println("TL: " + tangentLine.getDimensions());
        //System.out.println("XX: " + new FVector(x2-x1,y2-y1));
        float scaleX = geometricSpace.getScale().getX();
        float scaleY = geometricSpace.getScale().getY();

        canvas.line(scaleX * x1, scaleY * y1, scaleX * x2, scaleY * y2);

        incrementTangentLine += distance / 50.0f;
        return isMoving || (newDist / distance > 0.99 && newDist / distance < 1.01);
    }

    public boolean drawTangentLine(float t) {
        return this.drawTangentLine(t, false);
    }

    /**
     * @param s The new plane.getScale() object that will replace the original plane.getScale() object.
     *          TODO: Might make these void, they serve no purpose
     */
    @Override
    public Ellipse scale(Scale s) { //Instant scaling!, is Absolute! Not relative!
        super.scale(s);
        distance = Math.min((dimensions.getX() / scale.getX() + dimensions.getY() / scale.getY()), (dimensions.getX() + dimensions.getY()) * 0.5f);
        return this;
    }

    @Override
    public boolean display() {
        return super.display(Math::cos, Math::sin);
    }
}
