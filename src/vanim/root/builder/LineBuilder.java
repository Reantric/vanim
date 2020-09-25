package vanim.root.builder;

import org.jetbrains.annotations.NotNull;
import vanim.geometry.twodim.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

public class LineBuilder extends VObjectBuilder {
    float weight;
    FVector start, end;

    public LineBuilder(Plane p, FVector start, @NotNull FVector end, float weight, Color color, Reason reasonCreated) {
        super(p, start, new FVector(end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ()), color, reasonCreated);
        this.weight = weight;
        this.end = end;
        this.start = start; //Copy constructor
        this.pos = new FVector(start);
    }

    public LineBuilder(Plane p, FVector start, @NotNull FVector end, float weight, Color color) {
        this(p, start, end, weight, color, Reason.USER_CREATED);
    }

    public LineBuilder(Plane p, FVector start, FVector end) {
        this(p, start, end, 4, new Color());
    }

    public LineBuilder(Plane plane, FVector start, FVector end, Color color) {
        this(plane, start, end, 4, color);
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
