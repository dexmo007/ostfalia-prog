package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public abstract class Cell {

    final String exceptionMsg = "Invalid Orientation!";
    public char type;
    public char orientation;
    public String accessibleDirections;
    private State state = State.NOTHING;

    /**
     * getter for state attribute
     * @return state of the cell
     */
    public State getState() {
        return state;
    }

    /**
     * setter for state attribute
     *
     * @param state state of the cell
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * getter for state attribute of the bridge cell
     *
     * @param row row index of the cell calling this method
     * @param column column index of the cell calling this method
     * @param rowToCheck row index of the cell to check
     * @param columnToCheck column index of the cell to check
     * @return state of the cell
     */
    public State getState(int row, int column,
                          int rowToCheck, int columnToCheck) {
        return state;
    }

    /**
     * setter for the orientation attribute
     *
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if the orientation
     *                                     parameter is not between 0 and 3
     */
    void setOrientation(int orientation) throws IllegalOrientationException {
        switch (orientation) {
            case 0:
                this.orientation = '>';
                this.accessibleDirections = "E";
                break;
            case 1:
                this.orientation = '^';
                this.accessibleDirections = "N";
                break;
            case 2:
                this.orientation = '<';
                this.accessibleDirections = "W";
                break;
            case 3:
                this.orientation = 'v';
                this.accessibleDirections = "S";
                break;
            default:
                throw new IllegalOrientationException();
        }
    }

    /**
     * default method for a cell update
     *
     * @param grid   grid to update
     * @param row    row index
     * @param column column index
     */
    public void update(GridImpl grid, int row, int column) {
        switch (this.orientation) {
            case '>':
                if (column > 0 && grid.cells[row][column - 1]
                        .accessibleDirections.contains("E")) {
                    this.state = grid.cells[row][column - 1].
                            getState(row, column, row, column - 1);
                }
                break;
            case '^':
                if (row < grid.cells.length - 1
                        && grid.cells[row + 1][column]
                        .accessibleDirections.contains("N")) {
                    this.state = grid.cells[row + 1][column].
                            getState(row, column, row + 1, column);
                }
                break;
            case '<':
                if (column < grid.cells[0].length - 1
                        && grid.cells[row][column + 1]
                        .accessibleDirections.contains("W")) {
                    this.state = grid.cells[row][column + 1].
                            getState(row, column, row, column + 1);
                }
                break;
            case 'v':
                if (row > 0 && grid.cells[row - 1][column]
                        .accessibleDirections.contains("S")) {
                    this.state = grid.cells[row - 1][column].
                            getState(row, column, row - 1, column);
                }
                break;
            default:
                break;
        }
    }

    /**
     * string description of the cell
     *
     * @return string description of the cell
     */
    @Override
    public String toString() {
        return "" + this.type + this.state + this.orientation;
    }

    /**
     * @author Henrik and Maxi
     */
    public class IllegalOrientationException extends Exception {
        private static final long serialVersionUID = 2L;

        /**
         * constructor for IllegealOrientationException
         */
        public IllegalOrientationException() {
            super(exceptionMsg);
        }
    }
}