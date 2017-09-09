package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public class Zero extends NoInputCell {
    /**
     * constructor for input Zero cell
     *
     * @throws Cell.IllegalOrientationException if orientation
     *                                          parameter is not between 0 and 3
     */
    public Zero() throws Cell.IllegalOrientationException {
        this.type = 'Z';
        this.setState(State.ZERO);
        this.accessibleDirections = "NESW";
    }

    /**
     * updates the input Zero cell
     *
     * @param row    row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(GridImpl grid, int row, int column) {
        this.setState(State.ZERO);
    }
}
