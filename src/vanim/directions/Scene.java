package vanim.directions;

import vanim.core.Applet;
import vanim.root.CanvasObject;

import java.util.Stack;

public abstract class Scene {

    protected Applet window;
    public boolean[] step = new boolean[100];
    protected Stack<CanvasObject> order; // Back to front!

    public Scene(Applet window) {
        this.window = window;
    }

    public abstract boolean execute();

    public Stack<CanvasObject> getOrder() {
        return this.order;
    }

}
