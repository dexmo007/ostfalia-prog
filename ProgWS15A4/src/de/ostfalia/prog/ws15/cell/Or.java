package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public class Or extends Cell {
    /**
     * constructor for Or cell
     *
     * @param orientation orientation of the cell
     */
    public Or(int orientation) {
        setOrientation(orientation);
        this.type = 'R';
    }

    /**
     * updates or cell
     *
     * @param row    row index
     * @param column column index
     */
    @Override
    public void update(GridImpl grid, int row, int column) {
        InputGetter inputGetter = new InputGetter(grid, row, column,
                this.orientation).invoke();
        boolean in1 = inputGetter.getInState(0).asBoolean(false);
        boolean in2 = inputGetter.getInState(1).asBoolean(false);
        boolean in3 = inputGetter.getInState(2).asBoolean(false);
        if (in1 || in2 || in3) {
            this.setState(State.ONE);
        } else {
            this.setState(State.ZERO);
        }
    }
}
