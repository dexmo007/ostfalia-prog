package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * @author Henrik and Maxi
 */
public class Frequency extends NoInputCell {

    private int updateCounter;

    /**
     * constructor for Frequency cell
     *
     */
    public Frequency() {
        this.type = 'F';
        this.setState(State.ZERO);
        this.accessibleDirections = "NESW";
        updateCounter = 0;
    }

    /**
     * updates the Frequency cell
     *
     * @param row    row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(GridImpl grid, int row, int column) {
        updateCounter++;
        if (updateCounter % 5 == 0) {
            this.setState(this.getState().switchState(this.getState()));
        }
    }
}
