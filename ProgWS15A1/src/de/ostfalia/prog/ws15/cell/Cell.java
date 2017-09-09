package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridInit;
import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 * @author Henrik and Maxi
 */
public abstract class Cell {

    final String exceptionMsg = "Invalid Orientation!";
    public char type;
    public State state = State.NOTHING;
    public char orientation;
    public String accessibleDirections;

    /**
     * setter for the orientation attribute
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if the orientation
     * parameter is not between 0 and 3
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
     * @param row row index
     * @param column column index
     */
    public void update(int row, int column) {
        switch (this.orientation) {
            case '>':
                if (column > 0 && GridInit.cells[row][column - 1]
                        .accessibleDirections.contains("E")) {
                    this.state = GridInit.cells[row][column - 1].state;
                }
                break;
            case '^':
                if (row < GridInit.cells.length - 1
                        && GridInit.cells[row + 1][column]
                        .accessibleDirections.contains("N")) {
                    this.state = GridInit.cells[row + 1][column].state;
                }
                break;
            case '<':
                if (column < GridInit.cells[0].length - 1
                        && GridInit.cells[row][column + 1]
                        .accessibleDirections.contains("W")) {
                    this.state = GridInit.cells[row][column + 1].state;
                }
                break;
            case 'v':
                if (row > 0 && GridInit.cells[row - 1][column]
                        .accessibleDirections.contains("S")) {
                    this.state = GridInit.cells[row - 1][column].state;
                }
                break;
            default:
                break;
        }
    }

    /**
     * string description of the cell
     * @return string description of the cell
     */
    @Override
    public String toString() {
        char currentState;
        switch (this.state) {
            case ONE:
                currentState = '1';
                break;
            case ZERO:
                currentState = '0';
                break;
            case NOTHING:
                currentState = '-';
                break;
            default:
                currentState = '-';
                break;
        }
        return "" + this.type + currentState + this.orientation;
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