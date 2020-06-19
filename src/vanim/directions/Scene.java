package vanim.directions;

import processing.core.PApplet;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Scene {

    private static final Set<WeakReference<Scene>> allObjects = new HashSet<>();
    protected PApplet window;
    public boolean[] step = new boolean[100];

    public Scene(PApplet window) {
        this.window = window;
        allObjects.add(new WeakReference<>(this));
    }

    public abstract boolean execute();

    /**
     * @param
     * @return
     */
    public static Set<Scene> getClasses() {
        return allObjects.stream().map(Reference::get).collect(Collectors.toSet());
    }
}
