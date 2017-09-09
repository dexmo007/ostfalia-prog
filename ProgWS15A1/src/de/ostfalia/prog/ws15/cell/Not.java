package de.ostfalia.prog.ws15.cell;


import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 * @author Henrik and Maxi
 */
public class Not extends Cell {
    /**
     * constructor for not cell
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation
     * parameter is not between 0 and 3
     */
    public Not(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'N';
    }

    /**
     * updates the Not cell
     * @param row row index of the cell to be updated
     * @param column column index of the cell to be updated
     */
    @Override
    public void update(int row, int column) {
        super.update(row, column);
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
