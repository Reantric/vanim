package vanim.shapes;

import vanim.root.builder.LineBuilder;
import vanim.storage.vector.FVector;

public class DoubleLine extends Line {

    FVector average;
    Line l1, l2;

    public DoubleLine(LineBuilder builder) {
        super(builder);
        // System.out.println("r u serious m9: " + start);
        average = new FVector((start.getX() + end.getX()) / 2.0f, (start.getY() + end.getY()) / 2.0f);
        l1 = new Line(new LineBuilder(plane, average, start, weight, color, builder.getReasonCreated()));
        l2 = new Line(new LineBuilder(plane, average, end, weight, color, builder.getReasonCreated()));
    }

    @Override
    public boolean display(Object... obj) {
        return l1.display() & l2.display();
    }
}
