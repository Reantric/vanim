package vanim.misc;

public class Scale {
    float scaleX, scaleY, scaleZ;
    public Scale(float x, float y, float z){
        scaleX = x;
        scaleY = y;
        scaleZ = z;
    }

    public Scale(float x, float y) {
        scaleX = x;
        scaleY = y;
        scaleZ = 1;
    }

    public void setScaleAll(float scaleAll){
        this.scaleX = scaleAll;
        this.scaleY = scaleAll;
        this.scaleZ = scaleAll;
    }

    public void multiplyScaleAll(float scaleAll){
        this.scaleX *= scaleAll;
        this.scaleY *= scaleAll;
        this.scaleZ *= scaleAll;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void multiplyScaleX(float scaleX){
        this.scaleX *= scaleX;
    }

    public float getScaleZ() {
        return scaleZ;
    }

    public void setScaleZ(float scaleZ) {
        this.scaleZ = scaleZ;
    }

    public void multiplyScaleZ(float scaleZ){
        this.scaleZ *= scaleZ;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void multiplyScaleY(float scaleY){
        this.scaleY *= scaleY;
    }

}
