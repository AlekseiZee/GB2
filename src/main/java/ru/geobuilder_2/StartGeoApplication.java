package ru.geobuilder_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGeoApplication extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        iniRoot();
    }


    /**
     * Инициализирует первую страницу
     */
    public void iniRoot() {

        //Временно. для работы с окном "Новый расчет"
//        Controller_Main controller_main = new Controller_Main();
//        controller_main.openNewCalculationWindow("new_calculation-view.fxml", 768, 700);

        // Временно. Для удобстава работы с окном "Ручной ввод"
        Controller_NewCalculation controller_NewCalculation = new Controller_NewCalculation();
        controller_NewCalculation.openDataEntryWindowManually();

        // Разкомментировать после окончания работы с окном "Ручной ввод"
//        try {
//
//            FXMLLoader fxmlLoader = new FXMLLoader(StartGeoApplication.class.getResource("main-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 681, 529);
//            stage.setTitle("GeoBuilder 2.0");
//            stage.setMinWidth(681); // не позволит уменьшить наше окно до нуля по ширене
//            stage.setMinHeight(529); // не позволит уменьшить окно до нуля по высоте
//            stage.setMaxWidth(681);
//            stage.setMaxHeight(529);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    /**
     * Возвращает главную сцену.
     * @return
     */
    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}