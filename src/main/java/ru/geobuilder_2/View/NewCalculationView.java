package ru.geobuilder_2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.geobuilder_2.StartGeoApplication;

import java.io.IOException;

public class NewCalculationView implements ViewMaker {
    private Stage stage;

    public NewCalculationView(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Scene getScene() {
        try {
            FXMLLoader fxmlLoaderNewCalculation = new FXMLLoader(StartGeoApplication.class.getResource("new_calculation-view.fxml"));
            Scene SCENE_NEW_CALCULATION = new Scene(fxmlLoaderNewCalculation.load(), 768, 700);
            return SCENE_NEW_CALCULATION;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
