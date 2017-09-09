package de.ostfalia.prog.ws15.test;

import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.Grid.IllegalCellTypeException;
import de.ostfalia.prog.ws15.GridFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for Assignment 1
 *
 * @author weimar
 */
public class GridTest1 {

    /**
     * Helper method to compare strings with disregard to extra spaces.
     *
     * @param message  Error message
     * @param expected should be this
     * @param result   but was this
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
        Grid g1 = GridFactory.emptyGrid(1, 1);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one empty cell should be ", "--", res);
    }

    /**
     * A1
     * tests a grid with one zero cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testZero() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'Z', 0);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one zero cell should be", "Z0", res);
    }

    /**
     * A1
     * tests a grid with one zero cell and does one update()
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testZeroUpdate() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'Z', 0);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one zero cell should be", "Z0  ", res);
        g1.update();
        res = g1.getCellString(0, 0);
        assertSimilar(
                "Grid with only one zero cell should after update still be", "Z0",
                res);
    }

    /**
     * A1
     * tests a grid with one "one" cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testOne() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'E', 0);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one ONE cell should be", "E1  ", res);
    }

    /**
     * A1
     * tests a grid with one "one" cell and does one update
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testOneUpdate() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'E', 0);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one ONE cell should be", "E1  ", res);
        g1.update();
        res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one ONE cell should after update still be",
                "E1  ", res);
    }

    /**
     * A1
     * tests a grid with one "F (signal)" cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testF() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'F', 0);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one F cell should at first be",
                "F0  ", res);
        int t = 0;
        while (t < 3 * 5) {
            g1.update();
            t++;
            res = g1.getCellString(0, 0);
            assertSimilar("Grid with only one F cell should at t=" + t + " be",
                    (t % (2 * 5) < 5 ? "F0  " : "F1  "), res);
        }
    }

    /**
     * A1
     * tests a grid with a zero and a wire cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitRight0() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 2);
        g1.createCell(0, 0, 'Z', 0);
        g1.createCell(0, 1, 'W', 0);
        String res = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should initially be", "W- >", res);
        g1.update();
        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should after update be", "W0 >", res2);

    }

    /**
     * A1
     * tests a grid with a one and a wire cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitRight1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 2);
        g1.createCell(0, 0, 'E', 0);
        g1.createCell(0, 1, 'W', 0);
        String res = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should initially be", "W- >", res);
        g1.update();
        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should after update be", "W1 >", res2);

    }

    /**
     * A1
     * tests a grid with a zero and a not cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitRight0() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 2);
        g1.createCell(0, 0, 'Z', 0);
        g1.createCell(0, 1, 'N', 0);
        String res = g1.getCellString(0, 1);
        assertSimilar("Grid with not cell should initially be", "N- >", res);
        g1.update();
        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with not cell should after update be", "N1 >", res2);

    }

    /**
     * A1
     * tests a grid with a one and a not cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitRight1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 2);
        g1.createCell(0, 0, 'E', 0);
        g1.createCell(0, 1, 'N', 0);
        String res = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should initially be", "N- >", res);
        g1.update();
        String res2 = g1.getCellString(0, 1);
        assertSimilar("Grid with wire cell should after update be", "N0 >", res2);

    }


    /**
     * A1
     * tests a grid with a one and three wire cells in a row
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitRight3() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 4);
        g1.createCell(0, 0, 'E', 0);
        g1.createCell(0, 1, 'W', 0);
        g1.createCell(0, 2, 'W', 0);
        g1.createCell(0, 3, 'W', 0);
        String res = g1.getCellString(0, 3);
        assertSimilar("Grid with wire cell should initially be", "W- >", res);
        g1.update();
        String res2 = g1.getCellString(0, 3);
        assertSimilar("Grid with wire cell should after one update still be",
                "W- >", res2);
        g1.update();
        String res3 = g1.getCellString(0, 3);
        assertSimilar("Grid with wire cell should after 2 updates be", "W1 >",
                res3);

    }


    /**
     * A1
     * tests a grid with an and cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testAnd() throws IllegalCellTypeException {
        // -- E1
        // E1 A?
        Grid g1 = GridFactory.emptyGrid(3, 2);
        g1.createCell(0, 1, 'E', 0);
        g1.createCell(1, 0, 'E', 0);
        g1.createCell(1, 1, 'A', 0);
        g1.createCell(2, 1, 'E', 0);

        String res = g1.getCellString(1, 1);
        assertSimilar("Grid with and cell should initially be", "A-> ", res);
        g1.update();
        String res2 = g1.getCellString(1, 1);
        assertSimilar("Grid with and cell should after 1 updates be", "A1> ",
                res2);

        g1.createCell(0, 1, 'Z', 0);
        g1.update();
        res2 = g1.getCellString(1, 1);
        assertSimilar(
                "Grid with and cell should after 1 updates with a zero neighbor be",
                "A0> ", res2);

    }

    /**
     * A1
     * tests a grid with an or cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testOr() throws IllegalCellTypeException {
        // -- E1
        // E1 A?
        Grid g1 = GridFactory.emptyGrid(3, 2);
        g1.createCell(0, 1, 'Z', 0);
        g1.createCell(1, 0, 'Z', 0);
        g1.createCell(1, 1, 'R', 0);
        g1.createCell(2, 1, 'Z', 0);

        String res = g1.getCellString(1, 1);
        assertSimilar("Grid with \"or\" cell should initially be", "R-> ", res);
        g1.update();
        String res2 = g1.getCellString(1, 1);
        assertSimilar("Grid with  \"or\" cell should after 1 updates be", "R0> ",
                res2);

        g1.createCell(0, 1, 'E', 0);
        g1.update();
        res2 = g1.getCellString(1, 1);
        assertSimilar(
                "Grid with  \"or\" cell should after 1 updates with a one neighbor be",
                "R1> ", res2);

    }


    /**
     * A1
     * tests a grid with and, or, and not cells
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testAndOrNot1() throws IllegalCellTypeException {

        Grid g1 = GridFactory.emptyGrid(4, 5);

        g1.createCell(0, 0, 'F', 0);
        g1.createCell(1, 0, 'E', 0);
        g1.createCell(2, 0, 'Z', 0);

        g1.createCell(0, 1, 'C', 0);
        g1.createCell(1, 1, 'R', 0);
        g1.createCell(2, 1, 'W', 0);

        g1.createCell(0, 2, 'N', 0);
        g1.createCell(1, 2, 'W', 0);
        g1.createCell(2, 2, 'W', 0);

        g1.createCell(0, 3, 'A', 0);
        g1.createCell(1, 3, 'C', 0);
        g1.createCell(2, 3, 'W', 0);
        // g1.createCell(2, 3, 'B', 0);
        // g1.createCell(3, 3, 'W', 3);

        g1.createCell(0, 4, 'W', 0);
        g1.createCell(1, 4, 'W', 0);
        g1.createCell(2, 4, 'W', 0);

        String res = g1.getCellString(0, 4);
        assertSimilar("Grid with andornot should initially be", "W-> ", res);

        runUpdates(g1, 5);
        res = g1.getCellString(0, 4);
        assertSimilar("Grid with andornot should at t=5 be", "W1> ", res);

        runUpdates(g1, 5);

        res = g1.getCellString(0, 4);
        assertSimilar("Grid with andornot should at t=10, row 0 col 4  be",
                "W0> ", res);

        res = g1.getCellString(1, 4);
        assertSimilar("Grid with andornot should at t=10, row 1 col 4  be",
                "W1> ", res);

        res = g1.getCellString(2, 4);
        assertSimilar("Grid with andornot should at t=10, row 2 col 4  be",
                "W0> ", res);

        res = g1.getCellString(1, 3);
        assertSimilar("Grid with andornot should at t=10, row 1 col 3 be",
                "C1> ", res);

    }

    /**
     * run the specified number of updates.
     *
     * @param g1    which grid?
     * @param steps number of update steps.
     */
    private void runUpdates(Grid g1, int steps) {
        for (int i = 0; i < steps; i++) {
            g1.update();
        }
    }

    /**
     * A1
     * tests a grid with one zero cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test(expected = IllegalCellTypeException.class)
    public void testException() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'Y', 0);
        String res = g1.getCellString(0, 0);
        fail("Grid with only one \"Y\" cell should not exist " + res);
    }

}

