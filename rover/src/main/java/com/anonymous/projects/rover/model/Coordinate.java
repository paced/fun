package com.anonymous.projects.rover.model;

/** A coordinate on the {@link Table}. */
public class Coordinate {

    /** The X-value. */
    private final int x;

    /** The Y-value. */
    private final int y;

    /**
     * Create a {@link Coordinate} from an X and Y coordinate.
     *
     * @param theX the X-value
     * @param theY the Y-value
     */
    public Coordinate(final int theX, final int theY) {
        x = theX;
        y = theY;
    }

    /**
     * Return the X-value.
     *
     * @return the X-value
     */
    public final int getX() {
        return x;
    }

    /**
     * Return the Y-value
     *
     * @return the Y-value
     */
    public final int getY() {
        return y;
    }

}
