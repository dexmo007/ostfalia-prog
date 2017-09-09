package de.ostfalia.prog.ws15.cell;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public class Wire extends Cell {
    /**
     * constructor for Wire cell
     *
     * @param orientation orientation of the cell
     */
    public Wire(int orientation) {
        setOrientation(orientation);
        this.type = 'W';
    }
}
