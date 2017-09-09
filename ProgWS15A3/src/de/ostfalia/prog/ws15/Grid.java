package de.ostfalia.prog.ws15;

/**
 * @author Henrik and Maxi
 */
public interface Grid {
    /**
     * updates the whole Grid
     */
    void update();

    /**
     * getter for all outputs of the Grid
     *
     * @return String with all outputs
     */
    String getOutputs();

    /**
     * setter for all inputs in the grid
     *
     * @param in String with all inputs
     */
    void setInputs(String in);

    /**
     * getter for the description of a certain cell
     *
     * @param row    row index of the cell to examine
     * @param column column index of the cell to examine
     * @return String description of this certain cell
     */
    String getCellString(int row, int column);

    /**
     * creates a cell in the grid
     *
     * @param row         row index of the cell to be created
     * @param column      columm index of the cell to be created
     * @param type        type of the cell to be created
     * @param orientation orientation of the cell to be created
     * @throws IllegalCellTypeException if cell type char cannot be resolved
     */
    void createCell(int row, int column,
                    char type, int orientation)
            throws IllegalCellTypeException;

    /**
     * @author Henrik and Maxi
     */
    class IllegalCellTypeException extends Exception {
        private static final long serialVersionUID = 1L;

        /**
         * constructor for IllegalCellTypeOrientation
         *
         * @param message error message of the exception when thrown
         */
        public IllegalCellTypeException(String message) {
            super(message);
        }
    }
}