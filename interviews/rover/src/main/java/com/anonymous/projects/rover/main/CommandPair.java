package com.anonymous.projects.rover.main;

import com.anonymous.projects.rover.model.Command;
import com.anonymous.projects.rover.model.Coordinate;

import java.util.Optional;

/** A command and an {@link Optional} {@link Coordinate} to be executed. */
class CommandPair {

    /** The {@link Command}. */
    private final Command command;

    /** The {@link Coordinate} context. */
    private final Optional<Coordinate> coordinate;

    /**
     * Construct a {@link CommandPair} without a coordinate context.
     *
     * @param theCommand the {@link Command}
     */
    CommandPair(final Command theCommand) {
        command = theCommand;

        coordinate = Optional.empty();
    }

    /**
     * Construct a {@link CommandPair} containing a command and it's coordinate context.
     *
     * @param theCommand the {@link Command}
     * @param theCoordinate the {@link Coordinate}
     */
    CommandPair(final Command theCommand, final Coordinate theCoordinate) {
        command = theCommand;
        coordinate = Optional.of(theCoordinate);
    }

    /**
     * Return the {@link Optional} {@link Command}.
     * @return the {@link Optional} {@link Command}
     */
    Command getCommand() {
        return command;
    }

    /**
     * Return the {@link Optional} {@link Coordinate}.
     * @return the {@link Optional} {@link Coordinate}
     */
    Optional<Coordinate> getCoordinate() {
        return coordinate;
    }

}
