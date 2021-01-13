package com.anonymous.projects.rover.main;

import com.anonymous.projects.rover.util.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Unit tests for {@link RoverMain}. */
public class RoverMainTest {

    /**
     * Test a nominal short output where a validly placed item can be reported.
     */
    @Test
    public void elementaryTest1() throws IOException {
        runAndAssert(Arrays.asList("PLACE 0,0", "REPORT"), Arrays.asList("P: (0,0)"));
    }

    /**
     * Test a nominal short output where a valid move step happens after a valid place.
     */
    @Test
    public void elementaryTest2() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 0,0", "MOVE 0,1"), Arrays.asList("M: (0,0) (0,1)"));
    }

    /**
     * Test a nominal short output where a valid place step happens after a valid place, then is reported.
     */
    @Test
    public void elementaryTest3() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 0,0", "MOVE 1,1", "REPORT"), Arrays.asList("M: (0,0) (1,0) (1,1)",
                "P: (1,1)"));
    }

    /**
     * Test that multiple valid places only recognise the first one.
     */
    @Test
    public void multiplePlace() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 0,0", "PLACE 0,1", "REPORT"), Arrays.asList("P: (0,0)"));
    }

    /**
     * Test that multiple moves creates the correct output.
     */
    @Test
    public void multipleMove() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 0,0", "MOVE 1,2", "MOVE 2,1"), Arrays.asList("M: (0,0) (1,0) (1,1) (1,2)",
                "M: (1,2) (2,2) (2,1)"));
    }

    /**
     * Attempt to place the rover invalidly, then report.
     */
    @Test
    public void invalidPlace1() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 6,6", "REPORT"), Collections.emptyList());
    }

    /**
     * Attempt to place the rover invalidly, then move.
     */
    @Test
    public void invalidPlace2() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 6,6", "MOVE 1,2"), Collections.emptyList());
    }

    /**
     * Attempt to place the rover, then place it validly, then report.
     */
    @Test
    public void invalidThenValid() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 6,6", "PLACE 0,0", "REPORT"), Arrays.asList("P: (0,0)"));
    }

    /**
     * Place the rover, move it validly, try to drive it off the edge, and report.
     */
    @Test
    public void invalidFalling1() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 0,0", "MOVE 6,6", "REPORT"), Arrays.asList("P: (0,0)"));
    }

    /**
     * Place the rover, move it validly, try to drive it off the edge, then move it validly.
     */
    @Test
    public void invalidFalling2() throws IOException  {
        runAndAssert(Arrays.asList("PLACE 0,0", "MOVE 6,6", "MOVE 1,0"), Arrays.asList("M: (0,0) (1,0)"));
    }

    /**
     * Run the main method and assert that the output is what is expected.
     *
     * @param inputs
     * @throws IOException
     */
    private void runAndAssert(final List<String> inputs, final List<String> expected) throws IOException {
        writeInput(inputs);

        final List<String> outputs = IOUtils.runAndReadStdoutLines(() -> {
            try {
                RoverMain.main(null);
            } catch (IOException e) {
                /* Do nothing. */
            }
        });

        /* No output can also be a single output with an empty String. */

        if (outputs.size() > 1 || !outputs.isEmpty() && !outputs.get(0).isEmpty()) {
            Assert.assertEquals(expected, outputs);
        }
    }

    /**
     * Write an input file using the given lines.
     *
     * @param lines the lines to write to file
     * @throws IOException an {@link IOException}
     */
    private void writeInput(final List<String> lines) throws IOException {
        final File file = new File(RoverMain.INPUT_FILENAME);

        final FileWriter fileWriter = new FileWriter(file);

        for (final String line: lines) {
            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

}
