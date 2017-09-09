package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * Created by id445083 on 13.10.2015.
 *
 * @author Henrik and Maxi
 */
public class InputGetter {
    private GridImpl grid;
    private int row;
    private int column;
    private State[] inStates = {State.NOTHING, State.NOTHING, State.NOTHING};
    private char orientation;

    /**
     * constructor for InputGetter
     *
     * @param grid   grid to update
     * @param row         row index
     * @param column      column index
     * @param orientation orientation of the cell
     */
    public InputGetter(GridImpl grid, int row, int column, char orientation) {
        this.grid = grid;
        this.row = row;
        this.column = column;
        this.orientation = orientation;
    }


    /**
     * getter for in state
     * @param i index
     * @return in State
     */
    public State getInState(int i) {
        return inStates[i];
    }

    /**
     * switch statement that creates the arguments of the InputGetter
     *
     * @return InputGetter
     */
    public InputGetter invoke() {
        switch (orientation) {
            case '>':
                setNorthState(0);
                setSouthState(1);
                setWestState(2);
                break;
            case '^':
                setEastState(0);
                setSouthState(1);
                setWestState(2);
                break;
            case '<':
                setNorthState(0);
                setEastState(1);
                setSouthState(2);
                break;
            default: // case 'v'
                setNorthState(0);
                setEastState(1);
                setWestState(2);
        }
        return this;
    }

    /**
     * setter for south state
     *
     * @param i index of array
     */
    private void setSouthState(int i) {
        if (row < grid.cells.length - 1 && grid.cells
                [row + 1][column].accessibleDirections.contains("N")) {
            inStates[i] = grid.cells[row + 1][column].
                    getState(row, column, row + 1, column);
        }
    }

    /**
     * setter for west state
     *
     * @param i index of array
     */
    private void setWestState(int i) {
        if (column > 0 && grid.cells[row][column - 1]
                .accessibleDirections.contains("E")) {
            inStates[i] = grid.cells[row][column - 1].
                    getState(row, column, row, column - 1);
        }
    }

    /**
     * setter for east state
     *
     * @param i index of array
     */
    private void setEastState(int i) {
        if (column > grid.cells[0].length - 1 && grid.cells
                [row][column + 1].accessibleDirections.contains("W")) {
            inStates[i] = grid.cells[row][column + 1].
                    getState(row, column, row, column + 1);
        }
    }

    /**
     * setter for north state
     *
     * @param i index of array
     */
    private void setNorthState(int i) {
        if (row > 0 && grid.cells[row - 1][column]
                .accessibleDirections.contains("S")) {
            inStates[i] = grid.cells[row - 1][column].
                    getState(row, column, row - 1, column);
        }
    }
}
