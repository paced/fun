package com.anonymous.projects.rover.model;

import com.anonymous.projects.rover.automata.Rover;

/**
 * Data model for a {@link Rover} automaton on which to move.
 */
public class Table {

    /** what is the lowest possible X-value? */
    public static final int LEFTMOST_X = 0;

    /** What is the lowest possible Y-value? */
    public static final int BOTTOMMOST_Y = 0;

    /** The width of the table. **/
    public static final int TABLE_WIDTH = 5;

    /** The height of the table. **/
    public static final int TABLE_HEIGHT = 5;

}
