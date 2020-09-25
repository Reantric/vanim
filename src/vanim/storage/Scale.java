package vanim.storage;

import vanim.storage.vector.FVector;

/**
 * protonlaser91
 */
public class Scale extends FVector {

    /**
     * @param x Storing the x scaling value
     * @param y Storing the y scaling value
     * @param z Storing the z scaling value
     */
    public Scale(float x, float y, float z) {
        super(x, y, z);

    }

    public Scale(float x, float y) {
        super(x, y, 0f);

    }

    public Scale(Scale scale) {
        this(scale.getX(), scale.getY(), scale.getZ());
    }

    @Override
    public Scale reciprocate() {
        if (x != 0)
            x = 1 / x;
        if (y != 0)
            y = 1 / y;
        if (z != 0)
            z = 1 / z;
        return this;
    }
}
