package vanim.root.modular;

import vanim.storage.Color;

public interface Graphable {
    //Graph dis!

    /**
     * Graph the points in the coordinate array via iteration
     *
     * @return When the operation has completed
     */
    boolean graph();

    /**
     * @param x X-coordinate of the point to be added to the coordinates list
     * @param y Y-coordinate of the point to be added to the coordinates list
     * @return When the operation has successfully added the required number of points
     */
    boolean addPoint(float x, float y, Color color);
}
