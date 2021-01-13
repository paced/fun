package com.anonymous.projects.rover.main;

import com.anonymous.projects.rover.automata.Rover;
import com.anonymous.projects.rover.model.Command;
import com.anonymous.projects.rover.model.Coordinate;
import com.anonymous.projects.rover.util.IOUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Runner class for a {@link Rover} automaton to move on a table.
 */
public class RoverMain {

    /** The name of the expected input filename. */
    static final String INPUT_FILENAME = "input.txt";

    /**
     * Read in the input file, process it, and output to STDOUT.
     */
    public static void main(final String[] args) throws IOException {
        /* Instantiate rover. */

        final Rover rover = new Rover();

        /* Process input file. */

        final List<String> commands = IOUtils.readFileLines(INPUT_FILENAME);

        /* Feed commands to rover. */

        commands.forEach(command -> extractLine(command).ifPresent(commandPair -> {
            /* Pass only well-formed commands to the rover. */

            if (commandPair.getCommand().equals(Command.REPORT) && !commandPair.getCoordinate().isPresent()) {
                rover.report();
            } else if (commandPair.getCommand().equals(Command.MOVE) && commandPair.getCoordinate().isPresent()) {
                rover.move(commandPair.getCoordinate().get());
            } else if (commandPair.getCommand().equals(Command.PLACE) && commandPair.getCoordinate().isPresent()) {
                rover.place(commandPair.getCoordinate().get());
            }
        }));
    }

    /**
     * From a line of text, attempt to extract a command.
     *
     * @param line the line to parse
     * @return a {@link CommandPair}, optionally
     */
    private static Optional<CommandPair> extractLine(final String line) {
        final List<String> lineFragments = Arrays.asList(line.split(" "));

        /* We expect exactly two Strings delimited by a space. */

        if (lineFragments.size() > 2) {
            return Optional.empty();
        }

        /* Extract a command. */

        Optional<Command> command = parseCommand(lineFragments.get(0));

        if (!command.isPresent()) {
            return Optional.empty();
        }

        /* Extract coordinates. */

        try {
            if (lineFragments.size() == 2) {
                Optional<Coordinate> coordinate = parseCoordinate(lineFragments.get(1));

                return coordinate.map(theCoordinate -> new CommandPair(command.get(), theCoordinate));
            } else if (lineFragments.size() == 1) {
                return Optional.of(new CommandPair(command.get()));
            } else {
                throw new IllegalArgumentException(
                        "Input is >2 arguments but was not sterilised before attempting to find coordinates.");
            }

        } catch (final NumberFormatException e) {
            /* Coordinate invalid; entire line invalid. */

            return Optional.empty();
        }
    }

    /**
     * From a command {@link String}, extract a {@link Command} if possible.
     *
     * @param command the command {@link String}
     * @return a {@link Command}, optionally
     */
    private static Optional<Command> parseCommand(final String command) {
        try {
            return Optional.of(Command.valueOf(command.toUpperCase()));
        } catch (final IllegalArgumentException e) {
            /* Unknown command. */

            return Optional.empty();
        }
    }

    /**
     * From a coordinate {@link String}, extract a {@link Coordinate} if possible.
     *
     * @param coordinate the coordinate {@link String}
     * @return a {@link Coordinate}, optionally
     */
    private static Optional<Coordinate> parseCoordinate(final String coordinate) {
        final List<String> coordinateFragments = Arrays.asList(coordinate.split(","));

        if (coordinateFragments.size() != 2) {
            throw new NumberFormatException("Not a valid coordinate!");
        }

        /* May throw NumberFormatException but will be caught. */

        return Optional.of(new Coordinate(Integer.parseInt(coordinateFragments.get(0)),
                    Integer.parseInt(coordinateFragments.get(1))));
    }

}
