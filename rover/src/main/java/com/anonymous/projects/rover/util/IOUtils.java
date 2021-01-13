package com.anonymous.projects.rover.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Utilities for opening files. */
public class IOUtils {

    /**
     * Hidden private constructor.
     */
    private IOUtils() {
        /* Empty. */
    }

    /**
     * Extract all of the lines from the input file
     *
     * @return the lines found in the input file
     */
    public static List<String> readFileLines(final String filename) throws IOException {
        final File inputFile = new File(filename);

        return org.apache.commons.io.FileUtils.readLines(inputFile, "UTF-8").stream().filter(line ->
                !line.trim().isEmpty()).collect(Collectors.toList());
    }

    /**
     * Extract STDOUT lines after a synchronous method call.
     *
     * @param function the synchronous method to call
     * @return the lines found from STDOUT
     */
    public static List<String> runAndReadStdoutLines(final Runnable function) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(stream));
        function.run();
        System.setOut(System.out);

        return Arrays.asList(stream.toString().split("\n"));
    }

}
