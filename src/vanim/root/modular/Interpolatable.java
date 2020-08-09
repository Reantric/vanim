package vanim.root.modular;

import vanim.util.map.MapEase;
import vanim.util.map.MapType;

public interface Interpolatable<T> {
    boolean interpolate(T bound, MapType interpType, float speed, MapEase easing);
}
