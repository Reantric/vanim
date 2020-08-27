package vanim.root.modular;

@FunctionalInterface
public interface Displayable {

    /**
     * @param args Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    boolean display(Object... args); // Display the VObject
}
