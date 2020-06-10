package vanim.storage;

public class Vector<T> {
    T x,y,z;
    int size = 0;

    public <E> Vector(Vector<E> v){
        try {
            x = (T) v.getX();
            y = (T) v.getY();
            z = (T) v.getZ();
            size = v.getSize();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public Vector(T x, T y, T z){
        this.x = x;
        this.y = y;
        this.z = z;
        size = 3;
    }

    public Vector(T x, T y){
        this(x,y,null);
        size = 2;
    }

    public Vector(T x){
        this(x,null,null);
        size = 1;
    }

    public Vector() {
        this(null,null,null);
        size = 1;
    }

    public void setXY(T x, T y){
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x){
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y){
        this.y = y;
    }

    public T getZ() {
        return z;
    }

    public void setZ(T z){
        this.z = z;
    }

    public int getSize() {
        return size;
    }

}
