package ru.geobuilder_2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
            FXMLLoader fxmlLoaderUploadData = new FXMLLoader(StartGeoApplication.class.getResource("uploadDataToDatabase-view"));
            Scene SCENE_UPLOAD_DATA_BD = new Scene(fxmlLoaderUploadData.load(), 768, 700);
            return SCENE_UPLOAD_DATA_BD;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
