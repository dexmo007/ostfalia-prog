package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public class One extends NoInputCell {
    /**
     * constructor for input One cell
     *
     */
    public One() {
        this.type = 'E';
        this.setState(State.ONE);
        this.accessibleDirections = "NESW";
    }

    /**
     * updates the One cell
     *
     * @param row    row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(GridImpl grid, int row, int column) {
        this.setState(State.ONE);
    }
}
