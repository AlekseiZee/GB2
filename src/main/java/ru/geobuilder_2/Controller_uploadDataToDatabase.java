package ru.geobuilder_2;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;

public class Controller_uploadDataToDatabase {

    @FXML
    private TableView<RibBD> tableRibBD;

    @FXML
    private TableColumn<RibBD, Integer> tierColumnBD;

    @FXML
    private TableColumn<RibBD, String> ribLengthColumnBD;

    @FXML
    private Button commitInstanceButton;

    @FXML
    private MenuItem megafon;

    @FXML
    private MenuItem mts;

    @FXML
    private MenuItem vimpel;

    @FXML
    private MenuItem t2;

    @FXML
    private MenuItem non;

    @FXML
    private Button addNewObjectButton;

    @FXML
    private Button removeObjectButton;

    @FXML
    private Button openJOBButton;

    @FXML
    private DatePicker photoDate;

    @FXML
    private TextField author;

    @FXML
    private TextField numberBasisOfWorkField;

    @FXML
    private SplitMenuButton typeOfWork;

    @FXML
    private TextField objectReferenceField;

    @FXML
    private Button removeRibObjectButton;

    @FXML
    private Button addNewRibObjectButton;

    @FXML
    private TextField newOperator;

    @FXML
    private SplitMenuButton operator;

    @FXML
    private TextField objectСodeField;

    @FXML
    private TextField address;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField addressFieldFileJOB;

    @FXML
    public void showLogOfAngularMeasurements (ActionEvent event){
        FXMLLoader fxmlLoaderLogOfAngularMeasurements = new FXMLLoader();
        fxmlLoaderLogOfAngularMeasurements.setLocation(getClass().getResource("log_of_angular_measurements-view.fxml"));
        try {
            fxmlLoaderLogOfAngularMeasurements.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoaderLogOfAngularMeasurements.getRoot();
        Stage stage = new Stage();
        stage.setTitle("GeoBuilder 2.0");
        stage.setMinWidth(1194);
        stage.setMinHeight(854);
        stage.setMaxWidth(1194);
        stage.setMaxHeight(854);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void addNewObject(ActionEvent event) {
        objectСodeField.setDisable(false);
        address.setDisable(false);
        operator.setDisable(false);
        tableRibBD.setDisable(false);
        addNewRibObjectButton.setDisable(false);
        removeRibObjectButton.setDisable(false);
    }

    @FXML
    public void setMegafon(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(megafon.getText());
    }

    @FXML
    public void setMts(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(mts.getText());
    }

    @FXML
    public void setT2(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(t2.getText());
    }

    @FXML
    public void setVimpel(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(vimpel.getText());
    }

    @FXML
    public void setNon(ActionEvent event){
        newOperator.setDisable(false);
        operator.setText(non.getText());
    }


    @FXML
    public void addNewInstance(ActionEvent event) {
        commitInstanceButton.setDisable(false);
        objectReferenceField.setDisable(false);
        typeOfWork.setDisable(false);
        numberBasisOfWorkField.setDisable(false);
        author.setDisable(false);
        photoDate.setDisable(false);
        addressFieldFileJOB.setDisable(false);
        openJOBButton.setDisable(false);
    }

    @FXML
    public void openJOB(ActionEvent event) {
        FileChooser fileChooserJob = new FileChooser();
        fileChooserJob.setInitialDirectory(new File("C:\\Users\\Home")); // Указываем какую папку открыть изначально
        fileChooserJob.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt Files", "*.txt")); // Задаем расширения для выбора конкретных файлов
        File selectedFile = fileChooserJob.showOpenDialog(null);
        if (selectedFile != null) {
            addressFieldFileJOB.setText(selectedFile.getAbsolutePath());
        } else {
            System.out.println("file is not valid");
        }
    }

    @FXML
    public void goingBackToNewCal(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(StartGeoApplication.getStage());
        alert.setTitle("Предупреждение");
        alert.setHeaderText("Вы уверены, что хотите вернуться назад?");
        alert.setContentText("Если да, нажмите \"ок\"");

        alert.showAndWait();
        goBackButton.getScene().getWindow().hide();
        StartGeoApplication startGeoApplication = new StartGeoApplication();
        startGeoApplication.iniRoot();
    }

    private ObservableList<RibBD> ribsBD = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        tierColumnBD.setCellValueFactory(new PropertyValueFactory<RibBD, Integer>("tierBD"));
        ribLengthColumnBD.setCellValueFactory(new PropertyValueFactory<RibBD, String>("ribLengthBD"));

        // указываем, что хотим использовать этот набор данных из коллекции RibsList
        tableRibBD.setItems(ribsBD);

        // Разрешаем изменение в ячейке
        tableRibBD.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        ribLengthColumnBD.setCellFactory(TextFieldTableCell.forTableColumn());

        //Запрет на сортировку столбцов
        tierColumnBD.setSortable(false);
        ribLengthColumnBD.setSortable(false);

    }

    @FXML
    public void addRibBD() {
        RibBD ribBD = new RibBD("");
        tableRibBD.getItems().add(ribBD);
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void removeRibBD(){
        tableRibBD.getItems().remove(ribsBD.size() - 1);
        RibBD.setCountBD();
    }
}


