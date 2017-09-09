package de.ostfalia.prog.ws15.control;

import de.ostfalia.prog.ws15.view.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * controller class for height width controller
 * @author Henrik and Maxi
 */
public class HeightWidthController implements Initializable{

    public static String columns;
    public static String rows;

    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private javafx.scene.control.TextField rowsField;
    @FXML
    private javafx.scene.control.TextField columnsField;

    /**
     * ok button event handler
     */
    @FXML
    public void done() {
        Stage stage = (Stage) columnsField.getScene().getWindow();
        if (columnsField.getText() == null || columnsField.getText().isEmpty()
                || !columnsField.getText().matches("\\d+")) {
            Main.createApplication("WrongInput.fxml",
                    "Invalid input!", stage);
            close();
        } else if (rowsField.getText() == null || rowsField.getText().isEmpty()
                || !rowsField.getText().matches("\\d+")) {
            Main.createApplication("WrongInput.fxml",
                    "Invalid input!", stage);
            close();
        } else {
            columns = columnsField.getText();
            rows = rowsField.getText();
            close();
            Main.createApplication("Grid.fxml", "Grid", stage);
        }
    }

    /**
     * cancel button event handler
     */
    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * initializes the grid
     */
    private void initGrid() {
        GridPane gridPane2 = new GridPane();

        for (int i = 0; i < Integer.parseInt(rows); i++) {
            for (int j = 0; j < Integer.parseInt(columns); j++) {
                StackPane stackPane = new StackPane();
                gridPane2.getChildren().add(stackPane);
                GridPane.setConstraints(stackPane, j, i);
            }
        }

        gridPane2.setGridLinesVisible(true);
    }

    /**
     * controller initialization
     *
     * @param location url location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                rowsField.requestFocus();
            }
        });
    }
}