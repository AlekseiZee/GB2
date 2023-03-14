package ru.geobuilder_2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
            Scene sceneMainView = new Scene(fxmlLoaderMainView.load(), 768, 700);
            return sceneMainView;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
