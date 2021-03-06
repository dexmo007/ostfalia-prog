package de.ostfalia.prog.ws15.cell;


import de.ostfalia.prog.ws15.GridImpl;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public class Not extends Cell {
    /**
     * constructor for not cell
     *
     * @param orientation orientation of the cell
     */
    public Not(int orientation) {
        setOrientation(orientation);
        this.type = 'N';
    }

    /**
     * updates the Not cell
     *
     * @param row    row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(GridImpl grid, int row, int column) {
        super.update(grid, row, column);
        this.setState(this.getState().switchState(this.getState()));
    }
}
