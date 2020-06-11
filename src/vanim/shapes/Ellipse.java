package vanim.shapes;

import vanim.planes.Plane;
import vanim.storage.FVector;
import vanim.util.Mapper;
import vanim.storage.Scale;
import vanim.storage.Vector;

import java.util.*;

import static processing.core.PApplet.*;

/**
 * @author protonlaser91
 */
public class Ellipse extends ClosedShape {

    private static final List<Ellipse> allObjects = new ArrayList<>();

    /**
     *
     * @param p Plane that is to be drawn on
     * @param pos The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     * @param speed How fast the circle should be drawn. 1 is 1/TAU points every tick
     * @param delVal How fast the circle should be colored in per tick. After delVal ticks, the color wheel will
     *               have reached the beginning.
     */
    public Ellipse(Plane p, FVector pos, FVector dimensions, int speed, int delVal) {
        super(p,pos,dimensions,speed,delVal);
        distance = (scale.getY() * dimensions.getY() + scale.getX() * dimensions.getX()) * 0.9f;
        //300*0.9 = 270 which IS the distance
        allObjects.add(this);
    }

    /**
     * Draw a tangent line to the ellipse
     * @param x The x-coordinate where the line must be tangent to
     * @param y The y-coordinate where the line must be tangent to
     * @param isMoving If the tangent line return true while it's being created or after it has been fully created
     * @return When the tangent line is fully created or true (isMoving)
     */
    public boolean drawTangentLine(float x, float y, boolean isMoving) {
        float newDist = Mapper.map2(incrementTangentLine, 0, distance, 0, distance, Mapper.QUADRATIC, Mapper.EASE_IN);
        if (newDist > distance)
            newDist = distance;

        float common = newDist / (2 * sqrt((float) (1 + (Math.pow(dimensions.getY(), 4) * scale.getX() * x * scale.getX() * x) / (Math.pow(dimensions.getX(), 4) * scale.getY() * y * scale.getY() * y))));
        float x1 = common + (x * scale.getX()); //+
        float y1 = (-x * dimensions.getY() * dimensions.getY()) / (y * dimensions.getX() * dimensions.getX()) * common + (y * scale.getY()); //+
        float x2 = -common + (x * scale.getX()); //-
        float y2 = (x * dimensions.getY() * dimensions.getY()) / (y * dimensions.getX() * dimensions.getX()) * common + (y * scale.getY()); //-
        canvas.strokeWeight(5);
        canvas.stroke(30, 255, 255);
        if (Float.isNaN(y1) || Float.isNaN(y2)) {
            y1 = distance / 2;
            y2 = -y1;
        }

        canvas.line(x1, y1, x2, y2);
        incrementTangentLine += distance / 50.0f;
        return isMoving || (newDist / distance > 0.99 && newDist / distance < 1.01);
    }

    public boolean drawTangentLine(float x, float y) {
        return this.drawTangentLine(x, y, false);
    }

    /**
     *
     * @return An immutable list of all objects that have been created and are Ellipses
     *          or a subclass of Ellipse
     */
    @Override
    public List<? extends Ellipse> getAllObjects(){
        return allObjects;
    }

    /**
     *
     * @param s The new Scale object that will replace the original Scale object.
     * @return If the operation is a success
     * TODO: Might make these void, they serve no purpose
     */
    @Override
    public boolean scale(Scale s) { //Instant scaling!, is Absolute! Not relative!
        super.scale(s);
        distance = Math.min((dimensions.getX() / scale.getX() + dimensions.getY() / scale.getY()), (dimensions.getX() + dimensions.getY()) * 0.9f);
        return true;
    }

    /**
     *
     * @param obj Varargs to display the object at coordinates
     * @return Graphing the points in the coordinates list
     */
    @Override
    public boolean display(Object... obj) {
        addPoint(cos(incrementor / 100f),sin(incrementor / 100f));
        incrementor += speed;
        return graph();
    }
}
