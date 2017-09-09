package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.State;

/**
 * Created by Henrik on 9/29/2015.
 * @author Henrik and Maxi
 */
public class Or extends Cell {
    /**
     * constructor for Or cell
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation cannot be resolved
     */
    public Or(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'R';
    }

    /**
     * updates or cell
     * @param row row index
     * @param column column index
     */
    @Override
    public void update(int row, int column) {
        boolean in1 = false;
        boolean in2 = false;
        boolean in3 = false;
        InputGetter inputGetter = new InputGetter(row, column, in1, in2,
                in3, this.orientation).invoke();
        in1 = inputGetter.isIn1();
        in2 = inputGetter.isIn2();
        in3 = inputGetter.isIn3();
        if (in1 || in2 || in3) {
            this.state = State.ONE;
        } else {
            this.state = State.ZERO;
        }
    }
}
