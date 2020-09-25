package vanim.storage.vector;

import vanim.storage.Scale;
import vanim.storage.Vector;

public class FVector extends Vector<Float> { // Field Vector or Float Vector lmao

    public <E extends Number> FVector(Vector<E> v) {
        super(v);
    }

    public FVector(float x, float y, float z) {
        super(x, y, z);
    }

    public FVector(float x, float y) {
        this(x, y, 0);
    }

    public FVector(float x) {
        this(x, 0, 0);
    }

    public FVector() {
        this(0, 0, 0);
    }

    public static FVector scale(FVector start, Scale scale) {
        return FVector.scale(start, scale.getX(), scale.getY(), scale.getZ());
    }

    public static FVector scale(FVector v, Float... scale) {
        return new FVector(v).scale(scale);
    }

    public static FVector add(FVector v, FVector o) {
        return new FVector(v).add(o);
    }

    public static FVector subtract(FVector v, FVector o) {
        return new FVector(v).subtract(o);
    }

    public static FVector reciprocate(FVector v) {
        return new FVector(v).reciprocate();
    }

    public FVector addX(FVector v) {
        this.x += v.getX();
        return this;
    }

    public FVector addXY(FVector v) {
        this.x += v.getX();
        this.y += v.getY();
        return this;
    }

    public FVector addY(FVector v) {
        this.y += v.getY();
        return this;
    }

    public FVector addZ(FVector v) {
        this.z += v.getZ();
        return this;
    }

    /**
     * @param scaleAll Set all scale variables to this parameter.
     */
    public void setAll(float scaleAll) {
        this.x = scaleAll;
        this.y = scaleAll;
        this.z = scaleAll;
    }

    @Override
    public FVector add(Vector<Float> v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
        return this;
    }

    public FVector subtract(FVector v) {
        this.x -= v.getX();
        this.y -= v.getY();
        this.z -= v.getZ();
        return this;
    }

    @Override
    public FVector scale(Float... scale) {
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

    @Override
    public FVector reciprocate() {
        if (x != 0)
            x = 1 / x;
        if (y != 0)
            y = 1 / y;
        if (z != 0)
            z = 1 / z;
        return this;
    }

    @Override
    public FVector normalize() {
        this.scale(1.0f / this.getMag());
        return this;
    }

    @Override
    public float getMag() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public IVector getIntVec() {
        return new IVector(Math.round(x), Math.round(y), Math.round(z));
    }

    public FVector scale(Scale scale) {
        return this.scale(scale.getX(), scale.getY(), scale.getZ());
    }

}
