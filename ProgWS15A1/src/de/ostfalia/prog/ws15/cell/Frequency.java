package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.State;

/**
 * @author Henrik and Maxi
 */
public class Frequency extends NoInputCell {

    private int updateCounter;
    /**
     * constructor for Frequency cell
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation
     * parameter is not between 0 and 3
     */
    public Frequency(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'F';
        this.state = State.ZERO;
        this.accessibleDirections = "NESW";
        updateCounter = 0;
    }

    /**
     * updates the Frequency cell
     * @param row row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(int row, int column) {
        updateCounter++;
        if (updateCounter % 5 == 0) {
            switch (this.state) {
                case ONE:
                    this.state = State.ZERO;
                    break;
                case ZERO:
                    this.state = State.ONE;
                    break;
                default:
                    break;
            }
        }
    }
}
