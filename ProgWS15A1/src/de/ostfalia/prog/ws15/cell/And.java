package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.State;

/**
 * @author Henrik and Maxi
 */
public class And extends Cell {
    /**
     * constructor for And cell
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation
     * parameter is not between 0 and 3
     */
    public And(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'A';
    }

    /**
     * updates the And cell
     * @param row row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(int row, int column) {
        boolean in1 = true;
        boolean in2 = true;
        boolean in3 = true;

        InputGetter inputGetter = new InputGetter(row, column,
                in1, in2, in3, this.orientation).invoke();
        in1 = inputGetter.isIn1();
        in2 = inputGetter.isIn2();
        in3 = inputGetter.isIn3();
        boolean hasInputs = !(inputGetter.getIn1State() == State.NOTHING &&
                inputGetter.getIn2State() == State.NOTHING &&
                inputGetter.getIn3State() == State.NOTHING);
        if (in1 && in2 && in3 && hasInputs) {
            this.state = State.ONE;
        } else {
            this.state = State.ZERO;
        }
    }
}
