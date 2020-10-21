package vanim.geometry.onedim;

import processing.core.PGraphics;
import vanim.geometry.twodim.Grid;
import vanim.root.CanvasObject;
import vanim.root.builder.GeometricSpaceBuilder;
import vanim.root.builder.LineBuilder;
import vanim.root.builder.TextBuilder;
import vanim.shapes.DoubleLine;
import vanim.shapes.Line;
import vanim.storage.Scale;
import vanim.storage.color.Color;
import vanim.storage.vector.FVector;
import vanim.text.Text;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;
import static java.lang.Math.round;
import static processing.core.PApplet.println;

public class NumberLine extends Grid {
    Line line;
    boolean doubleEdged = true;
    int frameCountInit, frameCountBuffer, numOfTicks = 7;
    boolean frameWait;
    List<Text> text = new ArrayList<>();
    List<Line> visibleTicks = new ArrayList<>();
    Line progenitor;

    public NumberLine(GeometricSpaceBuilder builder) {
        super(builder);
        line = new DoubleLine(new LineBuilder(this, FVector.subtract(pos, FVector.scale(ticks, 3f)), FVector.add(pos, FVector.scale(ticks, 3f)), 8, color));
        FVector direction = line.getDirectionVec();
        FVector perpendicularTick = direction.getPerpendicular2D().setMag(30); // Must be absolute, as it is an FVector!
        FVector midpoint = line.getAverage();
        float len = line.getLength();
        progenitor = new DoubleLine(new LineBuilder(this, FVector.add(midpoint, perpendicularTick), FVector.subtract(midpoint, perpendicularTick), 7, new Color(255, 255, 255)));

        for (int i = (int) ceil(-numOfTicks / 2.0); i < ceil(numOfTicks / 2.0); i++) {
            if (i == 0) continue;

            FVector averagePoint = FVector.add(line.getStart(), FVector.scale(direction, len / 2 + i * len / numOfTicks));
            println(averagePoint);
            visibleTicks.add(new DoubleLine(
                    new LineBuilder(this, FVector.add(averagePoint, perpendicularTick), FVector.subtract(averagePoint, perpendicularTick), 7, color
                    )
            ));
            text.add(new Text(new TextBuilder(this, String.valueOf(round(i * scale.getX())), FVector.subtract(averagePoint, FVector.scale(perpendicularTick, 2.6f)).scale(scale), 50, textColor)));
        }

    }

    /**
     * @param args Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    @Override
    public boolean displayHelper(Object... args) {
        boolean b = line.display();
        visibleTicks.forEach(CanvasObject::display);
        progenitor.getColor().setHue(processing.frameCount / 2.0f % 255);
        text.forEach(CanvasObject::display);
        progenitor.display();
        //  if (!frameWait)
        //    eh;

        //     println(line.getStart());
        canvas.endDraw();
        return b;
        //TODO: Make some stuff shared in CartesianPlane and this a shared super method!
    }

    /**
     * @return
     */
    @Override
    public boolean generateSpace() {
        canvas.beginDraw();
        frameWait = processing.frameCount > frameCountInit + frameCountBuffer;
        return true;
    }

    private boolean labelAxes() {
        return false;
    }

    @Override
    public PGraphics getCanvas() {
        return canvas;
    }

    /**
     * @param s The new Scale object that will replace the original Scale object.
     * @return If the operation was a success.
     */
    @Override
    public NumberLine scale(Scale s) {
        return null;
    }
}
