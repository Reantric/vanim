package vanim.storage;

public class FVector extends Vector<Float> { // Field Vector or Float Vector lmao

    public <E> FVector(Vector<E> v) {
        super(v);
    }

    public FVector(float x, float y, float z) {
        super(!Float.isNaN(x) ? x : null, !Float.isNaN(y) ? y : null, !Float.isNaN(z) ? z : null);
    }

    public FVector(float x, float y) {
        this(x, y, Float.NaN);
    }

    public FVector(float x) {
        this(x, Float.NaN, Float.NaN);
    }

    public FVector() {
        this(Float.NaN, Float.NaN, Float.NaN);
    }

    public void addX(Vector<Float> v) {
        this.x += v.getX();
    }

    public void addXY(Vector<Float> v){
        this.x += v.getX();
        this.y += v.getY();
    }

    public void addY(Vector<Float> v){
        this.y += v.getY();
    }

    public void addZ(Vector<Float> v){
        this.z += v.getZ();
    }

    public void add(Vector<Float> v){
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
    }

    public void multiplyX(float scaleX){
        multiplyAll(scaleX,1,1);
    }

    public void multiplyY(float scaleY){
        multiplyAll(1,scaleY,1);
    }

    public void multiplyZ(float scaleZ){
        multiplyAll(1,1,scaleZ);
    }

    public void multiplyAll(float... scale){
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

}
