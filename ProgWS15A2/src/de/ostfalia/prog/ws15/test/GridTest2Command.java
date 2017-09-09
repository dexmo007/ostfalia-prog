package de.ostfalia.prog.ws15.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import org.junit.Test;

import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.GridFactory;
import de.ostfalia.prog.ws15.Grid.IllegalCellTypeException;
import de.ostfalia.prog.ws15.view.CommandLine;

/**
 * Tests for Assignment 2
 * @author weimar
 *
 */
public class GridTest2Command {

    /**
     * Helper method to compare strings with disregard to extra spaces.
     * @param message Error message
     * @param expected should be this
     * @param result but was this
     */
    static void assertSimilar(String message, String expected, String result) {
        assertEquals(message, expected.replaceAll(" ", ""),
                result.replaceAll(" ", ""));
    }

    /**
     * A1
     * tests an empty Grid.
     */
    @Test
    public void testEmpty() {
        String input = "g 1,1\n";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one empty cell should be ", "--", res);
    }

    /**
     * A1
     * tests a grid with one zero cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testZero() throws IllegalCellTypeException {
        String input = "g 1,1\ncZ0 0,0\n";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one zero cell should be", "Z0", res);
    }

    /**
     * A1
     * tests a grid with one zero cell and does one update()
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testZeroUpdate() throws IllegalCellTypeException {
        String input = "g 1,1\ncZ0 0,0\ns\n";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res = g1.getCellString(0, 0);
        assertSimilar(
                "Grid with only one zero cell should after update still be", "Z0",
                res);
    }

    /**
     * A1
     * tests a grid with one "one" cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testOne() throws IllegalCellTypeException {
        String input = "g 1,1\ncE0 0,0\n";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one ONE cell should be", "E1  ", res);
    }

    /**
     * A1
     * tests a grid with one "one" cell and does one update
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testOneUpdate() throws IllegalCellTypeException {
        String input = "g 1,1\ncE0 0,0\ns\n";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one ONE cell should after update still be",
                "E1  ", res);
    }

    /**
     * A1
     * tests a grid with one "F (signal)" cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testF() throws IllegalCellTypeException {
        String input = "g 1,1\ncF0 0,0\n";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one F cell should at first be",
                "F0  ", res);
    }

    /**
     * A1
     * tests a grid with one "F (signal)" cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testFRun() throws IllegalCellTypeException {
        String input = "g 1,1\ncF0 0,0\nr 15";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one F cell should at t=" + 15 + " be ","F1  ", res);
    }

    /**
     * A1
     * tests a grid with a zero and a wire cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitRight0() throws IllegalCellTypeException {
        String input = "g 1,2\ncZ0 0,0\ncW0 0,1\ns";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should after update be", "W0 >", res2);

    }

    /**
     * A1
     * tests a grid with a one and a wire cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitRight1() throws IllegalCellTypeException {
        String input = "g 1,2\ncE0 0,0\ncW0 0,1\nq 2";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should after update be", "W1 >", res2);

    }

    /**
     * A1
     * tests a grid with a zero and a not cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitRight0() throws IllegalCellTypeException {
        String input = "g 1,2\ncZ0 0,0\ncN0 0,1\ns";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with not cell should after update be", "N1 >", res2);

    }

    /**
     * A1
     * tests a grid with a one and a not cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitRight1() throws IllegalCellTypeException {
        String input = "g 1,2\ncE0 0,0\ncN0 0,1\ns";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);
        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should after update be", "N0 >", res2);

    }


    /**
     * A1
     * tests a grid with a one and three wire cells in a row
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitRight3() throws IllegalCellTypeException {
        String input = "g 1,4\ncE0 0,0\ncW0 0,1\ncW0 0,2\ncW0 0,3\nr 2";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res3 = g1.getCellString(0, 3);
        assertSimilar("Grid with wire cell should after 2 updates be", "W1 >",
                res3);

    }


    /**
     * A1
     * tests a grid with an and cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testAnd() throws IllegalCellTypeException {
        String input = "g 3,2\ncE0 0,1\ncE0 1,0\ncA0 1,1\ncE0 2,1\nr 1";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);


        String res2 = g1.getCellString(1, 1);
        assertSimilar("Grid with and cell should after 1 updates be", "A1> ",
                res2);
    }

    /**
     * A1
     * tests a grid with an or cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testOr() throws IllegalCellTypeException {
        String input = "g 3,2\ncZ0 0,1\ncZ0 1,0\ncR0 1,1\ncZ0 2,1\nr 1";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        // -- E1
        // E1 R?
        String res2 = g1.getCellString(1, 1);
        assertSimilar("Grid with  \"or\" cell should after 1 updates be", "R0> ",
                res2);

    }



    /**
     * A2
     * tests a grid with not cell oriented to the left
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitLeft1() throws IllegalCellTypeException {
        String input = "g 1,2\ncE0 0,1\ncN2 0,0\ns";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res2 = g1.getCellString(0, 0);
        assertSimilar("Grid with NOT cell facing left should after update be",
                "N0 <", res2);

    }

    /**
     * A2
     * tests a grid with not cell oriented up
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitUp1() throws IllegalCellTypeException {
        String input = "g 2,1\ncE0 1,0\ncN1 0,0\ns";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res2 = g1.getCellString(0, 0);
        assertSimilar("Grid with NOT cell facing up should after update be",
                "N0 ^", res2);

    }

    /**
     * A2
     * tests a grid with not cell oriented down
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitDown1() throws IllegalCellTypeException {
        String input = "g 2,1\ncE0 0,0\ncN3 1,0\ns";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res2 = g1.getCellString(1, 0);
        assertSimilar("Grid with NOT cell facing down should after update be",
                "N0 v", res2);
    }


    /**
     * A2
     * tests a grid with three wire cells oriented left
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitLeft3() throws IllegalCellTypeException {
        String input = "g 1,4\ncE0 0,3\ncW2 0,0\ncW2 0,1\ncW2 0,2\nr 2";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res3 = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell should after 2 updates be", "W1 <",
                res3);

    }

    /**
     * A2
     * tests a grid with a bridge orientation up
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testBridgeRdir1() throws IllegalCellTypeException { // Rotated
        String input = "g 3,3\ncE0 1,0\ncZ2 2,1\ncB1 1,1\ncW1 0,1\ncW0 1,2\nr 2";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res = g1.getCellString(1, 1);
        assertSimilar("Grid with bridge cell should later be", "B01^", res);
        res = g1.getCellString(0, 1);
        assertSimilar("Grid with bridge cell should later be", "W0^ ", res);
        res = g1.getCellString(1, 2);
        assertSimilar("Grid with bridge cell should later be", "W1> ", res);
    }





}
