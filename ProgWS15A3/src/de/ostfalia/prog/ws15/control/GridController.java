package de.ostfalia.prog.ws15.control;

import de.ostfalia.prog.ws15.Grid;
import de.ostfalia.prog.ws15.GridFactory;
import de.ostfalia.prog.ws15.GridImpl;
import de.ostfalia.prog.ws15.State;
import de.ostfalia.prog.ws15.cell.Bridge;
import de.ostfalia.prog.ws15.cell.Cell;
import de.ostfalia.prog.ws15.view.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maxi on 09.11.2015.
 *
 * @author Henrik and Maxi
 */
public class GridController implements Initializable {

    public static final int HEIGHT_WIDTH_OF_IMAGES = 50;
    public static final int ROWS = Integer.parseInt(HeightWidthController.rows);
    public static final int COLUMNS =
            Integer.parseInt(HeightWidthController.columns);
    public static final int NINETY_DEGREES = 90;
    public static final int THREE_SIXTY_DEGREES = 360;
    public static final int TWO_SEVENTY_DEGREES = 270;
    public static final int ONE_EIGHTY_DEGREES = 180;
    public static final int TEXT_SIZE = 30;
    Grid grid;
    Cell[][] cells;
    GridPane gridGridPane;

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button updateButton;
    @FXML
    private TextField updateField;

    /**
     * creates Grid and sets event handler
     */
    public void init() {
        grid = GridFactory.emptyGrid(ROWS, COLUMNS);
        cells = ((GridImpl) grid).cells;
        gridGridPane = new GridPane();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                StackPane stackPane = new StackPane();
                Text text = new Text("-");
                stackPane.getChildren().add(0, new ImageView());
                stackPane.getChildren().add(1, text);
                stackPane.setOnDragOver(event ->
                        event.acceptTransferModes(TransferMode.ANY));
                stackPane.setOnDragDropped(event -> dropHandler(event));
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
//        gridPane.setConstraints(scrollPane, 1, 0);
    }

    /**
     * event handler for Drag and drop start
     *
     * @param event MouseEvent in
     */
    @FXML
    public void dragHandler(MouseEvent event) {
//        System.out.println("dragHandler");
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
        ContextMenu contextMenu = new ContextMenu();
        MenuItem rotateClockwise = new MenuItem("Rotate Clockwise");
        MenuItem rotateAntiClockwise = new MenuItem("Rotate Anti-Clockwise");
        MenuItem rotate180 = new MenuItem("Rotate 180°");
        contextMenu.getItems().addAll(rotateClockwise,
                rotateAntiClockwise, rotate180);
        rotateClockwise.setOnAction(event1 -> {
            imageView.setRotate((imageView.getRotate()
                    + NINETY_DEGREES) % THREE_SIXTY_DEGREES);
            cells[indexRowColumn[1]][indexRowColumn[2]].setOrientation(
                    (int) ((imageView.getRotate() / NINETY_DEGREES) * 3) % 4);
        });
        rotateAntiClockwise.setOnAction(event1 -> {
            imageView.setRotate((imageView.getRotate()
                    + TWO_SEVENTY_DEGREES) % THREE_SIXTY_DEGREES);
            cells[indexRowColumn[1]][indexRowColumn[2]].setOrientation(
                    (int) ((imageView.getRotate() / NINETY_DEGREES) * 3) % 4);
        });
        rotate180.setOnAction(event1 -> {
            imageView.setRotate((imageView.getRotate()
                    + ONE_EIGHTY_DEGREES) % THREE_SIXTY_DEGREES);
            cells[indexRowColumn[1]][indexRowColumn[2]].setOrientation(
                    (int) ((imageView.getRotate() / NINETY_DEGREES) * 3) % 4);
        });
        stackPane.setOnContextMenuRequested(event1 -> {
            contextMenu.show(imageView, event1.getScreenX(),
                    event1.getScreenY());
        });
        imageView.setFitHeight(HEIGHT_WIDTH_OF_IMAGES);
        imageView.setFitWidth(HEIGHT_WIDTH_OF_IMAGES);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(0, imageView);
        stackPane.getChildren().add(1, new Text());
        gridGridPane.getChildren().remove(event.getGestureTarget());
        gridGridPane.getChildren().add(indexRowColumn[0], stackPane);
        try {
            grid.createCell(indexRowColumn[1], indexRowColumn[2],
                    ((ImageView) event.getGestureSource()).
                            getId().charAt(0), 0);
        } catch (Grid.IllegalCellTypeException e) {
        }
    }

    /**
     * gets the indexes from a stackpane in a grid pane
     *
     * @param stackPane stackPane to index
     *
     * @return int array with index, row index and column index
     */
    private int[] getRowColumnIndexes(StackPane stackPane) {
        int[] result = new int[3];
        for (int i = 0; i < gridGridPane.getChildren().size(); i++) {
            if (gridGridPane.getChildren().get(i) == stackPane) {
                result[0] = i;
                result[1] = i / ROWS;
                result[2] = i % COLUMNS;
            }
        }
        return result;
    }



    /**
     * updates the grid and gridpane with states
     */
    @FXML
    public void update() {
        int numberOfUpdates = 1;
        if (updateField.getText() != null &&
                !updateField.getText().isEmpty()
                && updateField.getText().matches("\\d+")) {
            numberOfUpdates = Integer.parseInt(updateField.getText());
        } else {
            Main.createApplication("WrongInput.fxml", "Wrong Input",
                    (Stage) updateButton.getScene().getWindow());
        }
        for (int i = 0; i < numberOfUpdates; i++) {
            int index = 0;
            grid.update();
            for (int j = 0; j < ROWS; j++) {
                for (int k = 0; k < COLUMNS; k++) {
                    State state = null;
                    if (cells[j][k] != null &&
                            (state = cells[j][k].getState()) != null) {
                        StackPane stackPane = (StackPane) gridGridPane.
                                getChildren().get(index);
                        Text text = new Text();
                        text.setFont(Font.font("Arial",
                                FontWeight.BOLD, TEXT_SIZE));
                        stackPane.getChildren().remove(1);
                        if (cells[j][k] instanceof Bridge) {
                            text.setText(
                                    ((Bridge) cells[j][k]).getStateString());
                        } else {
                            if (state == State.ONE) {
                                text.setText("1");
                                text.setFill(Color.GREEN);
                            } else if (state == State.ZERO) {
                                text.setText("0");
                                text.setFill(Color.RED);
                            }
                        }
                        stackPane.getChildren().add(1, text);
                    }
                    index++;
                }
            }
        }
        System.out.println(grid.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }
}
