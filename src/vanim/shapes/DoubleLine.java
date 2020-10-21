package vanim.shapes;

import vanim.root.builder.LineBuilder;

public class DoubleLine extends Line {

    Line l1, l2;

    public DoubleLine(LineBuilder builder) {
        super(builder);
        // System.out.println("r u serious m9: " + start);
        l1 = new Line(new LineBuilder(geometricSpace, average, start, weight, color, builder.getReasonCreated()));
        l2 = new Line(new LineBuilder(geometricSpace, average, end, weight, color, builder.getReasonCreated()));
    }

    @Override
    protected void recalculateDimensions() {
        super.recalculateDimensions();
        average.setXY((start.getX() + end.getX()) / 2.0f, (start.getY() + end.getY()) / 2.0f);
    }

    @Override
    public boolean display(Object... obj) {
        return l1.display() & l2.display();
    }
}
