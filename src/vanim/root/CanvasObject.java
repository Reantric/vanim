package vanim.root;

import processing.core.PApplet;
import processing.core.PGraphics;
import vanim.storage.Scale;
import vanim.storage.Vector;

/**
 * @author protonlaser91
 */
public abstract class CanvasObject {

    protected PGraphics canvas;
    protected Scale scale = new Scale(1,1);
    protected Vector<Float> pos;
    protected Vector<Float> dimensions; //width height
    public PApplet processing;

    /**
     *
     * @param p The processing instance that will be used to perform operations.
     * @param c The canvas that will be drawn on
     * @param pos The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     */
    protected CanvasObject(PApplet p, PGraphics c, Vector<Float> pos, Vector<Float> dimensions){
        processing = p;
        canvas = c;
        this.pos = pos;
        this.dimensions = dimensions;
    }

    protected CanvasObject(PGraphics c, Vector<Float> xy, Vector<Float> dimensions){
        this(null,c,xy,dimensions);
    }

    /**
     *
     * @param obj Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    public abstract boolean display(Object... obj); // Display the VObject
}
