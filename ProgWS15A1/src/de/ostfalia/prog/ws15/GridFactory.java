package de.ostfalia.prog.ws15;

/**
 * @author Henrik and Maxi
 */
public class GridFactory {
    /**
     * static method to construct an empty grid using the grid implementation
     * @param rows number of rows
     * @param columns number of columns
     * @return a grid using gridinit
     */
    public static Grid emptyGrid(int rows, int columns) {
        return new GridInit(rows, columns);
    }
}
