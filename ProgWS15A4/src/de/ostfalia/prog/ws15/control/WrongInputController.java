package de.ostfalia.prog.ws15.control;

import de.ostfalia.prog.ws15.view.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maxi on 11.11.2015.
 * @author Henrik and Maxi
 */
public class WrongInputController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button tryAgainButton;

    /**
     * try again event handler
     */
    public void tryAgain() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Main.getOldStage().show();
    }

    /**
     * cancel button event handler
     */
    @FXML
    public void close() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Main.getOldStage().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> tryAgainButton.requestFocus());
    }
}
