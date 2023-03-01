package ru.geobuilder_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller_Main implements Initializable {

    ArrayList<String> namesFiles = new ArrayList<>();

    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listViewGeoCal;
    @FXML
    private Button new_cal;

    @FXML
    private Button open;

    @FXML
    private Button search;


    @FXML
    public void getNewCal(ActionEvent event) {
        new_cal.getScene().getWindow().hide();
        openNewCalculationWindow("new_calculation-view.fxml", 768, 700);
    }

    /**
     * Метод открытия окна "Новый расчет"
     *
     * @param name
     * @param v
     * @param v1
     */
    protected void openNewCalculationWindow(String name, int v, int v1) {
        FXMLLoader fxmlLoaderNewCal = new FXMLLoader();
        fxmlLoaderNewCal.setLocation(getClass().getResource(name));
        try {
            fxmlLoaderNewCal.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoaderNewCal.getRoot();
        Stage stage = new Stage();
        stage.setTitle("GeoBuilder 2.0");
        stage.setMinWidth(v);
        stage.setMinHeight(v1);
        stage.setMaxWidth(v);
        stage.setMaxHeight(v1);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    /**
     * При нажатии переходим к выбору файлов
     *
     * @param event
     */
    @FXML
    void openFileCal(ActionEvent event) {
        FileChooser fileChooserGeoBuilder = new FileChooser();
        fileChooserGeoBuilder.setInitialDirectory(new File("D:\\TestGB")); // Указываем какую папку открыть изначально
        fileChooserGeoBuilder.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("geoBuilder Files", "*.txt")); // Задаем расширения для выбора конкретных файлов
        List<File> selectedFiles = fileChooserGeoBuilder.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            for (int i = 0; i < selectedFiles.size(); i++) {
                listViewGeoCal.getItems().add(selectedFiles.get(i).getAbsolutePath());
                namesFiles.add(selectedFiles.get(i).getName());
            }
        } else {
            System.out.println("file is not valid");
        }
    }

    @FXML
    void getSearch(ActionEvent event) {
        listViewGeoCal.getItems().clear();
        listViewGeoCal.getItems().addAll(searchList(searchBar.getText(), namesFiles));
    }

    @FXML
    public void uploadDataToDatabase(ActionEvent event) {
        new_cal.getScene().getWindow().hide();
        openNewCalculationWindow("uploadDataToDatabase-view.fxml", 1194, 854);
    }

    /**
     * Поиск файла
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewGeoCal.getItems().addAll(namesFiles);
    }

    private List<String> searchList(String searFiles, List<String> listOfStrings) {
        List<String> searchFileArrey = Arrays.asList(searFiles.trim().split(" "));
        return listOfStrings.stream().filter(imput -> {
            return searchFileArrey.stream().allMatch(namesFiles ->
                    imput.toLowerCase().contains(namesFiles.toLowerCase()));
        }).collect(Collectors.toList());
    }
}