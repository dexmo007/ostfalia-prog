package de.ostfalia.prog.ws15.cell;

/**
 * Created by Henrik on 10/9/2015.
 * @author Henrik and Maxi
 */
public abstract class NoInputCell extends Cell {
    /**
     * custom toString method
     * @return String desctiption of a cell
     */
    @Override
    public String toString() {
        char state;
        switch (this.state) {
            case ONE:
                state = '1';
                break;
            case ZERO:
                state = '0';
                break;
            case NOTHING:
                state = '-';
                break;
            default:
                state = '-';
                break;
        }
        return "" + this.type + state;
    }
}
