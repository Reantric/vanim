package vanim.root;

import processing.core.PApplet;
import processing.core.PGraphics;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author protonlaser91
 */
public abstract class CanvasObject implements GeneralObject{

    private static final Map<Class<? extends CanvasObject>, Set<WeakReference<CanvasObject>>> allObjects = new HashMap<>();
    protected PGraphics canvas;
    protected Scale scale = new Scale(1,1,1);
    protected FVector pos;
    protected FVector dimensions; //width height
    public PApplet processing;

    /**
     *
     * @param p The processing instance that will be used to perform operations.
     * @param c The canvas that will be drawn on
     * @param pos The position of the object on that plane (in scaled coordinates, not absolute)
     * @param dimensions The width, height (and depth) of the object (in scaled coordinates, not absolute)
     */
    @SuppressWarnings("unchecked")
    // Will work because the maximum superclass that will be reached is CanvasObject itself!
    protected CanvasObject(PApplet p, PGraphics c, FVector pos, FVector dimensions) {
        processing = p;
        canvas = c;
        this.pos = pos;
        this.dimensions = dimensions;

        Class<? extends CanvasObject> originalClass = getClass();
        Class<? super CanvasObject> finalSuperclass = CanvasObject.class.getSuperclass();
        while (!originalClass.equals(finalSuperclass)) {
            allObjects.computeIfAbsent(originalClass,
                    k -> new HashSet<>()).add(new WeakReference<>(this));
            originalClass = (Class<? extends CanvasObject>) originalClass.getSuperclass();
        }
    }

    protected CanvasObject(PGraphics c, FVector pos, FVector dimensions){
        this(null,c,pos,dimensions);
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
     *
     * @param obj Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    public abstract boolean display(Object... obj); // Display the VObject
}
