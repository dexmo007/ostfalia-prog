package de.ostfalia.prog.ws15.cell;

import de.ostfalia.prog.ws15.State;

/**
 * Created by Maxi on 21.10.2015.
 * @author Henrik and Maxi
 */
public class Input extends NoInputCell {
    /**
     * constructor for input cell
     */
    public Input() {
        this.accessibleDirections = "NESW";
        this.type = 'I';
        this.setState(State.NOTHING);
    }
}
