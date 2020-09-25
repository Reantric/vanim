package vanim.directions.subscene;

import vanim.core.Applet;
import vanim.directions.Scene;
import vanim.geometry.twodim.CartesianPlane;
import vanim.storage.vector.FVector;
import vanim.storage.vector.IVector;

public final class Scene2 extends Scene {

    CartesianPlane plane;

    public Scene2(Applet window) {
        super(window);
        plane = new CartesianPlane(window, new FVector(), new IVector(1920, 1080), new FVector(5, 5));
    }

    @Override
    public boolean execute() {
        step[0] = plane.generatePlane(); // Generate plane
        // Draw on it
        // Then display at the very end!
        plane.display();
        return false;
    }
}