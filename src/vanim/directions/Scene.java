package vanim.directions;

import processing.core.PApplet;

public abstract class Scene {

    protected PApplet window;
    public boolean[] step = new boolean[100];

    public Scene(PApplet window) {
        this.window = window;
    }

    public abstract boolean execute();

}
