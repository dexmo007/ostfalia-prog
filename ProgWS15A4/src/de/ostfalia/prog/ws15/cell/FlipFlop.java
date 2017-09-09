package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;

/**
 * Created by Maxi on 21.11.2015.
 * @author Henrik and Maxi
 */
public class FlipFlop extends Cell {

    private Wire wire;
    private Not not;

    /**
     * constructor for FlipFlop cell
     *
     * @param orientation orientation int
     */
    public FlipFlop(int orientation) {
        wire = new Wire(orientation);
        not = new Not((orientation + 3) % 4);
        setOrientation(orientation);
        this.type = 'D';
        this.accessibleDirections =
                wire.accessibleDirections.concat(not.accessibleDirections);
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        wire.setOrientation(orientation);
        not.setOrientation((orientation + 3) % 4);
        this.accessibleDirections =
                wire.accessibleDirections.concat(not.accessibleDirections);
    }

    @Override
    public State getState(int row, int column,
                          int rowToCheck, int columnToCheck) {

        if (column - columnToCheck == -1) {
            switch (this.orientation) {
                case 'v':
                    return not.getState();
                default: // case <
                    return wire.getState();
            }
        } else if (column - columnToCheck == 1) {
            switch (this.orientation) {
                case '^':
                    return not.getState();
                default: // case >
                    return wire.getState();
            }
        } else if (row - rowToCheck == -1) {
            switch (this.orientation) {
                case '^':
                    return wire.getState();
                default: // case <
                    return not.getState();
            }
        } else { // row - rowToCheck == 1
            switch (this.orientation) {
                case '>':
                    return not.getState();
                default: // case v
                    return wire.getState();
            }
        }
    }

    @Override
    public void update(GridImpl grid, int row, int column) {
        boolean update = false;
        Cell cell = null;
        switch (this.orientation) {
            case '>':
                if (row > 0) {
                    cell = grid.cells[row - 1][column];
                    update = cell != null &&
                            cell.accessibleDirections.contains("S");
                }
                break;
            case '<':
                if (row > grid.cells.length - 1) {
                    cell = grid.cells[row + 1][column];
                    update = cell != null &&
                            cell.accessibleDirections.contains("S");
                }
                break;
            case 'v':
                if (column < grid.cells[0].length - 1) {
                    cell = grid.cells[row][column + 1];
                    update = cell != null &&
                            cell.accessibleDirections.contains("W");
                }
                break;
            default: // case ^
                if (column < 0) {
                    cell = grid.cells[row][column - 1];
                    update = cell != null &&
                            cell.accessibleDirections.contains("E");
                }
                break;
        }
        if (update && cell.getState() == State.ONE) {
            wire.update(grid, row, column);
            this.setState(wire.getState());
            not.setState(wire.getState().switchState());
        }
    }
}
