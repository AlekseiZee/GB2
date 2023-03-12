package ru.geobuilder_2;

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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.geobuilder_2.persistence.entity.Instance;
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.repository.InstanceJpaRepository;
import ru.geobuilder_2.persistence.repository.ObjectJpaRepository;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller_uploadDataToDatabase {
    @FXML
    private TextFlow messageF;

    @FXML
    private TableView<Rib> tableRibBD;

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

        findObject();

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

    String nameObject;
    String addressObject;
    String operatorObject;

    Long indexObject;
    String typeOfWorkInstance;
    String numberBasisOfWorkFieldInstance;


    /**
     * Заносим созданый объект в базу данных.
     *
     * @return
     */
    @FXML
    private void uploadObject(ActionEvent event) {

        ObjectJpaRepository objectJpaRepository = new ObjectJpaRepository();
        Object obj = null;

        if (entranceControlUploadDataBaseObject()) {
            try {
                obj = objectJpaRepository.createObject(nameObject, operatorObject, addressObject);
            } catch (Exception e) {
                Text mes = new Text(
                        e.getMessage().isEmpty() ? "Ошибка в запросе SQL" + "\n" : e.getMessage() + "\n"
                );
                messageF.getChildren().add(mes);
            }
        }

        if (obj != null) {
            //messageF.getChildren().clear();
            outputMessage("Объект записан");

            objectСodeField.clear();
            addressObjectField.clear();
            operator.setText("");
            newOperator.clear();
        } else {
            outputMessage("Объект не записан");
        }
//        Instance instance = new Instance();
//        instance.setTypeOfWork("ART");
//        instance.setNumberBasisOfWork("qwe");
//        instance.setAuthor("Aleksei");
//
//
//        Instance instance2 = new Instance();
//        instance2.setTypeOfWork("asdad");
//        instance2.setNumberBasisOfWork("werwe");
//        instance2.setAuthor("Pavel");
//
//        obj.addInstance(instance);
//        obj.addInstance(instance2);
//        try {
//
//            EntityManager em = null;
//            EntityTransaction transaction = null;
//
//            em = PersistenceManager.INSTANCE.getEntityManager();
//            transaction = em.getTransaction();
//            transaction.begin();
//            em.merge(obj);
//            //em.flush(); // отправляем в базу все что сделали
//            transaction.commit(); // завершили транзакцию
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
    }

    /**
     * Создаем и загружаем в БД состояние объекта со всеми данными
     * @param event
     * @throws Exception
     */
    @FXML
    private void uploadInstance(ActionEvent event) throws Exception {

        InstanceJpaRepository instanceJpaRepository = new InstanceJpaRepository();
        Instance instance = null;

        if (entranceControlUploadDataBaseInstance()) {
            try {
                instance = instanceJpaRepository.createInstanceForObjectWithData(indexObject, typeOfWork.getText(),
                        numberBasisOfWorkField.getText(), author.getText(), addressFieldFileJOB.getText());

            } catch (Exception e) {
                Text mes = new Text(
                        e.getMessage().isEmpty() ? "Ошибка в запросе SQL" + "\n" : e.getMessage() + "\n"
                );
                messageF.getChildren().add(mes);
            }
        }
        if (instance != null) {
            //messageF.getChildren().clear();
            outputMessage("Состояние записано");

            typeOfWork.setText("");
            numberBasisOfWorkField.clear();
            author.clear();

        } else {
            outputMessage("Состояние не записано");
        }
    }


//    @FXML
//    private ObservableList<Rib> reedRibsFromDB(){
//        List<ru.geobuilder_2.persistence.entity.Rib> ribs = new ArrayList<>();
//        RibJpaRepository ribJpaRepository = new RibJpaRepository();
//        ribs = RibJpaRepository.readAllRib();
//        return (ObservableList<Rib>) FXCollections.observableArrayList(ribs);
//    }

    /**
     * Метод для проверки правильности и полноты вводимых данных для создания Object
     *
     * @return
     */
    public Boolean entranceControlUploadDataBaseObject() {
        if (objectСodeField.getText() != null && !objectСodeField.equals("")) {
            Pattern patternNameObject = Pattern.compile("^\\d{2}[-]?[_]?\\d{3,5}");
            Matcher matcherNameObject = patternNameObject.matcher(objectСodeField.getText());
            if (matcherNameObject.matches()) {
                this.nameObject = objectСodeField.getText().trim();
                if (addressObjectField.getText() != null && !addressObjectField.getText().equals("")) {
                    this.addressObject = addressObjectField.getText().trim();
                    if (!operator.getText().equals("")) {
                        if (operator.getText() == non.getText()) {
                            if (!newOperator.getText().equals("")) {
                                this.operatorObject = newOperator.getText().trim();
                                return true;
                            } else {
                                outputMessage("Оператор объекта не заполнен");
                                return false;
                            }
                        } else {
                            this.operatorObject = operator.getText();
                            return true;
                        }
                    } else {
                        outputMessage("Оператор объекта не заполнен");
                        return false;
                    }
                } else {
                    outputMessage("Адрес объекта не заполнен");
                    return false;
                }
            } else {
                outputMessage("Не верный формат кода объекта");
                return false;
            }
        } else {
            outputMessage("Код объекта не заполнен");
            return false;
        }
    }

    public Boolean entranceControlUploadDataBaseInstance() {
        if (typeOfWork.getText() != null && !typeOfWork.equals("")) {
            this.typeOfWorkInstance = typeOfWork.getText().trim();
            if (numberBasisOfWorkField.getText() != null && !numberBasisOfWorkField.getText().equals("")) {
                this.numberBasisOfWorkFieldInstance = numberBasisOfWorkField.getText().trim();
                return true;
            } else {
                outputMessage("Не указано основание работ");
                return false;
            }
        } else {
            outputMessage("Не указан вид работ");
            return false;
        }
    }


    public void findObject() {
        objectTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRibsDetails(newValue));
        ;
    }

    private void showRibsDetails(ObjectJFX newValue) {
        newValue.getIdObjectJFX();
        objectReferenceField.setText(newValue.getNumberObjectJFX());
        indexObject = Long.valueOf(newValue.getIdObjectJFX());
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
}


