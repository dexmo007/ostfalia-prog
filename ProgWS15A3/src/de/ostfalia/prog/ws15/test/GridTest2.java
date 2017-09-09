package de.ostfalia.prog.ws15.test;

import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.Grid.IllegalCellTypeException;
import de.ostfalia.prog.ws15.GridFactory;
import de.ostfalia.prog.ws15.view.CommandLine;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for Assignment 2
 *
 * @author weimar
 */
public class GridTest2 {

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
     * A2
     * tests a grid with not cell oriented to the left
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitLeft1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 2);
        g1.createCell(0, 1, 'E', 0);
        g1.createCell(0, 0, 'N', 2);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with NOT cell facing left should initially be",
                "N- <", res);
        System.out.println(g1);
        g1.update();
        String res2 = g1.getCellString(0, 0);
        assertSimilar("Grid with NOT cell facing left should after update be",
                "N0 <", res2);

    }

    /**
     * A2
     * tests a grid with not cell oriented up
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitUp1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(2, 1);
        g1.createCell(1, 0, 'E', 0);
        g1.createCell(0, 0, 'N', 1);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with NOT cell facing up should initially be",
                "N- ^", res);
        g1.update();
        String res2 = g1.getCellString(0, 0);
        assertSimilar("Grid with NOT cell facing up should after update be",
                "N0 ^", res2);

    }

    /**
     * A2
     * tests a grid with not cell oriented down
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testNotExplicitDown1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(2, 1);
        g1.createCell(0, 0, 'E', 0);
        g1.createCell(1, 0, 'N', 3);
        String res = g1.getCellString(1, 0);
        assertSimilar("Grid with NOT cell facing down should initially be",
                "N- v", res);
        g1.update();
        String res2 = g1.getCellString(1, 0);
        assertSimilar("Grid with NOT cell facing down should after update be",
                "N0 v", res2);
    }


    /**
     * A2
     * tests a grid with three wire cells oriented left
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitLeft3() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 4);
        g1.createCell(0, 3, 'E', 0);
        g1.createCell(0, 0, 'W', 2);
        g1.createCell(0, 1, 'W', 2);
        g1.createCell(0, 2, 'W', 2);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell should initially be", "W- <", res);
        g1.update();
        String res2 = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell should after one update still be",
                "W- <", res2);
        g1.update();
        String res3 = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell should after 2 updates be", "W1 <",
                res3);

    }

    /**
     * A2
     * tests a grid with cross plus three wire cells in all directions
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testCrossPlusWires() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(3, 4);
        g1.createCell(1, 0, 'E', 0);
        g1.createCell(1, 1, 'W', 0);
        g1.createCell(1, 2, 'C', 0);
        g1.createCell(0, 2, 'W', 1);
        g1.createCell(1, 3, 'W', 0);
        g1.createCell(2, 2, 'W', 3);

        String res = g1.getCellString(1, 2);
        assertSimilar("Grid with cross cell should initially be", "C-> ", res);
        g1.update();
        g1.update();
        String res2 = g1.getCellString(1, 2);
        assertSimilar("Grid with cross cell should after 2 updates be", "C1> ",
                res2);
        String res3 = g1.getCellString(1, 3);
        assertSimilar("Grid with cross plus wire cell should after 2 updates be",
                "W1> ", res3);
        res3 = g1.getCellString(0, 2);
        assertSimilar("Grid with cross plus wire cell should after 2 updates be",
                "W1^ ", res3);
        res3 = g1.getCellString(2, 2);
        assertSimilar("Grid with cross plus wire cell should after 2 updates be",
                "W1v ", res3);
    }

    /**
     * A2
     * tests a grid with cross plus three wire cells oriented left
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testCrossPlusWiresLeft() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(3, 4);
        g1.createCell(1, 3, 'E', 0);
        g1.createCell(1, 2, 'W', 2);
        g1.createCell(1, 1, 'C', 2);
        g1.createCell(0, 1, 'W', 1);
        g1.createCell(1, 0, 'W', 2);
        g1.createCell(2, 1, 'W', 3);

        String res = g1.getCellString(1, 1);
        assertSimilar("Grid with cross cell should initially be", "C-< ", res);
        g1.update();
        g1.update();
        g1.update();
        String res2 = g1.getCellString(1, 1);
        assertSimilar("Grid with cross cell should after 2 updates be", "C1< ",
                res2);
        String res3 = g1.getCellString(1, 0);
        assertSimilar("Grid with cross plus wire cell should after 2 updates be",
                "W1< ", res3);
        res3 = g1.getCellString(0, 1);
        assertSimilar("Grid with cross plus wire cell should after 2 updates be",
                "W1^ ", res3);
        res3 = g1.getCellString(2, 1);
        assertSimilar("Grid with cross plus wire cell should after 2 updates be",
                "W1v ", res3);
    }

    /**
     * A2
     * tests a grid with one Input cell
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testInput() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'I', 0);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one unset input cell should be", "I-  ",
                res);
    }

    /**
     * A2
     * tests a grid with one Input cell set to 0
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testInput0() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'I', 0);
        g1.setInputs("0");
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one input cell set to 0 should be", "I0  ",
                res);
    }

    /**
     * A2
     * tests a grid with one Input cell set to 1
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testInput1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'I', 0);
        g1.setInputs("1");
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one input cell set to 1 should be", "I1  ",
                res);
    }

    /**
     * A2
     * tests a grid with a wire orientation left
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitLeft1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 2);
        g1.createCell(0, 1, 'E', 0);
        g1.createCell(0, 0, 'W', 2);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell facing left should initially be",
                "W- <", res);
        System.out.println(g1);
        g1.update();
        String res2 = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell facing left should after update be",
                "W1 <", res2);

    }

    /**
     * A2
     * tests a grid with a wire orientation up
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testWireExplicitUp1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(2, 1);
        g1.createCell(1, 0, 'E', 0);
        g1.createCell(0, 0, 'W', 1);
        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell facing up should initially be",
                "W- ^", res);
        g1.update();
        String res2 = g1.getCellString(0, 0);
        assertSimilar("Grid with wire cell facing up should after update be",
                "W1 ^", res2);

    }

    /**
     * A2
     * tests a grid with a wire orientation down
     *
     * @throws IllegalCellTypeException does not happen
     */

    @Test
    public void testWireExplicitDown1() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(2, 1);
        g1.createCell(0, 0, 'E', 0);
        g1.createCell(1, 0, 'W', 3);
        String res = g1.getCellString(1, 0);
        assertSimilar("Grid with wire cell facing down should initially be",
                "W- v", res);
        g1.update();
        String res2 = g1.getCellString(1, 0);
        assertSimilar("Grid with wire cell facing down should after update be",
                "W1 v", res2);
    }


    /**
     * A2
     * tests a grid with a bridge orientation right
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testBridgeR() throws IllegalCellTypeException {
        // -- E1
        // E1 A?
        Grid g1 = GridFactory.emptyGrid(3, 3);
        g1.createCell(0, 1, 'E', 0);
        g1.createCell(1, 0, 'Z', 0);
        g1.createCell(1, 1, 'B', 0);
        g1.createCell(1, 2, 'W', 0);
        g1.createCell(2, 1, 'W', 3);

        String res = g1.getCellString(2, 1);
        assertSimilar("Grid with bridge cell should initially be", "W-v ", res);
        res = g1.getCellString(1, 2);
        assertSimilar("Grid with bridge cell should initially be", "W-> ", res);
        g1.update();
        System.out.println("BridgeR\n" + g1.toString());
        g1.update();
        System.out.println("BridgeR\n" + g1.toString());
        res = g1.getCellString(2, 1);
        assertSimilar("Grid with bridge cell should later be", "W1v ", res);
        res = g1.getCellString(1, 2);
        assertSimilar("Grid with bridge cell should later be", "W0> ", res);
        g1.createCell(0, 1, 'Z', 0);
        g1.createCell(1, 0, 'E', 0);
        g1.update();
        g1.update();
        res = g1.getCellString(2, 1);
        assertSimilar("Grid with bridge cell should After switch0<->1 be",
                "W0v ", res);
        res = g1.getCellString(1, 2);
        assertSimilar("Grid with bridge cell should later be", "W1> ", res);

    }

    /**
     * A2
     * tests a grid with a bridge orientation right, one cell down
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testBridgeRLower() throws IllegalCellTypeException {
        // -- E1
        // E1 A?
        int y = 1;
        Grid g1 = GridFactory.emptyGrid(4, 3);
        g1.createCell(0 + y, 1, 'E', 0);
        g1.createCell(1 + y, 0, 'Z', 0);
        g1.createCell(1 + y, 1, 'B', 0);
        g1.createCell(1 + y, 2, 'W', 0);
        g1.createCell(2 + y, 1, 'W', 3);

        String res = g1.getCellString(2 + y, 1);
        assertSimilar("Grid with bridge cell should initially be", "W-v ", res);
        res = g1.getCellString(1 + y, 2);
        assertSimilar("Grid with bridge cell should initially be", "W-> ", res);
        g1.update();
        System.out.println("BridgeRlower\n" + g1.toString());
        g1.update();
        System.out.println("BridgeRlower\n" + g1.toString());
        res = g1.getCellString(2 + y, 1);
        assertSimilar("Grid with bridge cell should later be", "W1v ", res);
        res = g1.getCellString(1 + y, 2);
        assertSimilar("Grid with bridge cell should later be", "W0> ", res);
        g1.createCell(0 + y, 1, 'Z', 0);
        g1.createCell(1 + y, 0, 'E', 0);
        g1.update();
        g1.update();
        res = g1.getCellString(2 + y, 1);
        assertSimilar("Grid with bridge cell should After switch0<->1 be",
                "W0v ", res);
        res = g1.getCellString(1 + y, 2);
        assertSimilar("Grid with bridge cell should later be", "W1> ", res);

    }

    /**
     * A2
     * tests a grid with a bridge orientation left
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testBridgeRdir2() throws IllegalCellTypeException { // Rotated
        // -- E1
        // E1 A?
        Grid g1 = GridFactory.emptyGrid(3, 3);
        g1.createCell(2, 1, 'E', 0);
        g1.createCell(1, 2, 'Z', 0);
        g1.createCell(1, 1, 'B', 2);
        g1.createCell(0, 1, 'W', 1);
        g1.createCell(1, 0, 'W', 2);

        String res = g1.getCellString(0, 1);
        assertSimilar("Grid with bridge cell should initially be", "W-^ ", res);
        res = g1.getCellString(1, 0);
        assertSimilar("Grid with bridge cell should initially be", "W-< ", res);
        g1.update();
        System.out.println("BridgeRdir2\n" + g1.toString());
        g1.update();
        System.out.println("BridgeRdir2\n" + g1.toString());
        res = g1.getCellString(0, 1);
        assertSimilar("Grid with bridge cell should later be", "W1^ ", res);
        res = g1.getCellString(1, 0);
        assertSimilar("Grid with bridge cell should later be", "W0< ", res);
        g1.createCell(2, 1, 'Z', 0);
        g1.createCell(1, 2, 'E', 0);
        g1.update();
        g1.update();
        res = g1.getCellString(0, 1);
        assertSimilar("Grid with bridge cell should After switch0<->1 be",
                "W0^ ", res);
        res = g1.getCellString(1, 0);
        assertSimilar("Grid with bridge cell should later be", "W1< ", res);

    }

    /**
     * A2
     * tests a grid with a bridge orientation down
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testBridgeRdir3() throws IllegalCellTypeException { // Rotated
        Grid g1 = GridFactory.emptyGrid(3, 3);
        g1.createCell(1, 2, 'E', 0);
        g1.createCell(0, 1, 'Z', 0);
        g1.createCell(1, 1, 'B', 3);
        g1.createCell(2, 1, 'W', 3);
        g1.createCell(1, 0, 'W', 2);

        String res = g1.getCellString(2, 1);
        assertSimilar("Grid with bridge cell should initially be", "W-v ", res);
        res = g1.getCellString(1, 0);
        assertSimilar("Grid with bridge cell should initially be", "W-< ", res);
        res = g1.getCellString(1, 1);
        assertSimilar("Grid with bridge cell should initially be", "B--v ", res);
        g1.update();
        System.out.println("BridgeRdir3\n" + g1.toString());
        g1.update();
        System.out.println("BridgeRdir3\n" + g1.toString());
        res = g1.getCellString(1, 1);
        assertSimilar("Grid with bridge cell should later be", "B01v", res);
        res = g1.getCellString(2, 1);
        assertSimilar("Grid with bridge cell should later be", "W0v ", res);
        res = g1.getCellString(1, 0);
        assertSimilar("Grid with bridge cell should later be", "W1< ", res);
        g1.createCell(1, 2, 'Z', 0);
        g1.createCell(0, 1, 'E', 0);
        g1.update();
        g1.update();
        res = g1.getCellString(2, 1);
        assertSimilar("Grid with bridge cell should After switch0<->1 be",
                "W1v ", res);
        res = g1.getCellString(1, 0);
        assertSimilar("Grid with bridge cell should later be", "W0< ", res);

    }

    /**
     * A2
     * tests a grid with a bridge orientation up
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testBridgeRdir1() throws IllegalCellTypeException { // Rotated
        Grid g1 = GridFactory.emptyGrid(3, 3);
        g1.createCell(1, 0, 'E', 0);
        g1.createCell(2, 1, 'Z', 0);
        g1.createCell(1, 1, 'B', 1);
        g1.createCell(0, 1, 'W', 1);
        g1.createCell(1, 2, 'W', 0);

        String res = g1.getCellString(0, 1);
        assertSimilar("Grid with bridge cell should initially be", "W-^ ", res);
        res = g1.getCellString(1, 2);
        assertSimilar("Grid with bridge cell should initially be", "W-> ", res);
        res = g1.getCellString(1, 1);
        assertSimilar("Grid with bridge cell should initially be", "B--^ ", res);
        g1.update();
        System.out.println("Bridge4\n" + g1.toString());
        g1.update();
        System.out.println("Bridge4\n" + g1.toString());
        res = g1.getCellString(1, 1);
        assertSimilar("Grid with bridge cell should later be", "B01^", res);
        res = g1.getCellString(0, 1);
        assertSimilar("Grid with bridge cell should later be", "W0^ ", res);
        res = g1.getCellString(1, 2);
        assertSimilar("Grid with bridge cell should later be", "W1> ", res);
        g1.createCell(1, 0, 'Z', 0);
        g1.createCell(2, 1, 'E', 0);
        g1.update();
        g1.update();
        res = g1.getCellString(0, 1);
        assertSimilar("Grid with bridge cell should After switch0<->1 be",
                "W1^ ", res);
        res = g1.getCellString(1, 2);
        assertSimilar("Grid with bridge cell should later be", "W0> ", res);

    }

//   // A2
//   @Test
//   public void testBridgeH() throws IllegalCellTypeException {
    // actually, BridgeH is redundant, equal to BridgeB rotated left.
//      // -- E1
//      // E1 A?
//      Grid g1 = GridFactory.emptyGrid(3, 3);
//      g1.createCell(0, 1, 'W', 1);
//      g1.createCell(1, 0, 'Z', 0);
//      g1.createCell(1, 1, 'H', 0);
//      g1.createCell(1, 2, 'W', 0);
//      g1.createCell(2, 1, 'E', 1);
//
//      String res = g1.getCellString(0, 1);
//      assertSimilar("Grid with bridgeL cell should initially be",
//           "W-^ ", res);
//      res = g1.getCellString(1, 2);
//      assertSimilar("Grid with bridgeL cell should initially be",
//           "W-> ", res);
//      g1.update();
//      System.out.println("Bridge\n" + g1.toString());
//      g1.update();
//      System.out.println("Bridge\n" + g1.toString());
//      res = g1.getCellString(0, 1);
//      assertSimilar("Grid with bridgeL cell should later be", "W1^ ", res);
//      res = g1.getCellString(1, 2);
//      assertSimilar("Grid with bridgeL cell should later be", "W0> ", res);
//      g1.createCell(2, 1, 'Z', 0);
//      g1.createCell(1, 0, 'E', 0);
//      g1.update();
//      g1.update();
//      res = g1.getCellString(0, 1);
//      assertSimilar("Grid with bridge cell should After switch0<->1 be",
//            "W0^ ", res);
//      res = g1.getCellString(1, 2);
//      assertSimilar("Grid with bridge cell should later be", "W1> ", res);
//
//   }

    /**
     * A2
     * tests a full adder
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testFullAdder() throws IllegalCellTypeException {
        // -- E1
        // E1 A?
        Grid g1 = GridFactory.emptyGrid(5, 8);

        g1.createCell(0, 0, 'I', 0);
        g1.createCell(1, 0, 'I', 0);
        g1.createCell(4, 0, 'I', 0);

        g1.createCell(0, 1, 'W', 0);
        g1.createCell(1, 1, 'C', 0);
        g1.createCell(2, 1, 'W', 3);
        g1.createCell(3, 1, 'C', 3);
        g1.createCell(4, 1, 'W', 0);

        g1.createCell(0, 2, 'C', 0);
        g1.createCell(1, 2, 'B', 0);
        g1.createCell(2, 2, 'X', 0);
        g1.createCell(3, 2, 'C', 0);
        g1.createCell(4, 2, 'W', 0);

        g1.createCell(0, 3, 'C', 0);
        g1.createCell(1, 3, 'A', 0);
        g1.createCell(2, 3, 'W', 0);
        g1.createCell(4, 3, 'W', 0);

        g1.createCell(1, 4, 'W', 0);
        g1.createCell(2, 4, 'C', 0);
        g1.createCell(3, 4, 'X', 0);
        g1.createCell(4, 4, 'C', 0);

        g1.createCell(1, 5, 'W', 0);
        g1.createCell(2, 5, 'A', 0);
        g1.createCell(3, 5, 'B', 1);
        g1.createCell(4, 5, 'C', 0);

        g1.createCell(1, 6, 'C', 0);
        g1.createCell(2, 6, 'R', 0);
        g1.createCell(3, 6, 'W', 0);

        g1.createCell(2, 7, 'O', 0);
        g1.createCell(3, 7, 'O', 0);

        String[] inputs = {"000", "001", "010", "011", "100", "101", "110",
                "111"};
        String[] outputs = {"00", "01", "01", "10", "01", "10", "10", "11"};

        for (int test = 0; test < 8; test++) {
            g1.setInputs(inputs[test]);
            for (int i = 0; i < 6; i++) {
                System.out
                        .println("Full Adder (input " + inputs[test] + ") t=" + i);
                g1.update();
                System.out.println(g1.toString());
            }
            String res = g1.getOutputs();
            assertEquals("Output wrong for input " + inputs[test], outputs[test],
                    res);
        }
    }

    /**
     * A2
     * tests a grid with a combination of and, or, not.
     *
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testAndOrNot() throws IllegalCellTypeException {

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
        g1.createCell(2, 3, 'B', 0);
        g1.createCell(3, 3, 'W', 3);

        g1.createCell(0, 4, 'W', 0);
        g1.createCell(1, 4, 'W', 0);
        g1.createCell(2, 4, 'W', 0);

        assertSimilar("Grid with andornot should initially be", "W-> ",
                g1.getCellString(0, 4));
        runUpdates(g1, 5);

        assertSimilar("Grid with andornot should at t=5 be", "W1> ",
                g1.getCellString(0, 4));
        runUpdates(g1, 5);

        assertSimilar("Grid with andornot should at t=10, row 0 col 4  be",
                "W0> ", g1.getCellString(0, 4));

        assertSimilar("Grid with andornot should at t=10, row 1 col 4  be",
                "W1> ", g1.getCellString(1, 4));

        assertSimilar("Grid with andornot should at t=10, row 2 col 4  be",
                "W0> ", g1.getCellString(2, 4));

        assertSimilar("Grid with andornot should at t=10 be", "B01> ",
                g1.getCellString(2, 3));

    }


    /**
     * A2
     * Tests comandLine
     *
     * @throws
     */
    @Test
    public void testCommand() {
        String input = "g 2,2\ncZ0 0,0\np";
        Reader is = new StringReader(input);

        Grid g1 = CommandLine.execute(is);

        String res = g1.getCellString(0, 0);
        assertSimilar("Grid with only one zero cell should be", "Z0", res);
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
