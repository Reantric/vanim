package vanim.root.modular;

import vanim.util.MapConstant;

public interface Interpolatable<T> {
    boolean interpolate(T bound, MapConstant interpType, float speed);
}
