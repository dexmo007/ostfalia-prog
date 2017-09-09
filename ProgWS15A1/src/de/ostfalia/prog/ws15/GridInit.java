package de.ostfalia.prog.ws15;

import de.ostfalia.prog.ws15.cell.Cell;
import de.ostfalia.prog.ws15.cell.CellFactory;

/**
 * @author Henrik and Maxi
 */
public class GridInit implements Grid {

    public static Cell[][] cells;

    /**
     * constructor for GridInit
     * @param rows number of rows for the grid
     * @param columns number of columns for the grid
     */
    public GridInit(int rows, int columns) {
        this.cells = new Cell[rows][columns];
    }

    /**
     * private empty constructor
     */
    private GridInit(){

    }

    /**
     * implementation of update method, updates the whole grid
     */
    @Override
    public void update() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = i % 2; j < cells[0].length; j = j + 2) {
                if (cells[i][j] != null) {
                    cells[i][j].update(i, j);
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = (i + 1) % 2; j < cells[0].length; j = j + 2) {
                if (cells[i][j] != null) {
                    cells[i][j].update(i, j);
                }
            }
        }
    }

    /**
     * getter for all outputs of the grid
     * @return String with all outputs
     */
    @Override
    public String getOutputs() {
        return null;
    }

    /**
     * setter for all inputs in the grid
     * @param in String with all inputs
     */
    @Override
    public void setInputs(String in) {

    }

    /**
     * getter for the description of a certain cell
     * @param row row index of the cell to examine
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
     * @param row row index of the cell to be created
     * @param column columm index of the cell to be created
     * @param type type of the cell to be created
     * @param orientation orientation of the cell
     *                    to be created
     * @throws IllegalCellTypeException if cell type char cannot
     * be resolved in CellFactory.createCell method
     */
    @Override
    public void createCell(int row, int column, char type, int orientation)
            throws IllegalCellTypeException {
        try {
            this.cells[row][column] = CellFactory.createCell(type, orientation);
        } catch (Cell.IllegalOrientationException e) {
            e.printStackTrace();
        }
    }
}
