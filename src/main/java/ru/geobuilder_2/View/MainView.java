package ru.geobuilder_2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.geobuilder_2.Controller_Main;
import ru.geobuilder_2.StartGeoApplication;

import java.io.IOException;

public class MainView implements ViewMaker {

    private Stage stage;

    public MainView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {
        try {
            FXMLLoader fxmlLoaderMainView = new FXMLLoader(StartGeoApplication.class.getResource("main-view.fxml"));
            Parent root = fxmlLoaderMainView.load();
            Controller_Main controller = fxmlLoaderMainView.getController();
            controller.setStage(stage);
//            stage.setMinWidth(681);
//            stage.setMinHeight(529);
//            stage.setMaxWidth(681);
//            stage.setMaxHeight(529);
            Scene sceneMainView = new Scene(root);
            return sceneMainView;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
