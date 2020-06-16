package vanim.storage.vector;

import vanim.storage.Vector;

public class IVector extends Vector<Integer> {

    public <E> IVector(Vector<E> v) {
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

    public void addX(Vector<Integer> v){
        this.x += v.getX();
    }

    public void addXY(Vector<Integer> v){
        this.x += v.getX();
        this.y += v.getY();
    }

    public void addY(Vector<Integer> v){
        this.y += v.getY();
    }

    public void addZ(Vector<Integer> v){
        this.z += v.getZ();
    }

    public void add(Vector<Integer> v){
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
    }

    public void multiplyX(Integer scaleX){
        multiplyAll(scaleX,1,1);
    }

    public void multiplyY(Integer scaleY){
        multiplyAll(1,scaleY,1);
    }

    public void multiplyZ(Integer scaleZ){
        multiplyAll(1,1,scaleZ);
    }

    public void multiplyAll(Integer... scale){
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
    }

    public FVector getFloatVec(){
        return new FVector(x,y,z);
    }

}
