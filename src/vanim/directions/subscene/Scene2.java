package vanim.directions.subscene;

import vanim.core.Applet;
import vanim.directions.Scene;
import vanim.geometry.onedim.NumberLine;
import vanim.geometry.twodim.CartesianGrid2D;
import vanim.root.builder.GeometricSpaceBuilder;
import vanim.root.builder.LineBuilder;
import vanim.shapes.Circle;
import vanim.shapes.DoubleLine;
import vanim.shapes.Line;
import vanim.storage.vector.FVector;
import vanim.storage.vector.IVector;

public final class Scene2 extends Scene {

    NumberLine line;
    CartesianGrid2D plane;
    Line actualLine;
    Circle b;

    public Scene2(Applet window) {
        super(window);
        line = new NumberLine(new GeometricSpaceBuilder(window, new FVector(), new IVector(1920, 1080), new FVector(5, 0)));
        actualLine = new DoubleLine(new LineBuilder(line, new FVector(), new FVector(300, 100)));
        // b = new Circle(new VObjectBuilder(plane,new FVector(),3, new Color()),1);
    }

    @Override
    public boolean execute() {
        step[0] = line.generateSpace(); // Generate plane
        // Draw on it
        actualLine.display();
        // Then display at the very end!
        line.display();
        return false;
    }
}