package vanim.root.modular;

import vanim.storage.Scale;

public interface NumberScalable<T> extends Scalable<T> {
    @Override
    default T scale(Scale s) {
        return scale(s.getX(), s.getY(), s.getZ());
    }

    T scale(Object... obj);
}
