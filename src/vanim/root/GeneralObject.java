package vanim.root;

import com.google.common.collect.Multiset;

public interface GeneralObject {
    Multiset<? extends CanvasObject> getAllObjects(); // An immutable list of all objects that have been created
    // and are of class ? or a subclass of ? that must extend CanvasObject
}
