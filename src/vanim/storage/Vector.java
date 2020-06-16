package vanim.storage;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vector<T> {
    protected T x,y,z;
    protected int size = 0;

    @SuppressWarnings("unchecked")
    public <E> Vector(@NotNull Vector<E> v){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector<?> vector = (Vector<?>) o;
        return getSize() == vector.getSize() &&
                Objects.equals(getX(), vector.getX()) &&
                Objects.equals(getY(), vector.getY()) &&
                Objects.equals(getZ(), vector.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ(), getSize());
    }

    @Override
    public String toString(){
        return String.format("[ %s , %s , %s ]",x,y,z);
    }
}
