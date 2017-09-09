package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.Grid;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public class CellFactory {
    /**
     * creates a cell
     *
     * @param type        type of the cell to be created
     * @param orientation orientation of the cell
     * @return created cell
     * @throws Grid.IllegalCellTypeException    if type cannot be resolved
     */
    public static Cell createCell(char type, int orientation)
            throws Grid.IllegalCellTypeException {
        Cell res;
        switch (type) {
            case 'W':
                res = new Wire(orientation);
                break;
            case 'Z':
                res = new Zero();
                break;
            case 'E':
                res = new One();
                break;
            case 'F':
                res = new Frequency();
                break;
            case 'A':
                res = new And(orientation);
                break;
            case 'R':
                res = new Or(orientation);
                break;
            case 'N':
                res = new Not(orientation);
                break;
            case 'C':
                res = new Cross(orientation);
                break;
            case 'B':
                res = new Bridge(orientation);
                break;
            case 'X':
                res = new XOr(orientation);
                break;
            case 'I':
                res = new Input();
                break;
            case 'O':
                res = new Output(orientation);
                break;
            case 'D':
                res = new FlipFlop(orientation);
                break;
            default:
                throw new Grid.IllegalCellTypeException("Invalid cell type!");
        }
        return res;
    }
}
