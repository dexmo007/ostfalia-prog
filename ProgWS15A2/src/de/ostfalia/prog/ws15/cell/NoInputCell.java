package de.ostfalia.prog.ws15.cell;

/**
 * Created by Henrik on 10/9/2015.
 *
 * @author Henrik and Maxi
 */
public abstract class NoInputCell extends Cell {
    /**
     * custom toString method
     *
     * @return String desctiption of a cell
     */
    @Override
    public String toString() {
        return "" + this.type + this.getState();
    }

}
