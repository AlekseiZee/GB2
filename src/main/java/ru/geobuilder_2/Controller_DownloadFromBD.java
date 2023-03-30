package ru.geobuilder_2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import ru.geobuilder_2.model.SceneName;
import ru.geobuilder_2.persistence.entity.Instance;
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.repository.ObjectJpaRepository;
import ru.geobuilder_2.persistence.tools.PersistenceManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Controller_DownloadFromBD {

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
    private TableView<Instance> instanceTable;

    @FXML
    private TableColumn<Instance, Integer> idInstColumn;
    @FXML
    private TableColumn<Instance, String> typeOfWorkColumn, numberBasisOfWorkFieldColumn, authorColumn;

    @FXML
    private TableColumn<Instance, Timestamp> photoDateColumn, DateColumn;

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
        numberOfObjects.setText("Найдено объектов: " );
    }

    private void updateNumberOfInstance() {
        numberOfObjects.setText("Найдено состояний: " );
    }

    @FXML
    private void setAddressCheck(){
        if (аddressCheck.isSelected()){
            addressTextField.setDisable(false);
        } else {
            addressTextField.setDisable(true);
        }
    }

    @FXML
    private void setOperatorCheck(){
        if (operatorCheck.isSelected()){
            operatorSplMenu.setDisable(false);
        } else {
            operatorSplMenu.setDisable(true);
        }
    }

    @FXML
    public void setMegafon(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(megafon.getText());
    }

    @FXML
    public void setMts(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(mts.getText());
    }

    @FXML
    public void setT2(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(t2.getText());
    }

    @FXML
    public void setVimpel(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(vimpel.getText());
    }

    @FXML
    public void setNon(ActionEvent event){
        specifiedOperatorTextField.setDisable(false);
        operatorSplMenu.setText(non.getText());
    }

    public void setTypeOfWork(ActionEvent event) {
        if(typeOfWorkCheck.isSelected()){
            typeOfWorkSplMenu.setDisable(false);
        } else {
            typeOfWorkSplMenu.setDisable(true);
        }
    }

    public void setBasisOfWorks(ActionEvent event) {
        if(basisOfWorksCheck.isSelected()){
            basisOfWorksTextField.setDisable(false);
        } else {
            basisOfWorksTextField.setDisable(false);
        }
    }

    public void setAuthor(ActionEvent event) {
        if(authorCheck.isSelected()){
            authorTextField.setDisable(false);
        } else {
            authorTextField.setDisable(true);
        }
    }

    public void setDateOfShooting(ActionEvent event) {
       if(dateOfShootingCheck.isSelected()){
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
    private ObservableList<Instance> instancesData = FXCollections.observableArrayList();
    private ObservableList<Rib> ribsBD = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        // Таблица Object
        idOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, Integer>("idObjectJFX"));
        codOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, Integer>("numberObjectJFX"));
        operatorOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, String>("operatorObjectJFX"));
        addressOColumn.setCellValueFactory(new PropertyValueFactory<ObjectJFX, String>("addressObjectJFX"));

        objectTable.setItems(this.objectsJFX);

        idInstColumn.setCellValueFactory(new PropertyValueFactory<Instance, Integer>("id"));
        typeOfWorkColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("typeOfWork"));
        numberBasisOfWorkFieldColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("typeOfWork"));
        photoDateColumn.setCellValueFactory(new PropertyValueFactory<Instance, Timestamp>("photoDateColumn"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Instance, Timestamp>("creationDate"));

        instanceTable.setItems(this.instancesData);
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
            ObjectJFX objectJFX = new ObjectJFX((int) object.getId(), object.getNumber(),
                    object.getOperator(), object.getAddress());
            objectsJFX.add(objectJFX);
        }
        return FXCollections.observableArrayList(objectsJFX);
    }

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
