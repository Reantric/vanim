package vanim.storage;

/**
 * protonlaser91
 */
public class Scale extends Vector<Float> {

    /**
     *
     * @param x Storing the x scaling value
     * @param y Storing the y scaling value
     * @param z Storing the z scaling value
     */
    public Scale(float x, float y, float z){
        super(x,y,z);

    }

    public Scale(float x, float y) {
        super(x,y,1f);

    }

    /**
     *
     * @param scaleAll Set all scale variables to this parameter.
     */
    public void setAll(float scaleAll){
        this.x = scaleAll;
        this.y = scaleAll;
        this.z = scaleAll;
    }

}
