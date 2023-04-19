package ru.geobuilder_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Controller_manualInput {

    private ArrayList<String> inputData = new ArrayList<>();

    public ArrayList<String> getInputData() {
        return inputData;
    }

    public void setInputData(ArrayList<String> inputData) {
        this.inputData = inputData;
    }

    @FXML
    private Button addLineAnglesButton, addLineAnglesButton1, addLineAnglesButton2, addLineAnglesButton3,
            addLineAnglesButton4, addLineAnglesButton5, addLineAnglesButton6, addLineAnglesButton7,
            removeLineAngleButton, removeLineAngleButton1, removeLineAngleButton2, removeLineAngleButton3,
            removeLineAngleButton4, removeLineAngleButton5, removeLineAngleButton6, removeLineAngleButton7;

    @FXML
    private TextFlow massage;

    @FXML
    private Button goBackButton;

    @FXML
    private TableView<PointHeightJFX> pointTable;

    @FXML
    private TableColumn nameAnglePCol, nameAnglePCol1, nameAnglePCol2, nameAnglePCol3, nameAnglePCol4,
            nameAnglePCol5, nameAnglePCol6, nameAnglePCol7;

    @FXML
    private TableColumn<PointHeightJFX, Integer> idPCol;

    @FXML
    private TableColumn<PointHeightJFX, String> namePCol;

    @FXML
    private TableColumn<PointHeightJFX, String> distancePCol;

    @FXML
    private TableView<AngleHeightJFX> angleTable, angleTable1, angleTable2, angleTable3, angleTable4, angleTable5, angleTable6, angleTable7;

    @FXML
    private TableColumn<AngleHeightJFX, Integer> idAngleCol, idAngleCol1, idAngleCol2, idAngleCol3, idAngleCol4, idAngleCol5, idAngleCol6, idAngleCol7;

    @FXML
    private TableColumn<AngleHeightJFX, String> heightObjectCol, hAngleCol, heightObjectCol1, hAngleCol1, heightObjectCol2, hAngleCol2,
            heightObjectCol3, hAngleCol3, heightObjectCol4, hAngleCol4, heightObjectCol5, hAngleCol5, heightObjectCol6, hAngleCol6, heightObjectCol7, hAngleCol7;

    ArrayList<ArrayList<AngleHeightJFX>> obsListsAngles = new ArrayList<>();

    private ObservableList<PointHeightJFX> pointsData = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData1 = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData2 = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData3 = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData4 = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData5 = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData6 = FXCollections.observableArrayList();
    private ObservableList<AngleHeightJFX> anglesData7 = FXCollections.observableArrayList();

    private void generateData() {

        for (int i = 0; i < pointsData.size(); i++) {
            inputData.add(pointsData.get(i).getNamePointHeight());
            inputData.add(pointsData.get(i).getDistancePointHeight());
            if(!obsListsAngles.isEmpty()) {
                for (int k = 0; k < obsListsAngles.get(i).size(); k++) {
                    inputData.add(obsListsAngles.get(i).get(k).getHeightObjectJFX());
                    inputData.add(obsListsAngles.get(i).get(k).getHAngleJFX());
                }
            }
        }
    }

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {

        //loadPointAndAngle();

        // Таблица Points
        idPCol.setCellValueFactory(new PropertyValueFactory<PointHeightJFX, Integer>("idPointHeight"));
        namePCol.setCellValueFactory(new PropertyValueFactory<PointHeightJFX, String>("namePointHeight"));
        distancePCol.setCellValueFactory(new PropertyValueFactory<PointHeightJFX, String>("distancePointHeight"));

        pointTable.setItems(this.pointsData);

        pointTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        namePCol.setCellFactory(TextFieldTableCell.forTableColumn());
        distancePCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Таблица Angles0
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));


        angleTable.setItems(anglesData);
        angleTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

        // Таблица Angles1
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol1.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol1.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol1.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));

        angleTable1.setItems(anglesData1);
        angleTable1.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol1.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol1.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

        // Таблица Angles2
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol2.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol2.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol2.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));

        angleTable2.setItems(anglesData2);
        angleTable2.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol2.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol2.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

        // Таблица Angles3
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol3.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol3.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol3.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));

        angleTable3.setItems(anglesData3);
        angleTable3.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol3.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol3.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

        // Таблица Angles4
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol4.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol4.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol4.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));

        angleTable4.setItems(anglesData4);
        angleTable4.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol4.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol4.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

        // Таблица Angles5
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol5.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol5.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol5.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));

        angleTable5.setItems(anglesData5);
        angleTable5.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol5.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol5.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

        // Таблица Angles6
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol6.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol6.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol6.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));

        angleTable6.setItems(anglesData6);
        angleTable6.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol6.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol6.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

        // Таблица Angles7
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol7.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, Integer>("idAngleHeightJFX"));
        heightObjectCol7.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("heightObjectJFX"));
        hAngleCol7.setCellValueFactory(new PropertyValueFactory<AngleHeightJFX, String>("hAngleHeightJFX"));

        angleTable7.setItems(anglesData7);
        angleTable7.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        heightObjectCol7.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());
        hAngleCol7.setCellFactory(TextFieldTableCell.<AngleHeightJFX>forTableColumn());

//        // Добавляем слушателя для автоматического отслеживания изменений в листе
//        pointsData.addListener((ListChangeListener<Point>) c -> updateNamePoint());
//        pointsData.addListener((ListChangeListener<Point>) c -> scenarioOfManipulatorStates());

//        // Добавляем слушателя для автоматического отслеживания изменений в листе
//        pointsData.addListener((ListChangeListener<Point>) c ->
//                printP());
//
//        //pointTable.chang().addListener((observable, oldValue, newValue)
//        // Следим за изменением в ячейке namePCol. Если изменилось значение то запускается метод getNameP() с
//        // с новым значением.
//       pointTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//           if (!newValue.getNamePoint().isEmpty() && newValue.getNamePoint().matches("[a-zA-Z][1|2]?")){
//
//               //massage.getChildren().add(new Text("Ячейка не пустая"));
//           }
//       });
////               (observable, oldValue, newValue) -> checkingInputData(String.valueOf(newValue)));

        //Запрет на сортировку столбцов
        idPCol.setSortable(false);
        namePCol.setSortable(false);
        distancePCol.setSortable(false);

        idAngleCol.setSortable(false);
        heightObjectCol.setSortable(false);
        hAngleCol.setSortable(false);
        heightObjectCol1.setSortable(false);
        hAngleCol1.setSortable(false);
        heightObjectCol2.setSortable(false);
        hAngleCol2.setSortable(false);
        heightObjectCol3.setSortable(false);
        hAngleCol3.setSortable(false);
        heightObjectCol4.setSortable(false);
        hAngleCol4.setSortable(false);
        heightObjectCol5.setSortable(false);
        hAngleCol5.setSortable(false);
        heightObjectCol6.setSortable(false);
        hAngleCol6.setSortable(false);
        heightObjectCol7.setSortable(false);
        hAngleCol7.setSortable(false);
    }

    /**
     * Обновляем заголовки таблицы с угоами в зависимости от названий стоянок в таблице Point
     */
    private void updateNamePoint() {
        if (!angleTable.isDisabled()) {
            nameAnglePCol.setText(pointsData.get(0).getNamePointHeight());
            if (!angleTable1.isDisabled()) {
                nameAnglePCol1.setText(pointsData.get(1).getNamePointHeight());
                if (!angleTable2.isDisabled()) {
                    nameAnglePCol2.setText(pointsData.get(2).getNamePointHeight());
                    if (!angleTable3.isDisabled()) {
                        nameAnglePCol3.setText(pointsData.get(3).getNamePointHeight());
                        if (!angleTable4.isDisabled()) {
                            nameAnglePCol4.setText(pointsData.get(4).getNamePointHeight());
                            if (!angleTable5.isDisabled()) {
                                nameAnglePCol5.setText(pointsData.get(5).getNamePointHeight());
                                if (!angleTable6.isDisabled()) {
                                    nameAnglePCol6.setText(pointsData.get(6).getNamePointHeight());
                                    if (!angleTable7.isDisabled()) {
                                        nameAnglePCol7.setText(pointsData.get(7).getNamePointHeight());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void printP() {
        massage.getChildren().add(new Text("Работает метод"));
    }

    // Проверка вводимых данных в ячейку namePCol
    private void checkingInputData(PointJFX pointJFX) {
        if (!pointTable.getItems().isEmpty()) {
            massage.getChildren().clear();
            massage.getChildren().add(new Text("Ячейка не пустая"));
        } else {
            massage.getChildren().clear();
            massage.getChildren().add(new Text("Ячейка пустая"));
        }
//            String newValueNamePoint = point.getNamePoint();
//            String newValueDistancePoint = point.getDistancePoint();
//            String newValueVAnglePoint = point.getVAnglePoint();
//            String newValueHAnglePoint = point.getHAnglePoint();
//            Pattern patterNamePoint = Pattern.compile("[a-zA-Z][1|2]?");
//            Pattern patterDistancePoint = Pattern.compile("");
//            Pattern patternVAnglePoint = Pattern.compile("");
//            Pattern patterHAnglePoint = Pattern.compile("");
//            Matcher matcherNamePoint = patterNamePoint.matcher(newValueNamePoint);
//            Matcher matcherDistancePoint = patterDistancePoint.matcher(newValueDistancePoint);
//            Matcher matcherVAnglePoint = patternVAnglePoint.matcher(newValueVAnglePoint);
//            Matcher matcherHAnglePoint = patterHAnglePoint.matcher(newValueHAnglePoint);
//            if (!matcherNamePoint.matches()) {
//                massage.getChildren().clear();
//                Text text = new Text("Не верный формат \n");
//                massage.getChildren().add(text);
//            } else {
//                massage.getChildren().clear();
//            }
//        }
    }


    // Добавление стоянки (Point)
    @FXML
    private void addLinePoint() {
        if (this.pointTable.getItems().size() < 8) {
            PointHeightJFX pointHeightJFX = new PointHeightJFX(this.pointTable.getItems().size() + 1,
                    "","");
            pointsData.add(pointHeightJFX);

            scenarioOfManipulatorStates();
        }
    }

    private void scenarioOfManipulatorStates() {
        switch (this.pointTable.getItems().size() - 1) {
            case 0 -> {
                angleTable.setDisable(false);
                addLineAnglesButton.setDisable(false);
                removeLineAngleButton.setDisable(false);
                break;
            }
            case 1 -> {
                angleTable.setDisable(false);
                angleTable1.setDisable(false);
                addLineAnglesButton.setDisable(false);
                addLineAnglesButton1.setDisable(false);
                removeLineAngleButton.setDisable(false);
                removeLineAngleButton1.setDisable(false);
                break;
            }
            case 2 -> {
                angleTable.setDisable(false);
                angleTable1.setDisable(false);
                angleTable2.setDisable(false);
                addLineAnglesButton.setDisable(false);
                addLineAnglesButton1.setDisable(false);
                addLineAnglesButton2.setDisable(false);
                removeLineAngleButton.setDisable(false);
                removeLineAngleButton1.setDisable(false);
                removeLineAngleButton2.setDisable(false);
                break;
            }
            case 3 -> {
                angleTable.setDisable(false);
                angleTable1.setDisable(false);
                angleTable2.setDisable(false);
                angleTable3.setDisable(false);
                addLineAnglesButton.setDisable(false);
                addLineAnglesButton1.setDisable(false);
                addLineAnglesButton2.setDisable(false);
                addLineAnglesButton3.setDisable(false);
                removeLineAngleButton.setDisable(false);
                removeLineAngleButton1.setDisable(false);
                removeLineAngleButton2.setDisable(false);
                removeLineAngleButton3.setDisable(false);
                break;
            }
            case 4 -> {
                angleTable.setDisable(false);
                angleTable1.setDisable(false);
                angleTable2.setDisable(false);
                angleTable3.setDisable(false);
                angleTable4.setDisable(false);
                addLineAnglesButton.setDisable(false);
                addLineAnglesButton1.setDisable(false);
                addLineAnglesButton2.setDisable(false);
                addLineAnglesButton3.setDisable(false);
                addLineAnglesButton4.setDisable(false);
                removeLineAngleButton.setDisable(false);
                removeLineAngleButton1.setDisable(false);
                removeLineAngleButton2.setDisable(false);
                removeLineAngleButton3.setDisable(false);
                removeLineAngleButton4.setDisable(false);
                break;
            }
            case 5 -> {
                angleTable.setDisable(false);
                angleTable1.setDisable(false);
                angleTable2.setDisable(false);
                angleTable3.setDisable(false);
                angleTable4.setDisable(false);
                angleTable5.setDisable(false);
                addLineAnglesButton.setDisable(false);
                addLineAnglesButton1.setDisable(false);
                addLineAnglesButton2.setDisable(false);
                addLineAnglesButton3.setDisable(false);
                addLineAnglesButton4.setDisable(false);
                addLineAnglesButton5.setDisable(false);
                removeLineAngleButton.setDisable(false);
                removeLineAngleButton1.setDisable(false);
                removeLineAngleButton2.setDisable(false);
                removeLineAngleButton3.setDisable(false);
                removeLineAngleButton4.setDisable(false);
                removeLineAngleButton5.setDisable(false);
                break;
            }
            case 6 -> {
                angleTable.setDisable(false);
                angleTable1.setDisable(false);
                angleTable2.setDisable(false);
                angleTable3.setDisable(false);
                angleTable4.setDisable(false);
                angleTable5.setDisable(false);
                angleTable6.setDisable(false);
                addLineAnglesButton.setDisable(false);
                addLineAnglesButton1.setDisable(false);
                addLineAnglesButton2.setDisable(false);
                addLineAnglesButton3.setDisable(false);
                addLineAnglesButton4.setDisable(false);
                addLineAnglesButton5.setDisable(false);
                addLineAnglesButton6.setDisable(false);
                removeLineAngleButton.setDisable(false);
                removeLineAngleButton1.setDisable(false);
                removeLineAngleButton2.setDisable(false);
                removeLineAngleButton3.setDisable(false);
                removeLineAngleButton4.setDisable(false);
                removeLineAngleButton5.setDisable(false);
                removeLineAngleButton6.setDisable(false);
                break;
            }
            case 7 -> {
                angleTable.setDisable(false);
                angleTable1.setDisable(false);
                angleTable2.setDisable(false);
                angleTable3.setDisable(false);
                angleTable4.setDisable(false);
                angleTable5.setDisable(false);
                angleTable6.setDisable(false);
                angleTable7.setDisable(false);
                addLineAnglesButton.setDisable(false);
                addLineAnglesButton1.setDisable(false);
                addLineAnglesButton2.setDisable(false);
                addLineAnglesButton3.setDisable(false);
                addLineAnglesButton4.setDisable(false);
                addLineAnglesButton5.setDisable(false);
                addLineAnglesButton6.setDisable(false);
                addLineAnglesButton7.setDisable(false);
                removeLineAngleButton.setDisable(false);
                removeLineAngleButton1.setDisable(false);
                removeLineAngleButton2.setDisable(false);
                removeLineAngleButton3.setDisable(false);
                removeLineAngleButton4.setDisable(false);
                removeLineAngleButton5.setDisable(false);
                removeLineAngleButton6.setDisable(false);
                removeLineAngleButton7.setDisable(false);
                break;
            }
        }
    }

    // Удаление стоянки (Point)
    @FXML
    private void removeLinePoint() {

        if (pointsData.size() > 0) {
            switch (this.pointTable.getItems().size() - 1) {
                case 0 -> {
                    angleTable.setDisable(true);
                    angleTable1.setDisable(true);
                    angleTable2.setDisable(true);
                    angleTable3.setDisable(true);
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    angleTable6.setDisable(true);
                    angleTable7.setDisable(true);
                    addLineAnglesButton.setDisable(true);
                    addLineAnglesButton1.setDisable(true);
                    addLineAnglesButton2.setDisable(true);
                    addLineAnglesButton3.setDisable(true);
                    addLineAnglesButton4.setDisable(true);
                    addLineAnglesButton5.setDisable(true);
                    addLineAnglesButton6.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton.setDisable(true);
                    removeLineAngleButton1.setDisable(true);
                    removeLineAngleButton2.setDisable(true);
                    removeLineAngleButton3.setDisable(true);
                    removeLineAngleButton4.setDisable(true);
                    removeLineAngleButton5.setDisable(true);
                    removeLineAngleButton6.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
                case 1 -> {
                    angleTable1.setDisable(true);
                    angleTable2.setDisable(true);
                    angleTable3.setDisable(true);
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    angleTable6.setDisable(true);
                    angleTable7.setDisable(true);
                    addLineAnglesButton1.setDisable(true);
                    addLineAnglesButton2.setDisable(true);
                    addLineAnglesButton3.setDisable(true);
                    addLineAnglesButton4.setDisable(true);
                    addLineAnglesButton5.setDisable(true);
                    addLineAnglesButton6.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton1.setDisable(true);
                    removeLineAngleButton2.setDisable(true);
                    removeLineAngleButton3.setDisable(true);
                    removeLineAngleButton4.setDisable(true);
                    removeLineAngleButton5.setDisable(true);
                    removeLineAngleButton6.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
                case 2 -> {
                    angleTable2.setDisable(true);
                    angleTable3.setDisable(true);
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    angleTable6.setDisable(true);
                    angleTable7.setDisable(true);
                    addLineAnglesButton2.setDisable(true);
                    addLineAnglesButton3.setDisable(true);
                    addLineAnglesButton4.setDisable(true);
                    addLineAnglesButton5.setDisable(true);
                    addLineAnglesButton6.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton2.setDisable(true);
                    removeLineAngleButton3.setDisable(true);
                    removeLineAngleButton4.setDisable(true);
                    removeLineAngleButton5.setDisable(true);
                    removeLineAngleButton6.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
                case 3 -> {
                    angleTable3.setDisable(true);
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    angleTable6.setDisable(true);
                    angleTable7.setDisable(true);
                    addLineAnglesButton3.setDisable(true);
                    addLineAnglesButton4.setDisable(true);
                    addLineAnglesButton5.setDisable(true);
                    addLineAnglesButton6.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton3.setDisable(true);
                    removeLineAngleButton4.setDisable(true);
                    removeLineAngleButton5.setDisable(true);
                    removeLineAngleButton6.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
                case 4 -> {
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    angleTable6.setDisable(true);
                    angleTable7.setDisable(true);
                    addLineAnglesButton4.setDisable(true);
                    addLineAnglesButton5.setDisable(true);
                    addLineAnglesButton6.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton4.setDisable(true);
                    removeLineAngleButton5.setDisable(true);
                    removeLineAngleButton6.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
                case 5 -> {
                    angleTable5.setDisable(true);
                    angleTable6.setDisable(true);
                    angleTable7.setDisable(true);
                    addLineAnglesButton5.setDisable(true);
                    addLineAnglesButton6.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton5.setDisable(true);
                    removeLineAngleButton6.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
                case 6 -> {
                    angleTable6.setDisable(true);
                    angleTable7.setDisable(true);
                    addLineAnglesButton6.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton6.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
                case 7 -> {
                    angleTable7.setDisable(true);
                    addLineAnglesButton7.setDisable(true);
                    removeLineAngleButton7.setDisable(true);
                    break;
                }
            }
            pointTable.getItems().remove(pointsData.size() - 1);
        }
    }

    // Добавление углов (Angles)
    private void addLineAng(ObservableList anglesDataList) {

        updateNamePoint();

        AngleHeightJFX angleHeightJFX = new AngleHeightJFX(anglesDataList.size() + 1, "", "");
        anglesDataList.add(angleHeightJFX);
//        obsListsAngles.add((ArrayList<Angle>) anglesDataList);
    }

    // Удаление углов (Angles)
    private void removeLineAng(ObservableList anglesDataList, TableView angleTable) {
        if (anglesDataList.size() > 0) {
            angleTable.getItems().remove(anglesDataList.size() - 1);
        }
    }

    @FXML
    private void addLineAngles() {
        addLineAng(anglesData);
    }

    @FXML
    private void removeLineAngles() {
        removeLineAng(anglesData, angleTable);
    }

    @FXML
    private void addLineAngles1() {
        addLineAng(anglesData1);
    }

    @FXML
    private void removeLineAngles1() {
        removeLineAng(anglesData1, angleTable1);
    }

    @FXML
    private void addLineAngles2() {
        addLineAng(anglesData2);
    }

    @FXML
    private void removeLineAngles2() {
        removeLineAng(anglesData2, angleTable2);
    }

    @FXML
    private void addLineAngles3() {
        addLineAng(anglesData3);
    }

    @FXML
    private void removeLineAngles3() {
        removeLineAng(anglesData3, angleTable3);
    }

    @FXML
    private void addLineAngles4() {
        addLineAng(anglesData4);
    }

    @FXML
    private void removeLineAngles4() {
        removeLineAng(anglesData4, angleTable4);
    }

    @FXML
    private void addLineAngles5() {
        addLineAng(anglesData5);
    }

    @FXML
    private void removeLineAngles5() {
        removeLineAng(anglesData5, angleTable5);
    }

    @FXML
    private void addLineAngles6() {
        addLineAng(anglesData6);
    }

    @FXML
    private void removeLineAngles6() {
        removeLineAng(anglesData6, angleTable6);
    }

    @FXML
    private void addLineAngles7() {
        addLineAng(anglesData7);
    }

    @FXML
    private void removeLineAngles7() {
        removeLineAng(anglesData7, angleTable7);
    }

//    // Добавление новой стоянки
//    @FXML
//    public void addColumnPoint() {
//
//        ObservableList<Angle> anglesData2 = FXCollections.observableArrayList();
//
//        Angle angle = new Angle(this.angleTable.getItems().size()+1, "", "12345", "98765");
//        //angleTable.getItems().add(angle);
//        anglesData2.add(angle);
//
//        TableColumn nameAnglePCol = new TableColumn(this.pointsData.get(this.angleTable.getColumns().size()-2).getNamePoint());
//        TableColumn vAngleCol = new TableColumn("Верт2. угол");
//        TableColumn hAngleCol = new TableColumn("Гор2. угол");
//        nameAnglePCol.getColumns().setAll(vAngleCol, hAngleCol);
//        angleTable.getColumns().addAll(nameAnglePCol);
//
//        angleTable.setItems(anglesData2);
//
//        idAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
//        namePointFromTableCol.setCellValueFactory(new PropertyValueFactory<Angle, String>("namePointFromTable"));
//        vAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
//        hAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));
//        angleTable.setItems(anglesData2);
//        angleTable.setEditable(true);
//
//        // Разрешение на ввод данных в ячейку
//        vAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
//        hAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
//
//        idAngleCol.setSortable(false);
//        vAngleCol.setSortable(false);
//        hAngleCol.setSortable(false);
//
//    }
//
//    // Удаление стоянки
//    @FXML
//    private void removeColumnPoint() {
//        if(angleTable.getColumns().size() > 3){
//            angleTable.getColumns().remove(angleTable.getColumns().size()-1);
//        }
//    }

//    private String nameP = pointsData.get(pointsData.size() - 1).getNamePoint();;
//
//    public String getNameP(Point point) {
//        nameP = pointsData.get(pointsData.size() - 1).getNamePoint();
//        //nameP = point.getNamePoint();
//        return nameP;
//    }

    // Нужно для инициализации измененных значений в ячейках. Без него данные не воспринимаются
    public void onEditChangerNamePoint(TableColumn.CellEditEvent<PointJFX, String> pointStringCellEditEvent) {
        PointHeightJFX pointHeightJFX = pointTable.getSelectionModel().getSelectedItem();
        pointHeightJFX.setNamePointHeight(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerDistancePoint(TableColumn.CellEditEvent<PointJFX, String> pointStringCellEditEvent) {
        PointHeightJFX pointHeightJFX = pointTable.getSelectionModel().getSelectedItem();
        pointHeightJFX.setDistancePointHeight(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX = angleTable.getSelectionModel().getSelectedItem();
        angleHeightJFX.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX = angleTable.getSelectionModel().getSelectedItem();
        angleHeightJFX.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight1(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX1 = angleTable1.getSelectionModel().getSelectedItem();
        angleHeightJFX1.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle1(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX1 = angleTable1.getSelectionModel().getSelectedItem();
        angleHeightJFX1.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight2(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX2 = angleTable2.getSelectionModel().getSelectedItem();
        angleHeightJFX2.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle2(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX2 = angleTable2.getSelectionModel().getSelectedItem();
        angleHeightJFX2.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight3(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX3 = angleTable3.getSelectionModel().getSelectedItem();
        angleHeightJFX3.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle3(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX3 = angleTable3.getSelectionModel().getSelectedItem();
        angleHeightJFX3.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight4(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX4 = angleTable4.getSelectionModel().getSelectedItem();
        angleHeightJFX4.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle4(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX4 = angleTable4.getSelectionModel().getSelectedItem();
        angleHeightJFX4.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight5(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX5 = angleTable5.getSelectionModel().getSelectedItem();
        angleHeightJFX5.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle5(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX5 = angleTable5.getSelectionModel().getSelectedItem();
        angleHeightJFX5.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight6(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX6 = angleTable6.getSelectionModel().getSelectedItem();
        angleHeightJFX6.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle6(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX6 = angleTable6.getSelectionModel().getSelectedItem();
        angleHeightJFX6.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHeight7(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX7 = angleTable7.getSelectionModel().getSelectedItem();
        angleHeightJFX7.setHeightObjectJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle7(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleHeightJFX angleHeightJFX7 = angleTable7.getSelectionModel().getSelectedItem();
        angleHeightJFX7.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Возвращаемся в окно "Новый расчет"
     *
     * @param event
     */
    @FXML
    public void goingBackToNewCal(ActionEvent event) {

        savePointAndAngle();

        goBackButton.getScene().getWindow().hide();
    }

    /**
     * Сохранение Point
     *
     * @param pointsData
     * @throws IOException
     */
    private void serializePointHeight(ObservableList<PointHeightJFX> pointsData) throws IOException {
        File fileFS = new File("D:\\TestGB\\cache\\point.txt");
        try (FileOutputStream fosManInpWin = new FileOutputStream(fileFS);
             ObjectOutputStream oosPoint = new ObjectOutputStream(fosManInpWin)) {
            ArrayList<PointHeightJFX> pointsManInpWin = new ArrayList<PointHeightJFX>(pointsData);
            oosPoint.writeObject(pointsManInpWin);
            //oosPoint.writeObject(new ArrayList<Point> (pointsManInpWin));
        }
    }

    /**
     * Десериализация Point
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private ObservableList<PointHeightJFX> deserializePointHeight() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<PointHeightJFX> pointsData = new ArrayList<PointHeightJFX>();
        File fileFS = new File("D:\\TestGB\\cache\\point.txt");
        try (FileInputStream fisPoint = new FileInputStream(fileFS); ObjectInputStream oisPoint = new ObjectInputStream(fisPoint)) {
            pointsData = (ArrayList<PointHeightJFX>) oisPoint.readObject();
        }
        return FXCollections.observableArrayList(pointsData);
    }

    /**
     * Сохранение Angle
     *
     * @param listsAngles
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void serializeAnglesHeight(ArrayList<ArrayList<AngleHeightJFX>> listsAngles) throws FileNotFoundException, IOException {
        File fileAng = new File("D:\\TestGB\\cache\\angles.txt");
        try (FileOutputStream fosManInpWinAng = new FileOutputStream(fileAng);
             ObjectOutputStream oosAngle = new ObjectOutputStream(fosManInpWinAng)) {
            ArrayList<AngleHeightJFX> anglesManInpWin = new ArrayList<AngleHeightJFX>(this.anglesData);
            ArrayList<AngleHeightJFX> anglesManInpWin1 = new ArrayList<AngleHeightJFX>(this.anglesData1);
            ArrayList<AngleHeightJFX> anglesManInpWin2 = new ArrayList<AngleHeightJFX>(this.anglesData2);
            ArrayList<AngleHeightJFX> anglesManInpWin3 = new ArrayList<AngleHeightJFX>(this.anglesData3);
            ArrayList<AngleHeightJFX> anglesManInpWin4 = new ArrayList<AngleHeightJFX>(this.anglesData4);
            ArrayList<AngleHeightJFX> anglesManInpWin5 = new ArrayList<AngleHeightJFX>(this.anglesData5);
            ArrayList<AngleHeightJFX> anglesManInpWin6 = new ArrayList<AngleHeightJFX>(this.anglesData6);
            ArrayList<AngleHeightJFX> anglesManInpWin7 = new ArrayList<AngleHeightJFX>(this.anglesData7);
            listsAngles.add(anglesManInpWin);
            listsAngles.add(anglesManInpWin1);
            listsAngles.add(anglesManInpWin2);
            listsAngles.add(anglesManInpWin3);
            listsAngles.add(anglesManInpWin4);
            listsAngles.add(anglesManInpWin5);
            listsAngles.add(anglesManInpWin6);
            listsAngles.add(anglesManInpWin7);
            oosAngle.writeObject(listsAngles);
        }
    }

    /**
     * Десериализация Angle
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private ArrayList<ArrayList<AngleHeightJFX>> deserializeAnglesHeight() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ArrayList<AngleHeightJFX>> anglesDataS = new ArrayList<>();
        File fileAng = new File("D:\\TestGB\\cache\\angles.txt");
        try (FileInputStream fisAngles = new FileInputStream(fileAng);
             ObjectInputStream oisAngles = new ObjectInputStream(fisAngles)) {
            anglesDataS = (ArrayList<ArrayList<AngleHeightJFX>>) oisAngles.readObject();
        }
        return anglesDataS;
    }

    /**
     * Нажимаем для сериализации Point и Angle
     */
    @FXML
    void savePointAndAngle() {
        try {
            this.serializePointHeight(this.pointsData);
            this.serializeAnglesHeight(obsListsAngles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        generateData();
    }

    @FXML
    private void loadPointAndAngle() throws IOException, ClassNotFoundException {
        try {
            this.pointsData = this.deserializePointHeight();
            this.pointTable.setItems(this.pointsData);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        if (pointsData.size() > 0 && pointsData.get(0) != null) {
            this.anglesData = FXCollections.observableArrayList(
                    this.deserializeAnglesHeight().get(0)
            );
            this.angleTable.setItems(anglesData);
            scenarioOfManipulatorStates();
            updateNamePoint();
            if (pointsData.size() > 1 && pointsData.get(1) != null) {
                this.anglesData1 = FXCollections.observableArrayList(
                        this.deserializeAnglesHeight().get(1)
                );
                this.angleTable1.setItems(anglesData1);
                scenarioOfManipulatorStates();
                updateNamePoint();
                if (pointsData.size() > 2 && pointsData.get(2) != null) {
                    this.anglesData2 = FXCollections.observableArrayList(
                            this.deserializeAnglesHeight().get(2)
                    );
                    this.angleTable2.setItems(anglesData2);
                    scenarioOfManipulatorStates();
                    updateNamePoint();
                    if (pointsData.size() > 3 && pointsData.get(3) != null) {
                        this.anglesData3 = FXCollections.observableArrayList(
                                this.deserializeAnglesHeight().get(3)
                        );
                        this.angleTable3.setItems(anglesData3);
                        scenarioOfManipulatorStates();
                        updateNamePoint();
                        if (pointsData.size() > 4 && pointsData.get(4) != null) {
                            this.anglesData4 = FXCollections.observableArrayList(
                                    this.deserializeAnglesHeight().get(4)
                            );
                            this.angleTable4.setItems(anglesData4);
                            scenarioOfManipulatorStates();
                            updateNamePoint();
                            if (pointsData.size() > 5 && pointsData.get(5) != null) {
                                this.anglesData5 = FXCollections.observableArrayList(
                                        this.deserializeAnglesHeight().get(5)
                                );
                                this.angleTable5.setItems(anglesData5);
                                scenarioOfManipulatorStates();
                                updateNamePoint();
                                if (pointsData.size() > 6 && pointsData.get(6) != null) {
                                    this.anglesData6 = FXCollections.observableArrayList(
                                            this.deserializeAnglesHeight().get(6)
                                    );
                                    this.angleTable6.setItems(anglesData6);
                                    scenarioOfManipulatorStates();
                                    updateNamePoint();
                                    if (pointsData.size() > 7 && pointsData.get(7) != null) {
                                        this.anglesData7 = FXCollections.observableArrayList(
                                                this.deserializeAnglesHeight().get(7)
                                        );
                                        this.angleTable7.setItems(anglesData7);
                                        scenarioOfManipulatorStates();
                                        updateNamePoint();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


