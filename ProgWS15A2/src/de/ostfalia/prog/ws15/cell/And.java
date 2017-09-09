package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * @author Henrik and Maxi
 */
public class And extends Cell {
    /**
     * constructor for And cell
     *
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation
     *                                     parameter is not between 0 and 3
     */
    public And(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'A';
    }

    /**
     * updates the And cell
     *
     * @param row    row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(GridImpl grid, int row, int column) {
        InputGetter inputGetter = new InputGetter(grid, row, column,
                this.orientation).invoke();
        boolean in1 = inputGetter.getInState(0).asBoolean(true);
        boolean in2 = inputGetter.getInState(1).asBoolean(true);
        boolean in3 = inputGetter.getInState(2).asBoolean(true);
        boolean hasInputs = !(inputGetter.getInState(0) == State.NOTHING &&
                inputGetter.getInState(1) == State.NOTHING &&
                inputGetter.getInState(2) == State.NOTHING);
        if (in1 && in2 && in3 && hasInputs) {
            this.setState(State.ONE);
        } else {
            this.setState(State.ZERO);
        }
    }
}
