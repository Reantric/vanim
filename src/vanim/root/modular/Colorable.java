package vanim.root.modular;


import vanim.storage.color.Color;

public interface Colorable<T> {
    boolean fadeOut();

    boolean fadeIn();

    Color getColor();

    T setColor(Color color);
}
