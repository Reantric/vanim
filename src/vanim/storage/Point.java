package vanim.storage;

public class Point extends Vector<Float> {
    Color color;

    public Point() {
        this(0, 0);
    }

    public Point(float x, float y, Color color) {
        super(x, y, 0f);
        this.color = color;
    }

    // maybe in the future add color? wack !
    public Point(float x, float y) {
        this(x, y, new Color());
    }

    public Point(double x, double y) {
        this((float) x, (float) y);
    }

    public Color getColor() {
        return this.color;
    }
}
