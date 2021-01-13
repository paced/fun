package com.anonymous.projects.rover.automata;

import com.anonymous.projects.rover.model.Coordinate;
import com.anonymous.projects.rover.model.Table;

/**
 * The rover automaton to be placed on a {@link Table}.
 */
public class Rover {

    /** Has the {@link Rover} been placed? */
    private boolean placed;

    /** Where is the rover? This is nullable. */
    private Coordinate coordinate;

    /**
     * Instantiate a {@link Rover} that has not yet been placed.
     */
    public Rover() {
        placed = false;
    }

    /**
     * Attempt to place the {@link Rover} on a valid {@link Coordinate}.
     *
     * @param destination the desired location
     */
    public void place(final Coordinate destination) {
        /* Is the rover already placed or is the destination invalid? */

        if (placed || isOutOfBounds(destination)) {
            return;
        }

        coordinate = destination;
        placed = true;
    }

    /**
     * Report the location of a {@link Rover} if it is placed. Silence if it is not.
     */
    public void report() {
        if (placed) {
            System.out.println(String.format("P: (%s,%s)", coordinate.getX(), coordinate.getY()));
        }
    }

    /**
     * Move along the X-axis then the Y-axis to reach the coordinate if it is on the table. Otherwise, do nothing.
     *
     * <p>
     *
     * Also, print on every step of the movement.
     *
     * @param destination the desired location
     */
    public void move(final Coordinate destination) {
        /* Is the rover placed and the coordinate to move actually on the board? */

        if (!placed || isOutOfBounds(destination)) {
            return;
        }

        /* Movement parts are not prone to race conditions. The steps will only be printed. */

        int stepX = coordinate.getX();
        int stepY = coordinate.getY();

        StringBuilder stringBuilder = new StringBuilder(String.format("M: (%s,%s)", stepX, stepY));

        while (destination.getX() != stepX) {
            stepX += destination.getX() < stepX ? -1 : 1;

            stringBuilder.append(String.format(" (%s,%s)", stepX, stepY));
        }

        while (destination.getY() != stepY) {
            stepY += destination.getY() < stepY ? -1 : 1;

            stringBuilder.append(String.format(" (%s,%s)", stepX, stepY));
        }

        System.out.println(stringBuilder.toString());

        coordinate = destination;
    }

    /**
     * Is the given destination {@link Coordinate} out of bounds?
     *
     * @param destination the desired location
     * @return whether the given {@link Coordinate} is out of bounds
     */
    private boolean isOutOfBounds(final Coordinate destination) {
        return destination.getX() < Table.LEFTMOST_X
                || destination.getX() > Table.LEFTMOST_X + Table.TABLE_WIDTH
                || destination.getY() < Table.BOTTOMMOST_Y
                || destination.getY() > Table.BOTTOMMOST_Y + Table.TABLE_HEIGHT;
    }

}
