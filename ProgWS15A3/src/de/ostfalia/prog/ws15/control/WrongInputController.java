package de.ostfalia.prog.ws15.control;

import de.ostfalia.prog.ws15.view.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Maxi on 11.11.2015.
 * @author Henrik and Maxi
 */
public class WrongInputController {

    @FXML
    private javafx.scene.control.Button cancelButton;

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
}
