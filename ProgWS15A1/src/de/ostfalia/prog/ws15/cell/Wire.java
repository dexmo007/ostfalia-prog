package de.ostfalia.prog.ws15.cell;

/**
 * Created by Henrik on 9/29/2015.
 * @author Henrik and Maxi
 */
public class Wire extends Cell {
    /**
     * constructor for Wire cell
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation
     * parameter is not between 0 and 3
     */
    public Wire(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'W';
    }
}
