package vanim.root.modular.display;

import vanim.root.modular.Displayable;

@FunctionalInterface
public interface TwoArgDisplay<A, B> extends Displayable {

    boolean display(A a, B b);

    default boolean display() {
        return true;
    }

    /**
     * @param args Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    @Override
    @SuppressWarnings("unchecked")
    default boolean display(Object... args) {
        return display((A) args[0], (B) args[1]);
    }

}
