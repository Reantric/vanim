package vanim.geometry;

import processing.core.PGraphics;
import vanim.core.Applet;
import vanim.root.builder.GeometricSpaceBuilder;
import vanim.root.vobjects.AbsoluteVObject;
import vanim.storage.vector.FVector;
import vanim.text.Text;

import java.util.HashMap;
import java.util.Map;

import static processing.core.PConstants.HSB;
import static vanim.planar.HEIGHT;
import static vanim.planar.WIDTH;

public abstract class GeometricSpace extends AbsoluteVObject {
    protected FVector ticks, rescale;
    protected Map<Text, Boolean> texts = new HashMap<>(); // Text, hasDisplayed

    /**
     * @param builder The necessary builder to construct this Plane
     *                Check the GeometricSpaceBuilder to know the params involved
     */
    protected GeometricSpace(GeometricSpaceBuilder builder) {
        super(builder);
        this.ticks = builder.getTicks();
        rescale = new FVector((float) canvas.width / WIDTH, (float) canvas.height / HEIGHT);
        scale.setX(300 / ticks.getX() * rescale.getX()); // 200 def
        if (ticks.getY() != 0) // Make sure dimension exists!
            scale.setY(300 / ticks.getY() * rescale.getY()); // 200 def
    }

    /**
     * @return The processing instance.
     */
    public Applet getProcessingInstance() {
        return this.processing;
    }

    /**
     * @return FVector of the axes
     */
    public FVector getAxes() {
        return ticks;
    }

    /**
     * @param args Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    @Override
    public boolean display(Object... args) {
        canvas.colorMode(HSB); // duplicate in grid2d
        canvas.background(0, 0, 0, 0);
        canvas.translate(canvas.width / 2.0f, canvas.height / 2.0f);
        displayHelper(args);
        processing.image(canvas, pos.getX(), pos.getY()); // <--- Do all method!
        texts.forEach((k, v) -> v = k.display());
        return false;
    }

    /**
     * Helper method called in display();
     *
     * @return
     */
    protected abstract boolean displayHelper(Object... args);

    /**
     * @return
     */
    public abstract boolean generateSpace(); // maybe another method that contains this that
    // would actually get some damn settings done fuck!

    public abstract PGraphics getCanvas();
}
