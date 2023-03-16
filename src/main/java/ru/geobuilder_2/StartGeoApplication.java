package ru.geobuilder_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.geobuilder_2.View.MainView;
import ru.geobuilder_2.View.NewCalculationView;
import ru.geobuilder_2.View.UploadDataToDBView;
import ru.geobuilder_2.model.SceneName;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StartGeoApplication extends Application {

    private static Map<SceneName, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) {

        // Create and store all scenes up front
        scenes.put(SceneName.MAIN_GB2, new MainView(stage).getScene());
        scenes.put(SceneName.SCENE_NEW_CALCULATION, new NewCalculationView(stage).getScene());
        scenes.put(SceneName.SCENE_UPLOAD_DATA_BD, new UploadDataToDBView(stage).getScene());

        // Start with the main scene
        stage.setScene(scenes.get(SceneName.MAIN_GB2));
        stage.setTitle("GeoBuilder 2.0");
        stage.show();
    }

    /** Returns a Map of the scenes by {@link SceneName} */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }

    public static void main(String[] args) {
        launch(args);
    }


//    private static Stage stage;
//
//    @Override
//    public void start(Stage stage) {
//        this.stage = stage;
//
//        iniRoot();
//    }
//
//
//    /**
//     * Инициализирует первую страницу
//     */
//    public void iniRoot() {
//
//        //Временно. для работы с окном "Новый расчет"
////        Controller_Main controller_main = new Controller_Main();
////        controller_main.openNewCalculationWindow("new_calculation-view.fxml", 768, 700);
//
////        // Временно. Для удобстава работы с окном "Ручной ввод"
////        Controller_NewCalculation controller_NewCalculation = new Controller_NewCalculation();
////        controller_NewCalculation.openDataEntryWindowManually();
//
//        // Разкомментировать после окончания работы с окном "Ручной ввод"
//        try {
//
//            FXMLLoader fxmlLoaderMainView = new FXMLLoader(StartGeoApplication.class.getResource("main-view.fxml"));
//            Scene sceneMainView = new Scene(fxmlLoaderMainView.load(), 768, 700);
//
//            FXMLLoader fxmlLoaderNewCalculationView = new FXMLLoader(StartGeoApplication.class.getResource("new_calculation-view.fxml"));
//            Scene sceneNewCalculationView = new Scene(fxmlLoaderNewCalculationView.load(), 768, 700);
//
//
//            stage.setTitle("GeoBuilder 2.0");
//            stage.setMinWidth(768); // не позволит уменьшить наше окно до нуля по ширене
//            stage.setMinHeight(700); // не позволит уменьшить окно до нуля по высоте
//            stage.setMaxWidth(768);
//            stage.setMaxHeight(700);
//            stage.setScene(sceneMainView);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * Возвращает главную сцену.
//     * @return
//     */
//    public static Stage getStage() {
//        return stage;
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
}