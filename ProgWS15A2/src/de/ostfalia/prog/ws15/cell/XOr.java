package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * @author Henrik und Maxi
 * Created by Maxi on 21.10.2015.
 */
public class XOr extends Cell {
    /**
     * constructor for xor cell
     * 
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation is not between 0 and 3
     */
    public XOr(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'X';
    }

    /**
     * update xor cell
     *
     * @param row    row index
     * @param column column index
     */
    @Override
    public void update(GridImpl grid, int row, int column) {
        boolean in1 = false;
        boolean in2 = false;
        switch (this.orientation) {
            case '>':
            case '<':
                if (row > 0 && grid.cells[row - 1][column]
                        .accessibleDirections.contains("S")) {
                    in1 = grid.cells[row - 1][column].getState(row,
                            column, row - 1, column) == State.ONE;
                }
                if (row < grid.cells.length - 1 && grid
                        .cells[row + 1][column]
                        .accessibleDirections.contains("N")) {
                    in2 = grid.cells[row + 1][column].getState(row,
                            column, row + 1, column) == State.ONE;
                }
                break;
            case '^':
            case 'v':
                if (column > 0 && grid.cells[row][column - 1]
                        .accessibleDirections.contains("E")) {
                    in1 = grid.cells[row][column - 1].getState(row,
                            column, row, column - 1) == State.ONE;
                }
                if (column < grid.cells[0].length - 1 &&
                        grid.cells[row][column + 1]
                                .accessibleDirections.contains("W")) {
                    in2 = grid.cells[row][column + 1].
                            getState(row, column, row, column + 1) == State.ONE;
                }
                break;
            default:
                break;
        }
        if (in1 ^ in2) {
            this.setState(State.ONE);
        } else {
            this.setState(State.ZERO);
        }
    }
}
