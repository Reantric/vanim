package vanim.root;

import processing.core.PApplet;
import processing.core.PGraphics;
import vanim.storage.Scale;
import vanim.storage.Vector;

public abstract class CanvasObject {

    protected PGraphics canvas;
    protected Scale relScale = new Scale(1,1);
    protected Vector<Float> pos;
    protected Vector<Float> dimensions; //width height
    public PApplet processing;

    protected CanvasObject(PApplet p, PGraphics c, Vector<Float> pos, Vector<Float> dimensions){
        processing = p;
        canvas = c;
        this.pos = pos;
        this.dimensions = dimensions;
    }

    protected CanvasObject(PGraphics c, Vector<Float> xy, Vector<Float> dimensions){
        this(null,c,xy,dimensions);
    }

    public abstract boolean display(Object... obj); // Display the VObject
}
