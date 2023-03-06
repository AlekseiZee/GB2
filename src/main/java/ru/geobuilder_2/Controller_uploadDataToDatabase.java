package ru.geobuilder_2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.repository.ObjectJpaRepository;
import ru.geobuilder_2.persistence.repository.RibJpaRepository;
import ru.geobuilder_2.persistence.tools.PersistenceManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller_uploadDataToDatabase {

    @FXML
    private TableView<Rib> tableRibBD;

    @FXML
    private TableView<Object> objectTable;

    @FXML
    private TableColumn<Object, Integer> idOColumn, codOColumn;

    @FXML
    private TableColumn<Object, String> addressOColumn, operatorOColumn;

    @FXML
    private TableColumn<Rib, Integer> tierColumnBD;

    @FXML
    private TableColumn<Rib, String> ribLengthColumnBD;

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
    private TextField objectСodeField, addressObjectField;

    @FXML
    private TextField address;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField addressFieldFileJOB;

    @FXML
    public void showLogOfAngularMeasurements(ActionEvent event) {
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
    public void setMegafon(ActionEvent event) {
        newOperator.setDisable(true);
        operator.setText(megafon.getText());
    }

    @FXML
    public void setMts(ActionEvent event) {
        newOperator.setDisable(true);
        operator.setText(mts.getText());
    }

    @FXML
    public void setT2(ActionEvent event) {
        newOperator.setDisable(true);
        operator.setText(t2.getText());
    }

    @FXML
    public void setVimpel(ActionEvent event) {
        newOperator.setDisable(true);
        operator.setText(vimpel.getText());
    }

    @FXML
    public void setNon(ActionEvent event) {
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
        fileChooserJob.setInitialDirectory(new File("D:\\TestGB")); // Указываем какую папку открыть изначально
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

    private ObservableList<Rib> ribsBD = FXCollections.observableArrayList();

    private ObservableList<Object> objectsData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        // Таблица Object
        idOColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
        codOColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("number"));
        addressOColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("operator"));
        operatorOColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("address"));


        objectTable.setItems(this.objectsData);


        tierColumnBD.setCellValueFactory(new PropertyValueFactory<Rib, Integer>("tier"));
        ribLengthColumnBD.setCellValueFactory(new PropertyValueFactory<Rib, String>("ribLength"));

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
        Rib rib = new Rib(this.tableRibBD.getItems().size()+1, "");
        tableRibBD.getItems().add(rib);
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void removeRibBD() {
        tableRibBD.getItems().remove(ribsBD.size() - 1);
    }

    /**
     * Получаем список объектов из базы
     */
    @FXML
    private ObservableList<Object> reedObjectFromDB(){
        List<Object> objects = new ArrayList<Object>();
        ObjectJpaRepository objectJpaRepository = new ObjectJpaRepository();
        objects = objectJpaRepository.readAllObject();
        return FXCollections.observableArrayList(objects);
    }


    /**
     *  Записывает entity. Делается через транзакцию.
     * @return
     */
    @FXML
    private void uploadObject(ActionEvent event){
        ObjectJpaRepository objectJpaRepository = new ObjectJpaRepository();
        objectJpaRepository.createObject(Integer.valueOf(objectСodeField.getText()), objectСodeField.getText(), addressObjectField.getText());
    }

//    @FXML
//    private ObservableList<Rib> reedRibsFromDB(){
//        List<ru.geobuilder_2.persistence.entity.Rib> ribs = new ArrayList<>();
//        RibJpaRepository ribJpaRepository = new RibJpaRepository();
//        ribs = RibJpaRepository.readAllRib();
//        return (ObservableList<Rib>) FXCollections.observableArrayList(ribs);
//    }

}


