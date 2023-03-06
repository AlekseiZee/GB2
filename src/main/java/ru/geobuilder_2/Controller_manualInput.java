package ru.geobuilder_2;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.util.ArrayList;

public class Controller_manualInput {

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
    private TableView<Point> pointTable;

    @FXML
    private TableColumn nameAnglePCol, nameAnglePCol1, nameAnglePCol2, nameAnglePCol3, nameAnglePCol4,
            nameAnglePCol5, nameAnglePCol6, nameAnglePCol7;

    @FXML
    private TableColumn<Point, String> idPCol;

    @FXML
    private TableColumn<Point, String> namePCol;

    @FXML
    private TableColumn<Point, String> distancePCol;

    @FXML
    private TableColumn<Point, String> vAnglePCol;

    @FXML
    private TableColumn<Point, String> hAnglePCol;

    @FXML
    private TableView<Angle> angleTable, angleTable1, angleTable2, angleTable3, angleTable4, angleTable5, angleTable6, angleTable7;

    @FXML
    private TableColumn<Angle, Integer> idAngleCol, idAngleCol1, idAngleCol2, idAngleCol3, idAngleCol4, idAngleCol5, idAngleCol6, idAngleCol7;

    @FXML
    private TableColumn<Angle, String> vAngleCol, hAngleCol, vAngleCol1, hAngleCol1, vAngleCol2, hAngleCol2,
            vAngleCol3, hAngleCol3, vAngleCol4, hAngleCol4, vAngleCol5, hAngleCol5, vAngleCol6, hAngleCol6, vAngleCol7, hAngleCol7;

    ArrayList<ObservableList<Angle>> obsListsAngles = new ArrayList<>();

    private ObservableList<Point> pointsData = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData1 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData2 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData3 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData4 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData5 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData6 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData7 = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        //loadPointAngle();

//        Point point = new Point(this.pointTable.getItems().size() + 1, "A", "",
//                "", "");
//        pointsData.add(point);

        // Таблица Points
        idPCol.setCellValueFactory(new PropertyValueFactory<Point, String>("idPoint"));
        namePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("namePoint"));
        distancePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("distancePoint"));
        vAnglePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("vAnglePoint"));
        hAnglePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("hAnglePoint"));

        pointTable.setItems(this.pointsData);

        pointTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        namePCol.setCellFactory(TextFieldTableCell.forTableColumn());
        distancePCol.setCellFactory(TextFieldTableCell.forTableColumn());
        vAnglePCol.setCellFactory(TextFieldTableCell.forTableColumn());
        hAnglePCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // Таблица Angles0
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        obsListsAngles.add((ObservableList<Angle>) anglesData);
        obsListsAngles.add((ObservableList<Angle>) anglesData1);
        obsListsAngles.add((ObservableList<Angle>) anglesData2);
        obsListsAngles.add((ObservableList<Angle>) anglesData3);
        obsListsAngles.add((ObservableList<Angle>) anglesData4);
        obsListsAngles.add((ObservableList<Angle>) anglesData5);
        obsListsAngles.add((ObservableList<Angle>) anglesData6);
        obsListsAngles.add((ObservableList<Angle>) anglesData7);


        angleTable.setItems((ObservableList<Angle>) obsListsAngles.get(0));
        angleTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles1
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol1.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol1.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol1.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable1.setItems((ObservableList<Angle>) obsListsAngles.get(1));
        angleTable1.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol1.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol1.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles2
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol2.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol2.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol2.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable2.setItems((ObservableList<Angle>) obsListsAngles.get(2));
        angleTable2.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol2.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol2.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles3
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol3.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol3.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol3.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable3.setItems((ObservableList<Angle>) obsListsAngles.get(3));
        angleTable3.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol3.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol3.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles4
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol4.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol4.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol4.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable4.setItems((ObservableList<Angle>) obsListsAngles.get(4));
        angleTable4.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol4.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol4.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles5
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol5.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol5.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol5.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable5.setItems((ObservableList<Angle>) obsListsAngles.get(5));
        angleTable5.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol5.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol5.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles6
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol6.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol6.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol6.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable6.setItems((ObservableList<Angle>) obsListsAngles.get(6));
        angleTable6.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol6.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol6.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles7
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol7.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol7.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol7.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable7.setItems((ObservableList<Angle>) obsListsAngles.get(7));
        angleTable7.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol7.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol7.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Добавляем слушателя для автоматического отслеживания изменений в листе
        pointsData.addListener((ListChangeListener<Point>) c -> updateNamePoint());


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

    private void updateNamePoint(){
        if(!angleTable.isDisabled()) {
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
    private void checkingInputData(Point point) {
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
            Point point = new Point(this.pointTable.getItems().size() + 1, "",
                    "", "", "");
            pointsData.add(point);

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
    private void addLineAng(ObservableList anglesDataList, String namePointFromTable) {

        updateNamePoint();

        Angle angle = new Angle(anglesDataList.size() + 1, namePointFromTable, "", "");
        anglesDataList.add(angle);
//        obsListsAngles.add((ArrayList<Angle>) anglesDataList);
    }

    // Удаление углов (Angles)
    private void removeLineAng(ObservableList anglesDataList, TableView angleTable) {
        angleTable.getItems().remove(anglesDataList.size() - 1);
    }

    @FXML
    private void addLineAngles(){
        addLineAng(anglesData, pointsData.get(0).getNamePoint());
    }

    @FXML
    private void removeLineAngles() {
        removeLineAng(anglesData, angleTable);
    }

    @FXML
    private void addLineAngles1(){
        addLineAng(anglesData1, pointsData.get(1).getNamePoint());
    }

    @FXML
    private void removeLineAngles1() {
        removeLineAng(anglesData1, angleTable1);
    }

    @FXML
    private void addLineAngles2(){
        addLineAng(anglesData2, pointsData.get(2).getNamePoint());
    }

    @FXML
    private void removeLineAngles2() {
        removeLineAng(anglesData2, angleTable2);
    }

    @FXML
    private void addLineAngles3(){
        addLineAng(anglesData3, pointsData.get(3).getNamePoint());
    }

    @FXML
    private void removeLineAngles3() {
        removeLineAng(anglesData3, angleTable3);
    }

    @FXML
    private void addLineAngles4(){
        addLineAng(anglesData4, pointsData.get(4).getNamePoint());
    }

    @FXML
    private void removeLineAngles4() {
        removeLineAng(anglesData4, angleTable4);
    }

    @FXML
    private void addLineAngles5(){
        addLineAng(anglesData5, pointsData.get(5).getNamePoint());
    }

    @FXML
    private void removeLineAngles5() {
        removeLineAng(anglesData5, angleTable5);
    }

    @FXML
    private void addLineAngles6(){
        addLineAng(anglesData6, pointsData.get(6).getNamePoint());
    }

    @FXML
    private void removeLineAngles6() {
        removeLineAng(anglesData6, angleTable6);
    }

    @FXML
    private void addLineAngles7(){
        addLineAng(anglesData7, pointsData.get(7).getNamePoint());
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
    public void onEditChangerNamePoint(TableColumn.CellEditEvent<Point, String> pointStringCellEditEvent) {
        Point point = pointTable.getSelectionModel().getSelectedItem();
        point.setNamePoint(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerDistancePoint(TableColumn.CellEditEvent<Point, String> pointStringCellEditEvent) {
        Point point = pointTable.getSelectionModel().getSelectedItem();
        point.setDistancePoint(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngPoint(TableColumn.CellEditEvent<Point, String> pointStringCellEditEvent) {
        Point point = pointTable.getSelectionModel().getSelectedItem();
        point.setVAnglePoint(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngPoint(TableColumn.CellEditEvent<Point, String> pointStringCellEditEvent) {
        Point point = pointTable.getSelectionModel().getSelectedItem();
        point.setHAnglePoint(pointStringCellEditEvent.getNewValue());
    }


    public void onEditChangerVAngle(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle = angleTable.getSelectionModel().getSelectedItem();
        angle.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle = angleTable.getSelectionModel().getSelectedItem();
        angle.setHAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle1(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle1 = angleTable1.getSelectionModel().getSelectedItem();
        angle1.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle1(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle1 = angleTable1.getSelectionModel().getSelectedItem();
        angle1.setHAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle2(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle2 = angleTable2.getSelectionModel().getSelectedItem();
        angle2.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle2(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle2 = angleTable2.getSelectionModel().getSelectedItem();
        angle2.setHAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle3(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle3 = angleTable3.getSelectionModel().getSelectedItem();
        angle3.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle3(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle3 = angleTable3.getSelectionModel().getSelectedItem();
        angle3.setHAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle4(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle4 = angleTable4.getSelectionModel().getSelectedItem();
        angle4.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle4(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle4 = angleTable4.getSelectionModel().getSelectedItem();
        angle4.setHAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle5(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle5 = angleTable5.getSelectionModel().getSelectedItem();
        angle5.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle5(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle5 = angleTable5.getSelectionModel().getSelectedItem();
        angle5.setHAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle6(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle6 = angleTable6.getSelectionModel().getSelectedItem();
        angle6.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle6(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle6 = angleTable6.getSelectionModel().getSelectedItem();
        angle6.setHAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerVAngle7(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle7 = angleTable7.getSelectionModel().getSelectedItem();
        angle7.setVAngle(angleStringCellEditEvent.getNewValue());
    }

    public void onEditChangerHAngle7(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle7 = angleTable7.getSelectionModel().getSelectedItem();
        angle7.setHAngle(angleStringCellEditEvent.getNewValue());
    }


    /**
     * Возвращаемся в окно "Новый расчет"
     *
     * @param event
     */
    @FXML
    public void goingBackToNewCal(ActionEvent event) {

        savePoint();

        goBackButton.getScene().getWindow().hide();
//        goBackButton.getScene().getWindow().hide();
//        Controller_Main controller_main = new Controller_Main();
//        controller_main.openNewCalculationWindow("new_calculation-view.fxml", 768, 700);
    }

    private void serializePoint(ObservableList<Point> pointsData) throws FileNotFoundException, IOException {
        File fileFS = new File("D:\\TestGB\\cache\\point.txt");
        try(FileOutputStream fosManInpWin = new FileOutputStream(fileFS); ObjectOutputStream oosPoint = new ObjectOutputStream(fosManInpWin)) {
            ArrayList<Point> pointsManInpWin = new ArrayList<Point>(pointsData);
            oosPoint.writeObject(pointsManInpWin);
            //oosPoint.writeObject(new ArrayList<Point> (pointsManInpWin));
        }
    }

    private ObservableList<Point> deserializePoint() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Point> pointsData = new ArrayList<Point>();
        File fileFS = new File("D:\\TestGB\\cache\\point.txt");
        try(FileInputStream fisPoint = new FileInputStream(fileFS); ObjectInputStream oisPoint = new ObjectInputStream(fisPoint)) {
            pointsData = (ArrayList<Point>) oisPoint.readObject();
        }
        return FXCollections.observableArrayList(pointsData);
    }

//    private void serializeAngle(ObservableList<Angle> anglesData) throws FileNotFoundException, IOException {
//        File fileAng = new File("D:\\TestGB\\cache\\angle.txt");
//        try(FileOutputStream fosManInpWinAng = new FileOutputStream(fileAng);
//            ObjectOutputStream oosAngle = new ObjectOutputStream(fosManInpWinAng)) {
//            ArrayList<Angle> anglesManInpWin = new ArrayList<Angle>(anglesData);
//            oosAngle.writeObject(anglesManInpWin);
//        }
//    }

    private void serializeAngles(ArrayList<ObservableList<Angle>> listsAngles) throws FileNotFoundException, IOException {
        File fileAng = new File("D:\\TestGB\\cache\\angles.txt");
        try(FileOutputStream fosManInpWinAng = new FileOutputStream(fileAng);
            ObjectOutputStream oosAngle = new ObjectOutputStream(fosManInpWinAng)) {
            ArrayList<Angle> anglesManInpWin = new ArrayList<Angle>(this.anglesData);
            ArrayList<Angle> anglesManInpWin1 = new ArrayList<Angle>(this.anglesData1);
            ArrayList<Angle> anglesManInpWin2 = new ArrayList<Angle>(this.anglesData2);
            ArrayList<Angle> anglesManInpWin3 = new ArrayList<Angle>(this.anglesData3);
            ArrayList<Angle> anglesManInpWin4 = new ArrayList<Angle>(this.anglesData4);
            ArrayList<Angle> anglesManInpWin5 = new ArrayList<Angle>(this.anglesData5);
            ArrayList<Angle> anglesManInpWin6 = new ArrayList<Angle>(this.anglesData6);
            ArrayList<Angle> anglesManInpWin7 = new ArrayList<Angle>(this.anglesData7);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin1);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin2);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin3);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin4);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin5);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin6);
            listsAngles.add((ObservableList<Angle>) anglesManInpWin7);
            oosAngle.writeObject(listsAngles);
        }
    }

    private ArrayList<ObservableList<Angle>> deserializeAngles() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ObservableList<Angle>> anglesDataS = new ArrayList<>();
        File fileAng = new File("D:\\TestGB\\cache\\angles.txt");
        try (FileInputStream fisAngles = new FileInputStream(fileAng);
             ObjectInputStream oisAngles = new ObjectInputStream(fisAngles)) {
            anglesDataS = (ArrayList<ObservableList<Angle>>) oisAngles.readObject();
//            if (pointsData.get(0) != null) {
//                this.anglesData = FXCollections.observableArrayList(anglesDataS.get(0));
//                if (pointsData.get(1) != null) {
//                    this.anglesData1 = FXCollections.observableArrayList(anglesDataS.get(1));
//                    if (pointsData.get(2) != null) {
//                        this.anglesData2 = FXCollections.observableArrayList(anglesDataS.get(2));
//                        if (pointsData.get(3) != null) {
//                            this.anglesData3 = FXCollections.observableArrayList(anglesDataS.get(3));
//                            if (pointsData.get(4) != null) {
//                                this.anglesData4 = FXCollections.observableArrayList(anglesDataS.get(4));
//                                if (pointsData.get(5) != null) {
//                                    this.anglesData5 = FXCollections.observableArrayList(anglesDataS.get(5));
//                                    if (pointsData.get(6) != null) {
//                                        this.anglesData6 = FXCollections.observableArrayList(anglesDataS.get(6));
//                                        if (pointsData.get(7) != null) {
//                                            this.anglesData7 = FXCollections.observableArrayList(anglesDataS.get(7));
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
            return anglesDataS;
        }
    }
        //return FXCollections.observableArrayList(anglesData);

//    private ObservableList<Angle> deserializeAngle() throws FileNotFoundException, IOException, ClassNotFoundException{
//        ArrayList<Angle> anglesData = new ArrayList<Angle>();
//        File fileAng = new File("D:\\TestGB\\cache\\angle.txt");
//        try(FileInputStream fisAngle = new FileInputStream(fileAng);
//            ObjectInputStream oisAngle = new ObjectInputStream(fisAngle)) {
//            anglesData = (ArrayList<Angle>) oisAngle.readObject();
//        }
//        return FXCollections.observableArrayList(anglesData);
//    }

    @FXML
    void savePoint(){
        try {
            this.serializePoint(this.pointsData);
            this.serializeAngles(this.obsListsAngles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    void savePoint(){
//        try {
//            this.serializePoint(this.pointsData);
//            this.serializeAngle(this.anglesData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private void loadPointAngle(){
        try {
            this.pointsData = this.deserializePoint();
            this.pointTable.setItems(this.pointsData);
            this.obsListsAngles = deserializeAngles();
            this.angleTable.setItems(this.anglesData);
            scenarioOfManipulatorStates();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}