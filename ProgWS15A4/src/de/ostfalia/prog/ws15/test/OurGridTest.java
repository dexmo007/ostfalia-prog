import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.Grid.IllegalCellTypeException;
import de.ostfalia.prog.ws15.GridFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Henrik and Maxi
 */
public class OurGridTest {
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
     * test a wire out of crosses that ends in a not cell
     *
     * @throws IllegalCellTypeException wrong cell type
     */
    @Test
    public void testNotAndCross() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(2, 2);
        g1.createCell(0, 0, 'E', 3);
        g1.createCell(0, 1, 'N', 1);
        g1.createCell(1, 0, 'C', 3);
        g1.createCell(1, 1, 'C', 0);
        String res = g1.getCellString(0, 1);
        assertSimilar("Not at the End should be ", "N-^", res);
        g1.update();
        assertSimilar("Not at the End should after update still be ",
                "N-^", res);
        g1.update();
        res = g1.getCellString(0, 1);
        assertSimilar("Not at the End should after second update be ",
                "N0^", res);
    }

    /**
     * Check an "or" cell that is surrounded by 0, then one cell changes to 1
     *
     * @throws IllegalCellTypeException wrong cell type
     */
    @Test
    public void testOr() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(3, 3);
        g1.createCell(0, 1, 'Z', 3);
        g1.createCell(1, 0, 'Z', 0);
        g1.createCell(1, 2, 'Z', 2);
        g1.createCell(1, 1, 'R', 3);
        g1.update();
        String res = g1.getCellString(1, 1);
        assertSimilar("The or Cell should be ", "R0v", res);
        g1.update();
        res = g1.getCellString(1, 1);
        assertSimilar("The or Cell should be ", "R0v", res);
        g1.createCell(0, 1, 'E', 3);
        g1.update();
        res = g1.getCellString(1, 1);
        assertSimilar("The or Cell should be ", "R1v", res);
    }

    /**
     * check an "and" cell that is surrounded by 1 and one 0, then all 1
     *
     * @throws IllegalCellTypeException if cell type cannot be resolved
     */
    @Test
    public void testAnd() throws IllegalCellTypeException {
        Grid g1 = GridFactory.emptyGrid(3, 3);
        g1.createCell(0, 1, 'Z', 3);
        g1.createCell(1, 0, 'E', 0);
        g1.createCell(1, 2, 'E', 2);
        g1.createCell(1, 1, 'A', 3);
        g1.update();
        String res = g1.getCellString(1, 1);
        assertSimilar("The or Cell should be ", "A0v", res);
        g1.update();
        res = g1.getCellString(1, 1);
        assertSimilar("The or Cell should be ", "A0v", res);
        g1.createCell(0, 1, 'E', 3);
        g1.update();
        res = g1.getCellString(1, 1);
        assertSimilar("The or Cell should be ", "A1v", res);
    }
}
