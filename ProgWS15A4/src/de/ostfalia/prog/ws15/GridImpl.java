package de.ostfalia.prog.ws15;

import de.ostfalia.prog.ws15.cell.Cell;
import de.ostfalia.prog.ws15.cell.CellFactory;

/**
 * @author Henrik and Maxi
 */
public class GridImpl implements Grid {

    public Cell[][] cells;

    /**
     * constructor for GridImpl
     *
     * @param rows    number of rows for the grid
     * @param columns number of columns for the grid
     */
    public GridImpl(int rows, int columns) {
        this.cells = new Cell[rows][columns];
    }

    /**
     * private empty constructor
     */
    private GridImpl() {

    }

    /**
     * implementation of update method, updates the whole grid
     */
    @Override
    public void update() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = i % 2; j < cells[0].length; j = j + 2) {
                if (cells[i][j] != null) {
                    cells[i][j].update(this, i, j);
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = (i + 1) % 2; j < cells[0].length; j = j + 2) {
                if (cells[i][j] != null) {
                    cells[i][j].update(this, i, j);
                }
            }
        }
    }

    /**
     * getter for all outputs of the grid
     *
     * @return String with all outputs
     */
    @Override
    public String getOutputs() {

        String outputs = "";

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] != null && cells[i][j].type == 'O') {
                    outputs = outputs + cells[i][j].getState();
                }
            }
        }
        return outputs;
    }

    /**
     * setter for all inputs in the grid
     *
     * @param in String with all inputs
     */
    @Override
    public void setInputs(String in) {

        int counter = 0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] != null && cells[i][j].type == 'I') {
                    if ((in.regionMatches(counter, "1", 0, 1))) {
                        cells[i][j].setState(State.ONE);
                    } else {
                        cells[i][j].setState(State.ZERO);
                    }
                    counter++;
                }
            }
        }
    }

    /**
     * getter for the description of a certain cell
     *
     * @param row    row index of the cell to examine
     * @param column column index of the cell to examine
     * @return String description of certain cell
     */
    @Override
    public String getCellString(int row, int column) {
        if (cells[row][column] != null) {
            return cells[row][column].toString();
        }
        return "--";
    }

    /**
     * creates a cell in the grid
     *
     * @param row         row index of the cell to be created
     * @param column      columm index of the cell to be created
     * @param type        type of the cell to be created
     * @param orientation orientation of the cell
     *                    to be created
     * @throws IllegalCellTypeException if cell type char cannot
     *         be resolved in CellFactory.createCell method
     */
    @Override
    public void createCell(int row, int column, char type, int orientation)
            throws IllegalCellTypeException {
        this.cells[row][column] = CellFactory.createCell(type, orientation);
    }

    /**
     * toString method for a grid
     *
     * @return string of the grid
     */
    @Override
    public String toString() {
        String res = "[";
        for (int i = 0; i < cells.length; i++) {
            if (i > 0) {
                res = res.concat(" ");
            }
            res = res.concat("[");
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] != null) {
                    res = res.concat(cells[i][j].toString());
                } else {
                    res = res.concat("--");
                }
                if (getCellString(i, j).length() == 2) {
                    res = res.concat(" ");
                }
                if (j < cells[0].length - 1) {
                    res = res.concat(",");
                }
            }
            res = res.concat("]");
            if (i < cells.length - 1) {
                res = res.concat(",\n");
            }
        }
        res = res.concat("]");
        return res;
    }
}
