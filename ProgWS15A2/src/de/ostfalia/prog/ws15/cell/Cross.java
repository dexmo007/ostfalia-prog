package de.ostfalia.prog.ws15.cell;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public class Cross extends Cell {
    /**
     * constructor for Cross cell
     *
     * @param orientation orientation of the cell
     * @throws IllegalOrientationException if orientation
     *                                     parameter is not between 0 and 3
     */
    public Cross(int orientation) throws IllegalOrientationException {
        setOrientation(orientation);
        this.type = 'C';
        if (this.orientation == '<' || this.orientation == '>') {
            this.accessibleDirections = this.accessibleDirections.concat("NS");
        } else {
            this.accessibleDirections = this.accessibleDirections.concat("EW");
        }
    }
}
