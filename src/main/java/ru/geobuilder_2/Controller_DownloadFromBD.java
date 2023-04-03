package ru.geobuilder_2;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import ru.geobuilder_2.persistence.entity.Instance;
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.repository.ObjectJpaRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Controller_DownloadFromBD {

    @FXML
    private Label quantityOfObjectLabel;

    @FXML
    private Label quantityOfInstanceLabel;

    int indObjectJFX, indInstanceJFX;

    @FXML
    private TextFlow messageF;

    private ArrayList<String> inputData = new ArrayList<>();

    public ArrayList<String> getInputData() {
        return inputData;
    }

    public void setInputData(ArrayList<String> inputData) {
        this.inputData = inputData;
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextField getObjectСodeField() {
        return objectСodeField;
    }

    @FXML
    private TableView<ObjectJFX> objectTable;

    @FXML
    private TableColumn<ObjectJFX, Integer> idOColumn, codOColumn;

    @FXML
    private TableColumn<ObjectJFX, String> addressOColumn, operatorOColumn;

    @FXML
    private TableView<InstanceJFX> instanceTable;

    @FXML
    private TableColumn<InstanceJFX, Integer> idInstColumn;
    @FXML
    private TableColumn<InstanceJFX, String> typeOfWorkColumn, numberBasisOfWorkFieldColumn, authorColumn;

    @FXML
    private TableColumn<InstanceJFX, Timestamp> photoDateColumn, DateColumn;

    @FXML
    private TextField objectСodeField;

    @FXML
    private Button goBackButton;

    @FXML
    private MenuItem megafon, mts, vimpel, t2, non;

    @FXML
    private MenuItem maintenance, emergencyWork;

    @FXML
    private CheckBox аddressCheck, operatorCheck, typeOfWorkCheck, basisOfWorksCheck, authorCheck, dateOfShootingCheck;

    @FXML
    private TextField addressTextField, basisOfWorksTextField, authorTextField;

    @FXML
    private TextField specifiedOperatorTextField;

    @FXML
    private SplitMenuButton operatorSplMenu, typeOfWorkSplMenu;

    @FXML
    private DatePicker dateOfShootingDatePicker;

    @FXML
    private Label numberOfObjects, numberOfInstance;

    private void updateNumberOfObjects() {
        numberOfObjects.setText("Найдено объектов: " + objectsJFX.size());
    }

    private void updateNumberOfInstance() {
        numberOfObjects.setText("Найдено состояний: " + instancesJFX.size());
    }

    @FXML
    private void setAddressCheck() {
        if (аddressCheck.isSelected()) {
            addressTextField.setDisable(false);
        } else {
            addressTextField.setDisable(true);
        }
    }

    @FXML
    private void setOperatorCheck() {
        if (operatorCheck.isSelected()) {
            operatorSplMenu.setDisable(false);
        } else {
            operatorSplMenu.setDisable(true);
        }
    }

    @FXML
    public void setMegafon(ActionEvent event) {
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(megafon.getText());
    }

    @FXML
    public void setMts(ActionEvent event) {
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(mts.getText());
    }

    @FXML
    public void setT2(ActionEvent event) {
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(t2.getText());
    }

    @FXML
    public void setVimpel(ActionEvent event) {
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(vimpel.getText());
    }

    @FXML
    public void setNon(ActionEvent event) {
        specifiedOperatorTextField.setDisable(false);
        operatorSplMenu.setText(non.getText());
    }

    public void setTypeOfWork(ActionEvent event) {
        if (typeOfWorkCheck.isSelected()) {
            typeOfWorkSplMenu.setDisable(false);
        } else {
            typeOfWorkSplMenu.setDisable(true);
        }
    }

    public void setBasisOfWorks(ActionEvent event) {
        if (basisOfWorksCheck.isSelected()) {
            basisOfWorksTextField.setDisable(false);
        } else {
            basisOfWorksTextField.setDisable(false);
        }
    }

    public void setAuthor(ActionEvent event) {
        if (authorCheck.isSelected()) {
            authorTextField.setDisable(false);
        } else {
            authorTextField.setDisable(true);
        }
    }

    public void setDateOfShooting(ActionEvent event) {
        if (dateOfShootingCheck.isSelected()) {
            dateOfShootingDatePicker.setDisable(false);
        }
    }


    /**
     * Возвращаемся на предыдущую страницу
     */
    @FXML
    public void goingBackToNewCal() {
        goBackButton.getScene().getWindow().hide();
    }

    public void setMaintenance(ActionEvent event) {
        typeOfWorkSplMenu.setText(maintenance.getText());
    }

    public void setEmergencyWork(ActionEvent event) {
        typeOfWorkSplMenu.setText(emergencyWork.getText());
    }

//    private void generateData() {
//
//        for (int i = 0; i < pointsData.size(); i++) {
//            inputData.add(pointsData.get(i).getNamePoint());
//            inputData.add(pointsData.get(i).getDistancePoint());
//            inputData.add(pointsData.get(i).getVAnglePoint());
//            inputData.add(pointsData.get(i).getHAnglePoint());
//            for (int k = 0; k < obsListsAngles.get(i).size(); k++) {
//                inputData.add(obsListsAngles.get(i).get(k).getVAngle());
//                inputData.add(obsListsAngles.get(i).get(k).getHAngle());
//            }
//        }
//    }

    private ObservableList<ObjectJFX> objectsJFX = FXCollections.observableArrayList();
    private ObservableList<InstanceJFX> instancesJFX = FXCollections.observableArrayList();
    private ObservableList<Rib> ribsBD = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        // Таблица Object
        idOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, Integer>("idObjectJFX"));
        codOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, Integer>("numberObjectJFX"));
        operatorOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, String>("operatorObjectJFX"));
        addressOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, String>("addressObjectJFX"));

        objectTable.setItems(this.objectsJFX);

        //Таблица Instance
        idInstColumn.setCellValueFactory(new PropertyValueFactory<InstanceJFX, Integer>("idInstanceJFX"));
        typeOfWorkColumn.setCellValueFactory(new PropertyValueFactory<InstanceJFX, String>("typeOfWorkInstanceJFX"));
        numberBasisOfWorkFieldColumn.setCellValueFactory(new PropertyValueFactory<InstanceJFX, String>("numberBasisOfWorkInstanceJFX"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<InstanceJFX, String>("authorInstanceJFX"));
        photoDateColumn.setCellValueFactory(new PropertyValueFactory<InstanceJFX, Timestamp>("photoDateColumnInstanceJFX"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<InstanceJFX, Timestamp>("creationDateInstanceJFX"));

        instanceTable.setItems(this.instancesJFX);

        //Берем из выделеной строки idObject
        objectTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showIdObject(newValue));
        //Берем из выделеной строки idInstance
        instanceTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showIdInstance(newValue));

//        objectsJFX.addListener((ListChangeListener<ObjectJFX>) c ->
//                getQuantityOfObjects());
//        instancesJFX.addListener((ListChangeListener<InstanceJFX>) c ->
//                getQuantityOfInstance());
    }

//    private void getQuantityOfObjects() {
//        quantityOfObjectLabel.setText(String.valueOf(objectsJFX.size()));
//    }
//
//    private void getQuantityOfInstance() {
//        quantityOfInstanceLabel.setText(String.valueOf(instancesJFX.size()));
//    }

    /**
     * Задаем значение idObject
     *
     * @param newValue
     */
    private void showIdObject(ObjectJFX newValue) {
        indObjectJFX = (newValue.getIdObjectJFX());
        quantityOfObjectLabel.setText(String.valueOf(objectsJFX.size()));
        quantityOfInstanceLabel.setText(String.valueOf(instancesJFX.size()));
    }

    /**
     * Задаем значение idInstance
     *
     * @param newValue
     */
    private void showIdInstance(InstanceJFX newValue) {
        indInstanceJFX = (newValue.getIdInstanceJFX());
    }

    /**
     * При нажатии кнопки "Получить список объектов", выгружаем все объекты, которые есть, из БД
     */
    @FXML
    private void loadObjectJFX() {
        this.objectsJFX = this.reedObjectFromDB();
        if (objectsJFX.isEmpty()) {
            this.objectTable.setItems(this.objectsJFX);
            outputMessage("В БД пока нет объектов");
        } else {
            this.objectTable.setItems(this.objectsJFX);
            outputMessage("Кол. объектов в БД: " + objectsJFX.size());
        }
        updateNumberOfObjects();
    }

    /**
     * При нажатии кнопки "Получить список состояний", выгружаем все состояния выделенного объекта
     */
    @FXML
    private void loadInstanceJFX() {
        this.instancesJFX = this.readInstanceFromObject(indObjectJFX);
        if (instancesJFX.isEmpty()) {
            this.instanceTable.setItems(this.instancesJFX);
            outputMessage("В БД пока нет состояний");
        } else {
            this.instanceTable.setItems(this.instancesJFX);
            outputMessage("Кол. состояний в БД: " + instancesJFX.size());
        }
        updateNumberOfInstance();
    }

    /**
     * Метод получения списока объектов из базы с помошью метода readAllObject() класса ObjectJpaRepository
     */
    private ObservableList<ObjectJFX> reedObjectFromDB() {
        ArrayList<ObjectJFX> objectsJFX = new ArrayList<ObjectJFX>();
        List<Object> objects = new ArrayList<Object>();
        ObjectJpaRepository objectJpaRepository = new ObjectJpaRepository();
        objects = objectJpaRepository.readAllObject();
        for (Object object : objects) {
            ObjectJFX objectJFX = new ObjectJFX(object);
            objectsJFX.add(objectJFX);
        }
        return FXCollections.observableArrayList(objectsJFX);
    }

    /**
     * Метод получения списка состояний из списка объектов
     */
    private ObservableList<InstanceJFX> readInstanceFromObject(int idObj) {
        ArrayList<InstanceJFX> instancesJFX = new ArrayList<InstanceJFX>();
        for (ObjectJFX objJFX : objectsJFX) {
            if (objJFX.getIdObjectJFX() == idObj) {
                for (Instance inst : objJFX.getObject().getInstances()) {
                    instancesJFX.add(new InstanceJFX(inst));
                }
              break;
            }
        }
        return FXCollections.observableArrayList(instancesJFX);
    }
//    private ObservableList<InstanceJFX> reedInstanceFromDB() {
//        ArrayList<InstanceJFX> instancesJFX = new ArrayList<InstanceJFX>();
//        List<Instance> instances = new ArrayList<Instance>();
//        InstanceJpaRepository instanceJpaRepository = new InstanceJpaRepository();
//        instances = (List<Instance>) instanceJpaRepository.readInstance(indObjectJFX);
//        for (Instance instance : instances) {
//            InstanceJFX instanceJFX = new InstanceJFX((int) instance.getId(), instance.getTypeOfWork(),
//                    instance.getNumberBasisOfWork(), instance.getAuthor(), instance.getPhotoDateColumn().toString(),
//                    instance.getCreationDate().toString());
//            instancesJFX.add(instanceJFX);
//        }
//        return FXCollections.observableArrayList(instancesJFX);
//    }

    /**
     * Метод вывода сообщений
     *
     * @param mess
     */
    public void outputMessage(String mess) {
        //messageF.getChildren().clear();
        Text text = new Text(mess + "\n");
        messageF.getChildren().add(text);
    }

//    /**
//     * При нажатии кнопки "Получить список объектов", выгружаем все объекты, которые есть, из БД
//     */
//    @FXML
//    private void loadObjectJFX() {
//        this.objectsJFX = this.reedObjectFromDB();
//        if (objectsJFX.isEmpty()) {
//            this.objectTable.setItems(this.objectsJFX);
//            outputMessage("В БД пока нет объектов");
//        } else {
//            this.objectTable.setItems(this.objectsJFX);
//            outputMessage("Кол. объектов в БД: " + objectsJFX.size());
//        }
//    }
}
