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
    private TableView<PointJFX> pointTable;

    @FXML
    private TableColumn nameAnglePCol, nameAnglePCol1, nameAnglePCol2, nameAnglePCol3, nameAnglePCol4,
            nameAnglePCol5, nameAnglePCol6, nameAnglePCol7;

    @FXML
    private TableColumn<PointJFX, Integer> idPCol;

    @FXML
    private TableColumn<PointJFX, String> namePCol;

    @FXML
    private TableColumn<PointJFX, String> distancePCol;

    @FXML
    private TableColumn<PointJFX, String> vAnglePCol;

    @FXML
    private TableColumn<PointJFX, String> hAnglePCol;

    @FXML
    private TableView<AngleJFX> angleTable, angleTable1, angleTable2, angleTable3, angleTable4, angleTable5, angleTable6, angleTable7;

    @FXML
    private TableColumn<AngleJFX, Integer> idAngleCol, idAngleCol1, idAngleCol2, idAngleCol3, idAngleCol4, idAngleCol5, idAngleCol6, idAngleCol7;

    @FXML
    private TableColumn<AngleJFX, String> vAngleCol, hAngleCol, vAngleCol1, hAngleCol1, vAngleCol2, hAngleCol2,
            vAngleCol3, hAngleCol3, vAngleCol4, hAngleCol4, vAngleCol5, hAngleCol5, vAngleCol6, hAngleCol6, vAngleCol7, hAngleCol7;

    ArrayList<ArrayList<AngleJFX>> obsListsAngles = new ArrayList<>();

    private ObservableList<PointJFX> pointsData = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData1 = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData2 = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData3 = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData4 = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData5 = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData6 = FXCollections.observableArrayList();
    private ObservableList<AngleJFX> anglesData7 = FXCollections.observableArrayList();

    private void generateData() {

        for (int i = 0; i < pointsData.size(); i++) {
            inputData.add(pointsData.get(i).getNamePoint());
            inputData.add(pointsData.get(i).getDistancePoint());
            inputData.add(pointsData.get(i).getVAnglePoint());
            inputData.add(pointsData.get(i).getHAnglePoint());
            for (int k = 0; k < obsListsAngles.get(i).size(); k++) {
                inputData.add(obsListsAngles.get(i).get(k).getVAngleJFX());
                inputData.add(obsListsAngles.get(i).get(k).getHAngleJFX());
            }
        }
    }

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {

        //loadPointAndAngle();

        // Таблица Points
        idPCol.setCellValueFactory(new PropertyValueFactory<PointJFX, Integer>("idPoint"));
        namePCol.setCellValueFactory(new PropertyValueFactory<PointJFX, String>("namePoint"));
        distancePCol.setCellValueFactory(new PropertyValueFactory<PointJFX, String>("distancePoint"));
        vAnglePCol.setCellValueFactory(new PropertyValueFactory<PointJFX, String>("vAnglePoint"));
        hAnglePCol.setCellValueFactory(new PropertyValueFactory<PointJFX, String>("hAnglePoint"));

        pointTable.setItems(this.pointsData);

        pointTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        namePCol.setCellFactory(TextFieldTableCell.forTableColumn());
        distancePCol.setCellFactory(TextFieldTableCell.forTableColumn());
        vAnglePCol.setCellFactory(TextFieldTableCell.forTableColumn());
        hAnglePCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Таблица Angles0
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));


        angleTable.setItems(anglesData);
        angleTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

        // Таблица Angles1
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol1.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol1.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol1.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));

        angleTable1.setItems(anglesData1);
        angleTable1.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol1.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol1.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

        // Таблица Angles2
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol2.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol2.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol2.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));

        angleTable2.setItems(anglesData2);
        angleTable2.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol2.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol2.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

        // Таблица Angles3
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol3.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol3.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol3.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));

        angleTable3.setItems(anglesData3);
        angleTable3.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol3.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol3.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

        // Таблица Angles4
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol4.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol4.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol4.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));

        angleTable4.setItems(anglesData4);
        angleTable4.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol4.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol4.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

        // Таблица Angles5
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol5.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol5.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol5.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));

        angleTable5.setItems(anglesData5);
        angleTable5.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol5.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol5.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

        // Таблица Angles6
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol6.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol6.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol6.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));

        angleTable6.setItems(anglesData6);
        angleTable6.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol6.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol6.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

        // Таблица Angles7
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol7.setCellValueFactory(new PropertyValueFactory<AngleJFX, Integer>("idAngleJFX"));
        vAngleCol7.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("vAngleJFX"));
        hAngleCol7.setCellValueFactory(new PropertyValueFactory<AngleJFX, String>("hAngleJFX"));

        angleTable7.setItems(anglesData7);
        angleTable7.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol7.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());
        hAngleCol7.setCellFactory(TextFieldTableCell.<AngleJFX>forTableColumn());

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
        vAnglePCol.setSortable(false);
        hAnglePCol.setSortable(false);

        idAngleCol.setSortable(false);
        vAngleCol.setSortable(false);
        hAngleCol.setSortable(false);
    }

    /**
     * Обновляем заголовки таблицы с угоами в зависимости от названий стоянок в таблице Point
     */
    private void updateNamePoint() {
        if (!angleTable.isDisabled()) {
            nameAnglePCol.setText(pointsData.get(0).getNamePoint());
            if (!angleTable1.isDisabled()) {
                nameAnglePCol1.setText(pointsData.get(1).getNamePoint());
                if (!angleTable2.isDisabled()) {
                    nameAnglePCol2.setText(pointsData.get(2).getNamePoint());
                    if (!angleTable3.isDisabled()) {
                        nameAnglePCol3.setText(pointsData.get(3).getNamePoint());
                        if (!angleTable4.isDisabled()) {
                            nameAnglePCol4.setText(pointsData.get(4).getNamePoint());
                            if (!angleTable5.isDisabled()) {
                                nameAnglePCol5.setText(pointsData.get(5).getNamePoint());
                                if (!angleTable6.isDisabled()) {
                                    nameAnglePCol6.setText(pointsData.get(6).getNamePoint());
                                    if (!angleTable7.isDisabled()) {
                                        nameAnglePCol7.setText(pointsData.get(7).getNamePoint());
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
            PointJFX pointJFX = new PointJFX(this.pointTable.getItems().size() + 1, "",
                    "", "", "");
            pointsData.add(pointJFX);

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

        AngleJFX angleJFX = new AngleJFX(anglesDataList.size() + 1, "", "");
        anglesDataList.add(angleJFX);
//        obsListsAngles.add((ArrayList<Angle>) anglesDataList);
    }

    // Удаление углов (Angles)
    private void removeLineAng(ObservableList anglesDataList, TableView angleTable) {
        angleTable.getItems().remove(anglesDataList.size() - 1);
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
        PointJFX pointJFX = pointTable.getSelectionModel().getSelectedItem();
        pointJFX.setNamePoint(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerDistancePoint(TableColumn.CellEditEvent<PointJFX, String> pointStringCellEditEvent) {
        PointJFX pointJFX = pointTable.getSelectionModel().getSelectedItem();
        pointJFX.setDistancePoint(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngPoint(TableColumn.CellEditEvent<PointJFX, String> pointStringCellEditEvent) {
        PointJFX pointJFX = pointTable.getSelectionModel().getSelectedItem();
        pointJFX.setVAnglePoint(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngPoint(TableColumn.CellEditEvent<PointJFX, String> pointStringCellEditEvent) {
        PointJFX pointJFX = pointTable.getSelectionModel().getSelectedItem();
        pointJFX.setHAnglePoint(pointStringCellEditEvent.getNewValue());
    }


    public void onEditChangerVAngle(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX = angleTable.getSelectionModel().getSelectedItem();
        angleJFX.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX = angleTable.getSelectionModel().getSelectedItem();
        angleJFX.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle1(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX1 = angleTable1.getSelectionModel().getSelectedItem();
        angleJFX1.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle1(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX1 = angleTable1.getSelectionModel().getSelectedItem();
        angleJFX1.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle2(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX2 = angleTable2.getSelectionModel().getSelectedItem();
        angleJFX2.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle2(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX2 = angleTable2.getSelectionModel().getSelectedItem();
        angleJFX2.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle3(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX3 = angleTable3.getSelectionModel().getSelectedItem();
        angleJFX3.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle3(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX3 = angleTable3.getSelectionModel().getSelectedItem();
        angleJFX3.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle4(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX4 = angleTable4.getSelectionModel().getSelectedItem();
        angleJFX4.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle4(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX4 = angleTable4.getSelectionModel().getSelectedItem();
        angleJFX4.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle5(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX5 = angleTable5.getSelectionModel().getSelectedItem();
        angleJFX5.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle5(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX5 = angleTable5.getSelectionModel().getSelectedItem();
        angleJFX5.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle6(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX6 = angleTable6.getSelectionModel().getSelectedItem();
        angleJFX6.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle6(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX6 = angleTable6.getSelectionModel().getSelectedItem();
        angleJFX6.setHAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle7(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX7 = angleTable7.getSelectionModel().getSelectedItem();
        angleJFX7.setVAngleJFX(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle7(TableColumn.CellEditEvent<AngleJFX, String> angleStringCellEditEvent) {
        AngleJFX angleJFX7 = angleTable7.getSelectionModel().getSelectedItem();
        angleJFX7.setHAngleJFX(angleStringCellEditEvent.getNewValue());
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
    private void serializePoint(ObservableList<PointJFX> pointsData) throws IOException {
        File fileFS = new File("D:\\TestGB\\cache\\point.txt");
        try (FileOutputStream fosManInpWin = new FileOutputStream(fileFS);
             ObjectOutputStream oosPoint = new ObjectOutputStream(fosManInpWin)) {
            ArrayList<PointJFX> pointsManInpWin = new ArrayList<PointJFX>(pointsData);
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
    private ObservableList<PointJFX> deserializePoint() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<PointJFX> pointsData = new ArrayList<PointJFX>();
        File fileFS = new File("D:\\TestGB\\cache\\point.txt");
        try (FileInputStream fisPoint = new FileInputStream(fileFS); ObjectInputStream oisPoint = new ObjectInputStream(fisPoint)) {
            pointsData = (ArrayList<PointJFX>) oisPoint.readObject();
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
    private void serializeAngles(ArrayList<ArrayList<AngleJFX>> listsAngles) throws FileNotFoundException, IOException {
        File fileAng = new File("D:\\TestGB\\cache\\angles.txt");
        try (FileOutputStream fosManInpWinAng = new FileOutputStream(fileAng);
             ObjectOutputStream oosAngle = new ObjectOutputStream(fosManInpWinAng)) {
            ArrayList<AngleJFX> anglesManInpWin = new ArrayList<AngleJFX>(this.anglesData);
            ArrayList<AngleJFX> anglesManInpWin1 = new ArrayList<AngleJFX>(this.anglesData1);
            ArrayList<AngleJFX> anglesManInpWin2 = new ArrayList<AngleJFX>(this.anglesData2);
            ArrayList<AngleJFX> anglesManInpWin3 = new ArrayList<AngleJFX>(this.anglesData3);
            ArrayList<AngleJFX> anglesManInpWin4 = new ArrayList<AngleJFX>(this.anglesData4);
            ArrayList<AngleJFX> anglesManInpWin5 = new ArrayList<AngleJFX>(this.anglesData5);
            ArrayList<AngleJFX> anglesManInpWin6 = new ArrayList<AngleJFX>(this.anglesData6);
            ArrayList<AngleJFX> anglesManInpWin7 = new ArrayList<AngleJFX>(this.anglesData7);
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
    private ArrayList<ArrayList<AngleJFX>> deserializeAngles() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ArrayList<AngleJFX>> anglesDataS = new ArrayList<>();
        File fileAng = new File("D:\\TestGB\\cache\\angles.txt");
        try (FileInputStream fisAngles = new FileInputStream(fileAng);
             ObjectInputStream oisAngles = new ObjectInputStream(fisAngles)) {
            anglesDataS = (ArrayList<ArrayList<AngleJFX>>) oisAngles.readObject();
        }
        return anglesDataS;
    }

    /**
     * Нажимаем для десериализации Point и Angle
     */
    @FXML
    void savePointAndAngle() {
        try {
            this.serializePoint(this.pointsData);
            this.serializeAngles(obsListsAngles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        generateData();
    }

    @FXML
    private void loadPointAndAngle() throws IOException, ClassNotFoundException {
        try {
            this.pointsData = this.deserializePoint();
            this.pointTable.setItems(this.pointsData);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        if (pointsData.size() > 0 && pointsData.get(0) != null) {
            this.anglesData = FXCollections.observableArrayList(
                    this.deserializeAngles().get(0)
            );
            this.angleTable.setItems(anglesData);
            scenarioOfManipulatorStates();
            updateNamePoint();
            if (pointsData.size() > 1 && pointsData.get(1) != null) {
                this.anglesData1 = FXCollections.observableArrayList(
                        this.deserializeAngles().get(1)
                );
                this.angleTable1.setItems(anglesData1);
                scenarioOfManipulatorStates();
                updateNamePoint();
                if (pointsData.size() > 2 && pointsData.get(2) != null) {
                    this.anglesData2 = FXCollections.observableArrayList(
                            this.deserializeAngles().get(2)
                    );
                    this.angleTable2.setItems(anglesData2);
                    scenarioOfManipulatorStates();
                    updateNamePoint();
                    if (pointsData.size() > 3 && pointsData.get(3) != null) {
                        this.anglesData3 = FXCollections.observableArrayList(
                                this.deserializeAngles().get(3)
                        );
                        this.angleTable3.setItems(anglesData3);
                        scenarioOfManipulatorStates();
                        updateNamePoint();
                        if (pointsData.size() > 4 && pointsData.get(4) != null) {
                            this.anglesData4 = FXCollections.observableArrayList(
                                    this.deserializeAngles().get(4)
                            );
                            this.angleTable4.setItems(anglesData4);
                            scenarioOfManipulatorStates();
                            updateNamePoint();
                            if (pointsData.size() > 5 && pointsData.get(5) != null) {
                                this.anglesData5 = FXCollections.observableArrayList(
                                        this.deserializeAngles().get(5)
                                );
                                this.angleTable5.setItems(anglesData5);
                                scenarioOfManipulatorStates();
                                updateNamePoint();
                                if (pointsData.size() > 6 && pointsData.get(6) != null) {
                                    this.anglesData6 = FXCollections.observableArrayList(
                                            this.deserializeAngles().get(6)
                                    );
                                    this.angleTable6.setItems(anglesData6);
                                    scenarioOfManipulatorStates();
                                    updateNamePoint();
                                    if (pointsData.size() > 7 && pointsData.get(7) != null) {
                                        this.anglesData7 = FXCollections.observableArrayList(
                                                this.deserializeAngles().get(7)
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


