package de.ostfalia.prog.ws15;

/**
 * Created by Henrik on 9/29/2015.
 *
 * @author Henrik and Maxi
 */
public enum State {
    ZERO, ONE, NOTHING;


    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "0";
            case 1:
                return "1";
            default:
                return "-";
        }
    }

    /**
     * converts state to boolean
     *
     * @param isNothingTrue default boolean for nothing state
     *
     * @return boolean state
     */
    public boolean asBoolean(boolean isNothingTrue) {
        switch (this.ordinal()) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                return isNothingTrue;
        }
    }
}
