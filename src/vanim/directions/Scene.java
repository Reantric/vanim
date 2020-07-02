package vanim.directions;

import vanim.core.Applet;

public abstract class Scene {

    protected Applet window;
    public boolean[] step = new boolean[100];

    public Scene(Applet window) {
        this.window = window;
    }

    public abstract boolean execute();

}
