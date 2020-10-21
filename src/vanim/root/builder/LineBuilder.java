package vanim.root.builder;

import org.jetbrains.annotations.NotNull;
import vanim.geometry.GeometricSpace;
import vanim.storage.color.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

public class LineBuilder extends VObjectBuilder {
    float weight;
    FVector start, end;

    public LineBuilder(GeometricSpace g, FVector start, @NotNull FVector end, float weight, Color color, Reason reasonCreated) {
        super(g, start, new FVector(end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ()), color, reasonCreated);
        this.weight = weight;
        this.end = end;
        this.start = start; //Copy constructor
        this.pos = new FVector(start);
    }

    public LineBuilder(GeometricSpace g, FVector start, @NotNull FVector end, float weight, Color color) {
        this(g, start, end, weight, color, Reason.USER_CREATED);
    }

    public LineBuilder(GeometricSpace g, FVector start, FVector end) {
        this(g, start, end, 4, new Color());
    }

    public LineBuilder(GeometricSpace g, FVector start, FVector end, Color color) {
        this(g, start, end, 4, color);
    }

    public float getLineWeight() {
        return weight;
    }

    public FVector getStart() {
        return start;
    }

    public FVector getEnd() {
        return end;
    }
}
