package de.ostfalia.prog.ws15.cell;

/**
 * Created by Maxi on 21.10.2015.
 * @author Henrik and Maxi
 */
public class Output extends Cell {
    /**
     * constructor for output cell
     *
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation is not between 0 and 3
     */
    public Output(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'O';
        this.accessibleDirections = "";
    }
}
