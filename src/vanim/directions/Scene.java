package vanim.directions;

import processing.core.PApplet;
import vanim.util.Mapper;
import vanim.util.Useful;

import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static vanim.Directions.*;
import static vanim.planar.b;
import static vanim.planar.canvas;

public abstract class Scene{
    protected PApplet window;

    public Scene(PApplet window){
        this.window = window;
    }

    public abstract boolean scene();
}
