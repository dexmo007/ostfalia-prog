package de.ostfalia.prog.ws15.cell;

/**
 * Created by Maxi on 21.10.2015.
 *
 * @author Henrik and Maxi
 */
public class Output extends Cell {
    /**
     * constructor for output cell
     *
     * @param orientation orientation of the cell
     */
    public Output(int orientation) {
        setOrientation(orientation);
        this.type = 'O';
        this.accessibleDirections = "";
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        this.accessibleDirections = "";
    }
}
