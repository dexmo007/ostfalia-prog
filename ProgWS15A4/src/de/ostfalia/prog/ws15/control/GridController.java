package de.ostfalia.prog.ws15.control;

import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.GridFactory;
import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;
import de.ostfalia.prog.ws15.cell.Bridge;
import de.ostfalia.prog.ws15.cell.Cell;
import de.ostfalia.prog.ws15.view.CommandLine;
import de.ostfalia.prog.ws15.view.Main;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maxi on 09.11.2015.
 *
 * @author Henrik and Maxi
 */
public class GridController implements Initializable {

    public static final int HEIGHT_WIDTH_OF_IMAGES = 50;
    public static final int NINETY_DEGREES = 90;
    public static final int THREE_SIXTY_DEGREES = 360;
    public static final int TWO_SEVENTY_DEGREES = 270;
    public static final int ONE_EIGHTY_DEGREES = 180;
    public static final int TEXT_SIZE = 30;
    public static final int ONE_SECOND = 1000;
    public static int rows;
    public static int columns;
    Grid grid;
    Cell[][] cells;
    GridPane gridGridPane;
    private boolean doTask = false;

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button updateButton;
    @FXML
    private TextField updateField;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextField inputsField;

    /**
     * creates Grid and sets event handler
     */
    public void init() {
        rows = Integer.parseInt(HeightWidthController.rows);
        columns = Integer.parseInt(HeightWidthController.columns);
        grid = GridFactory.emptyGrid(rows, columns);
        cells = ((GridImpl) grid).cells;
        gridGridPane = new GridPane();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                StackPane stackPane = new StackPane();
                Text text = new Text("-");
                stackPane.getChildren().add(0, new ImageView());
                stackPane.getChildren().add(1, text);
                stackPane.setOnDragOver(event ->
                        event.acceptTransferModes(TransferMode.ANY));
                stackPane.setOnDragDropped(this::dropHandler);
                stackPane.setPrefHeight(HEIGHT_WIDTH_OF_IMAGES);
                stackPane.setPrefWidth(HEIGHT_WIDTH_OF_IMAGES);
                gridGridPane.getChildren().add(stackPane);
                GridPane.setConstraints(stackPane, j, i);
            }
        }

        gridGridPane.setGridLinesVisible(true);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent((gridGridPane));

        this.borderPane.setCenter(scrollPane);
    }

    /**
     * event handler for Drag and drop start
     *
     * @param event MouseEvent in
     */
    @FXML
    public void dragHandler(MouseEvent event) {
        ImageView iv = (ImageView) event.getSource();
        Dragboard db = ((ImageView) event.getSource())
                .startDragAndDrop(TransferMode.ANY);
        db.setDragView(iv.getImage());
        ClipboardContent content = new ClipboardContent();
        content.putString(iv.getId());
        db.setContent(content);
    }

    /**
     * event handler for drop event
     *
     * @param event drop event
     */
    @FXML
    public void dropHandler(DragEvent event) {
        //change StackPane to show the right Image
        StackPane stackPane = (StackPane) event.getGestureTarget();
        int[] indexRowColumn = getRowColumnIndexes(stackPane);
        ImageView imageView = new ImageView(((ImageView)
                event.getGestureSource()).getImage());
        imageView.setId(((ImageView) event.getGestureSource()).getId());
        imageView.setFitHeight(HEIGHT_WIDTH_OF_IMAGES);
        imageView.setFitWidth(HEIGHT_WIDTH_OF_IMAGES);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(0, imageView);
        stackPane.getChildren().add(1, new Text());
        addContextMenu(stackPane);
        gridGridPane.getChildren().remove(event.getGestureTarget());
        gridGridPane.getChildren().add(indexRowColumn[0], stackPane);
        try {
            grid.createCell(indexRowColumn[1], indexRowColumn[2],
                    ((ImageView) event.getGestureSource()).
                            getId().charAt(0), 0);
        } catch (Grid.IllegalCellTypeException e) {
            //does not happen
        }
    }

    /**
     * adds a context menu to the stackpane
     *
     * @param stackPane stackpane to add context menu to
     */
    private void addContextMenu(StackPane stackPane) {
        int[] indexRowColumn = getRowColumnIndexes(stackPane);
        ContextMenu contextMenu = new ContextMenu();
        ImageView imageView = ((ImageView) stackPane.getChildren().get(0));
        MenuItem rotateClockwise = new MenuItem("Rotate Clockwise");
        MenuItem rotateAntiClockwise = new MenuItem("Rotate Anti-Clockwise");
        MenuItem rotate180 = new MenuItem("Rotate 180°");
        MenuItem remove = new MenuItem("Remove");
        contextMenu.getItems().addAll(rotateClockwise,
                rotateAntiClockwise, rotate180, remove);
        rotateClockwise.setOnAction(event1 -> rotateCell(imageView,
                cells[indexRowColumn[1]][indexRowColumn[2]], NINETY_DEGREES));
        rotateAntiClockwise.setOnAction(event1 ->
                rotateCell(imageView,
                        cells[indexRowColumn[1]][indexRowColumn[2]],
                        TWO_SEVENTY_DEGREES));
        rotate180.setOnAction(event1 ->
            rotateCell(imageView,
                    cells[indexRowColumn[1]][indexRowColumn[2]],
                    ONE_EIGHTY_DEGREES));
        remove.setOnAction(event1 -> {
            stackPane.getChildren().clear();
            cells[indexRowColumn[1]][indexRowColumn[2]] = null;
            stackPane.getChildren().add(0, new ImageView());
            stackPane.getChildren().add(1, new Text("-"));
        });
        stackPane.setOnContextMenuRequested(event1 ->
                contextMenu.show(stackPane, event1.getScreenX(),
                        event1.getScreenY()));
    }

    /**
     * new grid action handler
     *
     * @param event action event
     */
    @FXML
    public void createNewGrid(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        Main.createApplication("GridHeightWidth.fxml", "New Grid", stage);
    }

    /**
     * reset grid action handler
     *
     * @param event action event
     */
    @FXML
    public void resetGrid(ActionEvent event) {
        init();
    }

    /**
     * read command file action handler
     *
     * @param event action event
     */
    @FXML
    public void readCommands(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter =
                new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(filter);
        File file = fc.showOpenDialog(menuBar.getScene().getWindow());
        try {
            if (file != null) {
                grid = CommandLine.execute(new FileReader(file));
            }
        } catch (FileNotFoundException e) {
            //doesn't happen
        }
        cells = ((GridImpl) grid).cells;
        gridGridPane = new GridPane();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                StackPane stackPane = new StackPane();
                if (cells[i][j] == null) {
                    stackPane.getChildren().add(0, new ImageView());
                    stackPane.getChildren().add(1, new Text("-"));
                } else {
                    Image image =
                            new Image("Block" + cells[i][j].type + ".png");
                    ImageView imageView = new ImageView(image);
                    imageView.setId(String.valueOf(cells[i][j].type));
                    int degrees = (THREE_SIXTY_DEGREES - cells[i][j].
                            getOrientationNumber() * NINETY_DEGREES)
                            % THREE_SIXTY_DEGREES;
                    imageView.setRotate(degrees);
                    imageView.setFitHeight(HEIGHT_WIDTH_OF_IMAGES);
                    imageView.setFitWidth(HEIGHT_WIDTH_OF_IMAGES);
                    stackPane.getChildren().add(0, imageView);
                    addContextMenu(stackPane);
                    stackPane.getChildren().add(1, chooseText(cells[i][j]));
                }
                stackPane.setOnDragOver(event1 ->
                        event1.acceptTransferModes(TransferMode.ANY));
                stackPane.setOnDragDropped(this::dropHandler);
                stackPane.setPrefHeight(HEIGHT_WIDTH_OF_IMAGES);
                stackPane.setPrefWidth(HEIGHT_WIDTH_OF_IMAGES);
                gridGridPane.getChildren().add(stackPane);
                GridPane.setConstraints(stackPane, j, i);
            }
        }
        gridGridPane.setGridLinesVisible(true);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent((gridGridPane));
        this.borderPane.setCenter(scrollPane);
    }

    /**
     * returns the appropriate Text for a cell in update
     *
     * @param cell cell for which the text is needed
     *
     * @return correct text
     */
    private Text chooseText(Cell cell) {
        Text text = new Text();
        chooseText(cell, text);
        return text;
    }

    /**
     * chooses and sets the text for a text
     *
     * @param cell refering cell
     * @param text text to set
     */
    private void chooseText(Cell cell, Text text) {
        text.setFont(Font.font("Arial",
                FontWeight.BOLD, TEXT_SIZE));
        if (cell instanceof Bridge) {
            text.setText(((Bridge) cell).getStateString());
        } else {
            if (cell.getState() == State.ONE) {
                text.setText("1");
                text.setFill(Color.GREEN);
            } else if (cell.getState() == State.ZERO) {
                text.setText("0");
                text.setFill(Color.RED);
            }
        }
    }

    /**
     * close event handler, called on menuitem close
     *
     * @param event action event
     */
    @FXML
    public void close(ActionEvent event) {
        Stage old = ((Stage) menuBar.getScene().getWindow());
        old.close();
    }

    /**
     * rotates a cell and the corrisponding image view in the grid pane
     *
     * @param imageView image view to rotate
     * @param cell      cell to rotate
     * @param degrees   rotation degrees
     */
    private void rotateCell(ImageView imageView, Cell cell, int degrees) {
        imageView.setRotate((imageView.getRotate()
                + degrees) % THREE_SIXTY_DEGREES);
        cell.setOrientation((int)
                ((imageView.getRotate() / NINETY_DEGREES) * 3) % 4);
    }

    /**
     * gets the indexes of a stackpane in the grid pane
     *
     * @param stackPane stack pane to index
     *
     * @return int array containing index, row index and column index
     */
    private int[] getRowColumnIndexes(StackPane stackPane) {
        int[] result = new int[3];
        for (int i = 0; i < gridGridPane.getChildren().size(); i++) {
            if (gridGridPane.getChildren().get(i) == stackPane) {
                result[0] = i;
                result[1] = i / cells.length;
                result[2] = i % cells[0].length;
            }
        }
        return result;
    }


    /**
     * updates the grid and gridpane with states
     */
    @FXML
    public void update() {
        doTask = !doTask;
        if (doTask) {
            updateButton.setText("Stop!");
        } else {
            updateButton.setText("Update");
        }
        Task<Integer> objectTask = new Task<Integer>() {
            @Override
            protected void done() {
                System.out.println("done");
            }

            @Override
            protected Integer call() throws Exception {
                while (doTask) {
                    updateTask();
                    Thread.sleep(ONE_SECOND);
                }
                return 0;
            }
        };
        Thread thread = new Thread(objectTask);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * updates everything
     */
    @FXML
    public void updateTask() {
        int index = 0;
        grid.update();
        for (Cell[] cell : cells) {
            for (int k = 0; k < cells[0].length; k++) {
                if (cell[k] != null &&
                        cell[k].getState() != null) {
                    StackPane stackPane = (StackPane) gridGridPane.
                            getChildren().get(index);
                    Text text = (Text) stackPane.getChildren().get(1);
                    chooseText(cell[k], text);
                }
                index++;
            }
        }
        System.out.println(grid);
    }

    /**
     * sets inputs both in grid and grid pane
     * event handler called on set inputs button
     *
     * @param event action event
     */
    @FXML
    public void setInputs(ActionEvent event) {
        if (inputsField.getText() == null || inputsField.getText().isEmpty()
                || !inputsField.getText().matches("\\d+")) {
            Stage stage = ((Stage) inputsField.getScene().getWindow());
            Main.createApplication("WrongInput.fxml",
                    "Invalid input!", stage);
        } else {
            String inputs = inputsField.getText();
            grid.setInputs(inputs);
            int counter = 0;
            for (int i = 0; i < gridGridPane.getChildren().size() - 1; i++) {
                StackPane stackPane = ((StackPane)
                        gridGridPane.getChildren().get(i));
                String id = stackPane.getChildren().get(0).getId();
                if (id != null && id.equals("I")) {
                    Text text = new Text();
                    text.setFont(Font.font("Arial",
                            FontWeight.BOLD, TEXT_SIZE));
                    String input = inputs.substring(counter, counter + 1);
                    if (input.equals("1")) {
                        text.setText("1");
                        text.setFill(Color.GREEN);
                    } else if (input.equals("0")) {
                        text.setText("0");
                        text.setFill(Color.RED);
                    }
                    stackPane.getChildren().remove(1);
                    stackPane.getChildren().add(1, text);
                    counter++;
                }
            }
            System.out.println(grid);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }
}
