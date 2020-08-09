package vanim.root.modular;


import vanim.storage.Color;

public interface Colorable<T> {
    boolean fadeOut();

    boolean fadeIn();

    Color getColor();

    T setColor(Color color);
}
