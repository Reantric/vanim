package vanim.storage.vector;

import vanim.storage.Vector;

public class IVector extends Vector<Integer> {

    public <E extends Number> IVector(Vector<E> v) {
        super(v);
    }

    public IVector(Integer x, Integer y, Integer z) {
        super(x, y, z);
    }

    public IVector(Integer x, Integer y) {
        this(x,y,0);
    }

    public IVector(Integer x) {
        this(x,0,0);
    }

    public IVector() {
        this(0,0,0);
    }

    public IVector addX(Vector<Integer> v) {
        this.x += v.getX();
        return this;
    }

    public IVector addXY(Vector<Integer> v) {
        this.x += v.getX();
        this.y += v.getY();
        return this;
    }

    public IVector addY(Vector<Integer> v) {
        this.y += v.getY();
        return this;
    }

    public IVector addZ(Vector<Integer> v) {
        this.z += v.getZ();
        return this;
    }

    public IVector add(Vector<Integer> v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
        return this;
    }


    public IVector scale(Integer... scale) {
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
    public IVector reciprocate() { // Is not well defined!, Truncates!
        if (x != 0)
            x = 1 / x;
        if (y != 0)
            y = 1 / y;
        if (z != 0)
            z = 1 / z;
        return this;
    }

    @Override // TODO: bruhh1gh
    public Vector<Integer> normalize() {
        return null;
    }

    @Override
    public float getMag() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public FVector getFloatVec() {
        return new FVector(x, y, z);
    }

}
