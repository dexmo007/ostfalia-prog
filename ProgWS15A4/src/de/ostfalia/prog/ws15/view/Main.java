package de.ostfalia.prog.ws15.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Maxi on 07.11.2015.
 *
 * @author Henrik and Maxi
 */
public class Main extends Application {
    private static Stage oldStage;

    /**
     * main method
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * creates a javafx application from a fxml, string title and an old stage
     *
     * @param resource fxml resource
     * @param title    String title
     * @param old      old Stage
     */
    public static void createApplication(String resource,
                                         String title, Stage old) {
        setOldStage(old);
        if (title.equals("Invalid input!")) {
            old.hide();
        } else {
            old.close();
        }
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource(resource));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets the old stage
     *
     * @return old stage
     */
    public static Stage getOldStage() {
        return oldStage;
    }

    /**
     * sets the old stage
     *
     * @param oldStage stage to set
     */
    public static void setOldStage(Stage oldStage) {
        Main.oldStage = oldStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        createApplication("GridHeightWidth.fxml", "New Grid", stage);
    }
}
