package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 * @author Henrik and Maxi
 */
public class One extends NoInputCell {
    /**
     * constructor for input One cell
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation
     * parameter is not between 0 and 3
     */
    public One(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'E';
        this.state = State.ONE;
        this.accessibleDirections = "NESW";
    }

    /**
     * updates the One cell
     * @param row row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(int row, int column) {
        this.state = State.ONE;
    }
}
