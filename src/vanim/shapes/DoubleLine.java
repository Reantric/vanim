package vanim.shapes;

import vanim.planes.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;

public class DoubleLine extends Line{

    FVector average;

    public DoubleLine(Plane p, FVector start, FVector end, float weight, Color color) {
            super(p,start,end,weight,color);
            end.multiplyAll(absScale.getX(),absScale.getY());
            average = new FVector((start.getX() + end.getX())/2.0f,(start.getY() + end.getY())/2.0f);
    }

    public DoubleLine(Plane p, FVector start, FVector end) {
        this(p,start,end,4,new Color());
    }

    public DoubleLine(Plane p, FVector start, FVector end, float weight) {
        this(p,start,end,weight,new Color());
    }

    @Override
    public boolean display(Object... obj) {
        canvas.stroke(color.getHue().getValue(), color.getSaturation().getValue(),
                color.getBrightness().getValue(), color.getAlpha().getValue());
        canvas.strokeCap(strokeNum);
        setMapPower(3);
        push(100);
        if (weight != 0)
            canvas.strokeWeight(weight);


        canvas.line(average.getX(), average.getY(), pos.getX(), pos.getY());
        canvas.line(average.getX(), average.getY(), 2 * average.getX() - pos.getX(), 2 * average.getY() - pos.getY());
        return pos.equals(end);
    }
}
