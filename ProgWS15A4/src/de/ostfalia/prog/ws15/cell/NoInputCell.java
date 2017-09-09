package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;

/**
 * Created by Henrik on 10/9/2015.
 *
 * @author Henrik and Maxi
 */
public abstract class NoInputCell extends Cell {
    @Override
    public void setOrientation(int orientation) {
    }

    /**
     * custom toString method
     *
     * @return String desctiption of a cell
     */
    @Override
    public String toString() {
        return "" + this.type + this.getState();
    }

    @Override
    public void update(GridImpl grid, int row, int column) {
        //do nothing
    }
}
