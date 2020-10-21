package vanim.storage;

import vanim.root.modular.Colorable;
import vanim.storage.color.Color;
import vanim.storage.vector.FVector;

public class Point extends FVector implements Colorable<Point> {
    Color color;

    public Point() {
        this(0, 0);
    }


    @Override
    public Point scale(Float... scale) {
        int len = scale.length;
        switch (len) {
            case 1 -> {
                this.x *= scale[0];
                this.y *= scale[0];
                this.z *= scale[0];
            }
            case 2 -> {
                this.x *= scale[0];
                this.y *= scale[1];
            }
            case 3 -> {
                this.x *= scale[0];
                this.y *= scale[1];
                this.z *= scale[2];
            }
        }
        return this;
    }

    public Point scale(Scale scale) {
        return this.scale(scale.getX(), scale.getY(), scale.getZ());
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

    @Override
    public boolean fadeOut() {
        return color.getAlpha().interpolate(0);
    }

    @Override
    public boolean fadeIn() {
        return color.getAlpha().interpolate(255);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Point setColor(Color color) {
        this.color = color;
        return this;
    }
}
