package de.ostfalia.prog.ws15.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.Grid.IllegalCellTypeException;
import de.ostfalia.prog.ws15.GridFactory;

/**
 * Tests for Assignment 2
 * @author weimar
 *
 */
public class ZweiGridsTest {

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
     * tests two grids with one zero  and one One cell
     * @throws IllegalCellTypeException does not happen
     */
    @Test
    public void testZeroOne() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(1, 1);
        g1.createCell(0, 0, 'Z', 0);
        Grid g2 = GridFactory.emptyGrid(1, 1);
        g2.createCell(0, 0, 'E', 0);
        String res = g1.getCellString(0, 0);
        assertSimilar("Static variable in Grid? Grid 1 with only one ZERO cell should be", "Z0", res);
        String res2 = g2.getCellString(0, 0);
        assertSimilar("Static variable in Grid? Grid 2 with only one ONE cell should be", "E1", res2);
    }



}
