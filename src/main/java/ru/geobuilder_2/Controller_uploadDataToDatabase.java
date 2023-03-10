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
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.geobuilder_2.persistence.entity.Instance;
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.repository.ObjectJpaRepository;
import ru.geobuilder_2.persistence.repository.RibJpaRepository;
import ru.geobuilder_2.persistence.tools.PersistenceManager;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller_uploadDataToDatabase {
    @FXML
    private TextFlow messageF;

    @FXML
    private TableView<Rib> tableRibBD;

    @FXML
    private TableView<Object> objectTable;

    @FXML
    private TableColumn<Object, Integer> idOColumn, codOColumn;

    @FXML
    private TableColumn<Object, String> addressOColumn, operatorOColumn;

    @FXML
    private TableView<Instance> instanceTable;

    @FXML
    private TableColumn<Instance, Integer> idInstColumn;
    @FXML
    private TableColumn<Instance, String> typeOfWorkColumn, numberBasisOfWorkFieldColumn, authorColumn;

    @FXML
    private TableColumn<Instance, Timestamp> photoDateColumn, DateColumn;

    @FXML
    private TableColumn<Rib, Integer> tierColumnBD;

    @FXML
    private TableColumn<Rib, String> ribLengthColumnBD;

    @FXML
    private Button commitInstanceButton;

    @FXML
    private MenuItem maintenance, accident;

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
    private Label objectReferenceField;

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


    /**
     * Вызываем окно "журнал угловых измерений"
     *
     * @param event
     */
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


    /**
     * Открываем функционал для добавления объекта в БД.
     *
     * @param
     */
    @FXML
    public void addNewObject() {
        objectСodeField.setDisable(false);
        addressObjectField.setDisable(false);
        operator.setDisable(false);
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
    public void setMaintenance(ActionEvent event) {
        typeOfWork.setText(maintenance.getText());
    }

    @FXML
    public void setAccident(ActionEvent event) {
        typeOfWork.setText(accident.getText());
    }

    /**
     * Открываем функционал для добавления нового состояния
     *
     * @param event
     */
    @FXML
    public void addNewInstance(ActionEvent event) {
        objectReferenceField.setDisable(false);
        typeOfWork.setDisable(false);
        numberBasisOfWorkField.setDisable(false);
        author.setDisable(false);
        photoDate.setDisable(false);
        addressFieldFileJOB.setDisable(false);
        openJOBButton.setDisable(false);
        tableRibBD.setDisable(false);
        addNewRibObjectButton.setDisable(false);
        removeRibObjectButton.setDisable(false);
    }


    /**
     * Открываем окно для выбора файла с данными
     *
     * @param event
     */
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

    /**
     * Возвращаемся назад
     *
     * @param event
     */
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

    private ObservableList<Object> objectsData = FXCollections.observableArrayList();
    private ObservableList<Instance> instancesData = FXCollections.observableArrayList();
    private ObservableList<Rib> ribsBD = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        // Таблица Object
        idOColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
        codOColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("number"));
        addressOColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("operator"));
        operatorOColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("address"));

        objectTable.setItems(this.objectsData);

        idInstColumn.setCellValueFactory(new PropertyValueFactory<Instance, Integer>("id"));
        typeOfWorkColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("typeOfWork"));
        numberBasisOfWorkFieldColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("typeOfWork"));
        photoDateColumn.setCellValueFactory(new PropertyValueFactory<Instance, Timestamp>("photoDateColumn"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Instance, Timestamp>("creationDate"));

        instanceTable.setItems(this.instancesData);

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
        Rib rib = new Rib(this.tableRibBD.getItems().size() + 1, "");
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
    private ObservableList<Object> reedObjectFromDB() {
        List<Object> objects = new ArrayList<Object>();
        ObjectJpaRepository objectJpaRepository = new ObjectJpaRepository();
        objects = objectJpaRepository.readAllObject();
        return FXCollections.observableArrayList(objects);
    }


    /**
     * Записывает entity. Делается через транзакцию.
     *
     * @return
     */
    @FXML
    private void uploadObject(ActionEvent event) {
        String operatorName;
        if (operator.getText() == non.getText()){
            operatorName = newOperator.getText();
        } else {
            operatorName = operator.getText();
        }
        ObjectJpaRepository objectJpaRepository = new ObjectJpaRepository();
        Object o = objectJpaRepository.createObject(Integer.valueOf(objectСodeField.getText()), operatorName,
                addressObjectField.getText());

        if (o != null) {
            messageF.getChildren().clear();
            Text mes = new Text("Объект записан");
            messageF.getChildren().add(mes);
        } else {
            messageF.getChildren().clear();
            Text mes = new Text("Объект не записался");
            messageF.getChildren().add(mes);
        }
        Instance instance = new Instance();
        instance.setTypeOfWork("ART");
        instance.setNumberBasisOfWork("qwe");
        instance.setAuthor("Aleksei");


//        Instance instance2 = new Instance();
//        instance2.setTypeOfWork("asdad");
//        instance2.setNumberBasisOfWork("werwe");
//        instance2.setAuthor("Pavel");

        o.addInstance(instance);
        //o.addInstance(instance2);
        try {

            EntityManager em = null;
            EntityTransaction transaction = null;

            em = PersistenceManager.INSTANCE.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(o);
            //em.flush(); // отправляем в базу все что сделали
            transaction.commit(); // завершили транзакцию
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


//    @FXML
//    private ObservableList<Rib> reedRibsFromDB(){
//        List<ru.geobuilder_2.persistence.entity.Rib> ribs = new ArrayList<>();
//        RibJpaRepository ribJpaRepository = new RibJpaRepository();
//        ribs = RibJpaRepository.readAllRib();
//        return (ObservableList<Rib>) FXCollections.observableArrayList(ribs);
//    }

}


