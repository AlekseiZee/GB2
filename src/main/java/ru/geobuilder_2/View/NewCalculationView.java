package ru.geobuilder_2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.geobuilder_2.Controller_Main;
import ru.geobuilder_2.Controller_NewCalculation;
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
            Parent root = fxmlLoaderNewCalculation.load();
            Controller_NewCalculation controller = fxmlLoaderNewCalculation.getController();
            controller.setStage(stage);
//            stage.setMinWidth(768);
//            stage.setMinHeight(700);
//            stage.setMaxWidth(768);
//            stage.setMaxHeight(700);
            Scene SCENE_NEW_CALCULATION = new Scene(root);
            return SCENE_NEW_CALCULATION;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
