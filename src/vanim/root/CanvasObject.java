package vanim.root;

import vanim.core.Applet;
import vanim.core.Graphics2D;
import vanim.root.modular.ColorCompatible;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author protonlaser91
 */
public abstract class CanvasObject implements ColorCompatible {

    private static final Map<Class<? extends CanvasObject>, Set<WeakReference<CanvasObject>>> allObjects = new HashMap<>();
    protected Graphics2D canvas;
    protected Color color;
    protected Scale scale = new Scale(1, 1, 1);
    protected FVector pos;
    protected FVector dimensions; //width height
    public Applet processing;

    /**
     * @param p          The processing instance that will be used to perform operations.
     * @param c          The canvas that will be drawn on
     * @param pos        The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     */
    @SuppressWarnings("unchecked")
    // Will work because the maximum superclass that will be reached is CanvasObject itself!
    protected CanvasObject(Applet p, Graphics2D c, FVector pos, FVector dimensions, Color color) {
        processing = p;
        canvas = c;
        this.pos = pos;
        this.dimensions = dimensions;
        this.color = color;

        Class<? extends CanvasObject> originalClass = getClass();
        Class<? super CanvasObject> finalSuperclass = CanvasObject.class.getSuperclass();
        while (!originalClass.equals(finalSuperclass)) {
            allObjects.computeIfAbsent(originalClass,
                    k -> new HashSet<>()).add(new WeakReference<>(this));
            originalClass = (Class<? extends CanvasObject>) originalClass.getSuperclass();
        }
    }

    protected CanvasObject(Applet p, Graphics2D c, FVector pos, FVector dimensions) {
        this(p, c, pos, dimensions, new Color());
    }

    protected CanvasObject(Graphics2D c, FVector pos, FVector dimensions) {
        this(null, c, pos, dimensions);
    }

    /**
     * @return Color object of the selected CanvasObject
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return The position of the selected CanvasObject
     */
    public FVector getPos() {
        return this.pos;
    }

    /**
     * @return The dimensions of the selected CanvasObject
     */
    public FVector getDimensions() {
        return this.dimensions;
    }

    /**
     * @return The reference to the canvas the host plane uses to draw on
     */
    public Graphics2D getCanvas() {
        return this.canvas;
    }

    /**
     * @param type The class (<T>) of objects of which to be returned
     * @return An immutable list of all objects that have been created and are <T> Type
     * or a subclass of <T>
     */
    public static <T> List<T> getAllObjects(Class<T> type) {
        return allObjects.get(type).stream().map(f -> type.cast(f.get())).collect(Collectors.toList());
    }

    /**
     * Interpolate alpha to 0 (fade to black)
     *
     * @return When the operation has completed
     */
    @Override
    public boolean fadeOut() {
        return color.getAlpha().interp(0);
    }

    /**
     * Interpolate alpha to 255 (fade in)
     *
     * @return When the operation has completed
     */
    @Override
    public boolean fadeIn() {
        return color.getAlpha().interp(255);
    }

    /**
     * @param obj Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    public abstract boolean display(Object... obj); // Display the VObject
}
