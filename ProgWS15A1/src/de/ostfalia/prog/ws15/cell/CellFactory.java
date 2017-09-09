package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.Grid;

/**
 * Created by Henrik on 9/29/2015.
 * @author Henrik and Maxi
 */
public class CellFactory {
    /**
     * creates a cell
     * @param type type of the cell to be created
     * @param orientation orientation of the cell
     * @return created cell
     * @throws Grid.IllegalCellTypeException if type cannot be resolved
     * @throws Cell.IllegalOrientationException if orientation
     * cannot be resolved
     */
    public static Cell createCell(char type, int orientation)
            throws Grid.IllegalCellTypeException,
            Cell.IllegalOrientationException {
        switch (type) {
            case 'W':
                return new Wire(orientation);
            case 'Z':
                return new Zero(orientation);
            case 'E':
                return new One(orientation);
            case 'F':
                return new Frequency(orientation);
            case 'A':
                return new And(orientation);
            case 'R':
                return new Or(orientation);
            case 'N':
                return new Not(orientation);
            case 'C':
                return new Cross(orientation);
            default:
                throw new Grid.IllegalCellTypeException("Invalid cell type!");

        }
    }
}
