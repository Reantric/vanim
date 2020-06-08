package vanim.Planes;

import processing.core.*;
import vanim.storage.Scale;
import vanim.storage.Vector;
import vanim.root.CanvasObject;
import static processing.core.PApplet.*;

public abstract class Plane extends CanvasObject {

    protected Vector<Float> ticks;
    protected int frameCountInit;
    protected int frameCountBuffer;

    public Plane(PApplet p, Vector<Float> xy, Vector<Integer> dimensions, Vector<Float> ticks) {
        super(p, p.createGraphics(dimensions.getX(),dimensions.getY(),P2D), xy, new Vector<>(dimensions));
        this.ticks = ticks;
    }

    protected abstract boolean generatePlane();
    protected abstract boolean scale(float... obj); //1-2 args for scale
    protected abstract void rotatePlane(float theta);
    protected abstract void moveVector(PVector initial, PVector output);

    public PGraphics getCanvas(){
        return this.canvas;
    }

    public Scale getScale(){
        return this.relScale;
    }

    public PApplet getProcessingInstance(){
        return this.processing;
    }
}
