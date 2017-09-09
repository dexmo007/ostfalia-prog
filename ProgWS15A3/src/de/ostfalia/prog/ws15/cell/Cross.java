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
     */
    public Cross(int orientation) {
        setOrientation(orientation);
        this.type = 'C';
        if (this.orientation == '<' || this.orientation == '>') {
            this.accessibleDirections = this.accessibleDirections.concat("NS");
        } else {
            this.accessibleDirections = this.accessibleDirections.concat("EW");
        }
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        if (this.orientation == '<' || this.orientation == '>') {
            this.accessibleDirections = this.accessibleDirections.concat("NS");
        } else {
            this.accessibleDirections = this.accessibleDirections.concat("EW");
        }
    }
}
