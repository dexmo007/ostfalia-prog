package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * Created by Maxi on 21.10.2015.
 * @author Henrik and Maxi
 */
public class Bridge extends Cell {

    private Wire wire1;
    private Wire wire2;

    /**
     * constructor for bridge cell
     * @param orientation orientation of the bridge cell
     * @throws IllegalOrientationException if not orientation between 0 and 3
     */
    public Bridge(int orientation) throws IllegalOrientationException {
        this.wire1 = new Wire(orientation);
        this.wire2 = new Wire((orientation + 3) % 4);
        this.type = 'B';
        this.accessibleDirections =
                wire1.accessibleDirections.concat(wire2.accessibleDirections);
//        this.setOrientation(orientation);
    }

    @Override
    public State getState(int row, int column,
                          int rowToCheck, int columnToCheck) {
        if (row - rowToCheck == 1) {
            if (wire1.accessibleDirections.contains("S")) {
                return wire1.getState();
            } else {
                return wire2.getState();
            }
        } else if (row - rowToCheck == -1) {
            if (wire1.accessibleDirections.contains("N")) {
                return wire1.getState();
            } else {
                return wire2.getState();
            }
        } else if (column - columnToCheck == 1) {
            if (wire1.accessibleDirections.contains("E")) {
                return wire1.getState();
            } else {
                return wire2.getState();
            }
        } else { //column-columnToCheck=-1
            if (wire1.accessibleDirections.contains("W")) {
                return wire1.getState();
            } else {
                return wire2.getState();
            }
        }
    }

    @Override
    public void update(GridImpl grid, int row, int column) {
        wire1.update(grid, row, column);
        wire2.update(grid, row, column);
    }

    @Override
    public String toString() {
        return "" + this.type + wire1.getState()
                + wire2.getState() + wire1.toString().charAt(2);
    }

}
