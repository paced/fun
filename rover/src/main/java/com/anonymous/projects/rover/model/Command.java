package com.anonymous.projects.rover.model;

import com.anonymous.projects.rover.automata.Rover;

/** Enumeration for the commands available for the {@link Rover}. */
public enum Command {

    /** Attempt to place a {@link Rover} on the {@link Table}. */
    PLACE,

    /** Attempt to move a {@link Rover} that already is on the {@link Table}. */
    MOVE,

    /** If the {@link Rover} is on the table, report its location. */
    REPORT

}
