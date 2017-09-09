package de.ostfalia.prog.ws15.view;

import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.GridFactory;

import java.io.Reader;
import java.util.Scanner;

/**
 * Created by Henrik on 10/21/2015.
 *
 * @author Henrik and Maxi
 */
public class CommandLine {

    private static final String INVALID_INPUT = "Invalid input!";

    /**
     * executes the command line
     *
     * @param reader input reader for commands
     * @return the grid created by commands
     */
    public static de.ostfalia.prog.ws15.Grid execute(Reader reader) {
        de.ostfalia.prog.ws15.Grid grid = null;
        try (Scanner sc = new Scanner(reader)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                char command = line.charAt(0);
                switch (command) {
                    case 'g':
                        grid = executeG(grid, line);
                        break;
                    case 'c':
                        executeC(grid, line);
                        break;
                    case 'p':
                        System.out.println(grid);
                        break;
                    case 's':
                        executeS(grid);
                        break;
                    case 'r':
                        executeR(grid, line);
                        break;
                    case 'i':
                        executeI(grid, line);
                        break;
                    case 'o':
                        executeO(grid);
                        break;
                    case 'q':
                        executeQ(grid, line);
                        break;
                    case '#':
                        break;
                    case 'x':
                        return grid;
                    default:
                        System.out.println(INVALID_INPUT);
                        break;
                }
            }
        }
        return grid;
    }

    /**
     * executes o command
     *
     * @param grid target grid
     */
    private static void executeO(de.ostfalia.prog.ws15.Grid grid) {
        try {
            System.out.println(grid.getOutputs());
        } catch (NullPointerException e) {
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * executes q command
     *
     * @param grid target grid
     * @param line operation
     */
    private static void executeQ(de.ostfalia.prog.ws15.Grid grid, String line) {
        try {
            int runs = Integer.parseInt(line.substring(2, 3));
            for (int i = 0; i < runs; i++) {
                grid.update();
                System.out.println(grid);
            }
        } catch (StringIndexOutOfBoundsException |
                NumberFormatException |
                NullPointerException e) {
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * executes i command
     *
     * @param grid target grid
     * @param line operation
     */
    private static void executeI(de.ostfalia.prog.ws15.Grid grid, String line) {
        try {
            String inputs = line.substring(2, line.length());
            grid.setInputs(inputs);
        } catch (StringIndexOutOfBoundsException |
                NullPointerException e) {
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * executes r command
     *
     * @param grid target grid
     * @param line operation
     */
    private static void executeR(de.ostfalia.prog.ws15.Grid grid, String line) {
        try {
            int runs = Integer.parseInt(line.substring(2, line.length()));
            for (int i = 0; i < runs; i++) {
                grid.update();
            }
        } catch (NumberFormatException |
                StringIndexOutOfBoundsException |
                NullPointerException e) {
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * executes s command
     *
     * @param grid target grid
     */
    private static void executeS(de.ostfalia.prog.ws15.Grid grid) {
        try {
            grid.update();
            return;
        } catch (NullPointerException e) {
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * executes c command
     *
     * @param grid target grid
     * @param line operation
     */
    private static void executeC(de.ostfalia.prog.ws15.Grid grid, String line) {
        try {
            char type = line.charAt(1);
            int orientation =
                    Integer.parseInt(line.substring(2, 3));
            int row = Integer.parseInt(line.substring(4, 5));
            int column = Integer.parseInt(line.substring(6, 7));
            grid.createCell(row, column, type, orientation);
        } catch (de.ostfalia.prog.ws15.Grid.IllegalCellTypeException |
                NumberFormatException | NullPointerException |
                StringIndexOutOfBoundsException e) {
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * executes g command
     *
     * @param grid target grid
     * @param line operation
     * @return created grid
     */
    private static Grid executeG(Grid grid, String line) {
        try {
            int row = Integer.parseInt(line.substring(2, 3));
            int column = Integer.parseInt(line.substring(4, 5));
            grid = GridFactory.emptyGrid(row, column);
        } catch (NumberFormatException |
                NullPointerException e) {
            System.out.println(INVALID_INPUT);
        }
        return grid;
    }
}