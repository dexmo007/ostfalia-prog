package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridInit;
import de.ostfalia.prog.ws15.State;

/**
 * Created by id445083 on 13.10.2015.
 * @author Henrik and Maxi
 */
public class InputGetter {
    private int row;
    private int column;
    private boolean in1;
    private boolean in2;
    private boolean in3;
    private State in1State;
    private State in2State;
    private State in3State;
    private char orientation;

    /**
     * constructor for InputGetter
     *
     * @param row         row index
     * @param column      column index
     * @param in1         first input
     * @param in2         second input
     * @param in3         third input
     * @param orientation orientation of the cell
     */
    public InputGetter(int row, int column, boolean in1, boolean in2,
                       boolean in3, char orientation) {
        this.row = row;
        this.column = column;
        this.in1 = in1;
        this.in2 = in2;
        this.in3 = in3;
        this.orientation = orientation;
    }

    /**
     * getter in1
     * @return first input
     */
    public boolean isIn1() {
        return in1;
    }

    /**
     * getter in2
     * @return second input
     */
    public boolean isIn2() {
        return in2;
    }

    /**
     * getter for in1State
     * @return in1State
     */
    public State getIn1State() {
        return in1State;
    }
    /**
     * getter for in2State
     * @return in2State
     */
    public State getIn2State() {
        return in2State;
    }
    /**
     * getter for in3State
     * @return in3State
     */
    public State getIn3State() {
        return in3State;
    }

    /**
     * getter in3
     * @return third input
     */

    public boolean isIn3() {
        return in3;
    }

    /**
     * switch statement that creates the arguments of the InputGetter
     * @return InputGetter
     */
    public InputGetter invoke() {
        switch (orientation) {
            case '>':
                if (row > 0 && GridInit.cells[row - 1][column]
                        .accessibleDirections.contains("S")) {
                    in1State = GridInit.cells[row - 1][column].state;
                    in1 = in1State == State.ONE;
                }
                if (row < GridInit.cells.length - 1
                        && GridInit.cells[row + 1][column]
                        .accessibleDirections.contains("N")) {
                    in2State = GridInit.cells[row + 1][column].state;
                    in2 = in2State == State.ONE;
                }
                if (column > 0 && GridInit.cells[row][column - 1]
                        .accessibleDirections.contains("E")) {
                    in3State = GridInit.cells[row][column - 1].state;
                    in3 = in3State == State.ONE;
                }
                break;
            case '^':
                if (column > GridInit.cells[0].length - 1
                        && GridInit.cells[row][column + 1]
                        .accessibleDirections.contains("W")) {
                    in1State = GridInit.cells[row][column + 1].state;
                    in1 = in1State == State.ONE;
                }
                if (row < GridInit.cells.length - 1
                        && GridInit.cells[row + 1][column]
                        .accessibleDirections.contains("N")) {
                    in2State = GridInit.cells[row + 1][column].state;
                    in2 = in2State == State.ONE;
                }
                if (column > 0 && GridInit.cells[row][column - 1]
                        .accessibleDirections.contains("E")) {
                    in3State = GridInit.cells[row][column - 1].state;
                    in3 = in3State == State.ONE;
                }
                break;
            case '<':
                if (row > 0 && GridInit.cells[row - 1][column]
                        .accessibleDirections.contains("S")) {
                    in1State = GridInit.cells[row - 1][column].state;
                    in1 = in1State == State.ONE;
                }
                if (column > GridInit.cells[0].length - 1
                        && GridInit.cells[row][column + 1]
                        .accessibleDirections.contains("W")) {
                    in2State = GridInit.cells[row][column + 1].state;
                    in2 = in2State == State.ONE;
                }
                if (row < GridInit.cells.length - 1
                        && GridInit.cells[row + 1][column]
                        .accessibleDirections.contains("N")) {
                    in3State = GridInit.cells[row + 1][column].state;
                    in3 = in3State == State.ONE;
                }
                break;
            case 'v':
                if (row > 0 && GridInit.cells[row - 1][column]
                        .accessibleDirections.contains("S")) {
                    in1State = GridInit.cells[row - 1][column].state;
                    in1 = in1State == State.ONE;
                }
                if (column > GridInit.cells[0].length - 1
                        && GridInit.cells[row][column + 1]
                        .accessibleDirections.contains("W")) {
                    in2State = GridInit.cells[row][column + 1].state;
                    in2 = in2State == State.ONE;
                }
                if (column > 0 && GridInit.cells[row][column - 1]
                        .accessibleDirections.contains("E")) {
                    in3State = GridInit.cells[row][column - 1].state;
                    in3 = in3State == State.ONE;
                }
                break;
            default:
                break;
        }
        return this;
    }
}
