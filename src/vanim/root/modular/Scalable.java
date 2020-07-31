package vanim.root.modular;

import vanim.storage.Scale;

public interface Scalable<T> {
    /**
     * @param s The new Scale object that will replace the original Scale object.
     * @return If the operation was a success.
     */
    T scale(Scale s);

    Scale getScale();
}
