package ru.geobuilder_2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.geobuilder_2.Controller_Main;
import ru.geobuilder_2.Controller_uploadDataToDatabase;
import ru.geobuilder_2.StartGeoApplication;

import java.io.IOException;

public class UploadDataToDBView implements ViewMaker {

    private Stage stage;

    public UploadDataToDBView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {
        try {
            FXMLLoader fxmlLoaderUploadToDB = new FXMLLoader(StartGeoApplication.class.getResource("uploadDataToDatabase-view"));
            Parent root = fxmlLoaderUploadToDB.load();
            Controller_uploadDataToDatabase controller = fxmlLoaderUploadToDB.getController();
            controller.setStage(stage);
            Scene SCENE_UPLOAD_DATA_BD = new Scene(root, 768, 700);
            return SCENE_UPLOAD_DATA_BD;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
