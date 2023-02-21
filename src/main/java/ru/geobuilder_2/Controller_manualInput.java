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

public class Controller_manualInput {

    @FXML
    private Button adderPoint1, adderPoint2, adderPoint3, adderPoint4, adderPoint5,
            removerPoint1, removerPoint2, removerPoint3, removerPoint4, removerPoint5;

    @FXML
    private TextFlow massage;

    @FXML
    private Button goBackButton;

    @FXML
    private TableView<Point> pointTable;

    @FXML
    private TableColumn nameAnglePCol1;

    @FXML
    private TableColumn nameAnglePCol2;

    @FXML
    private TableColumn nameAnglePCol3;

    @FXML
    private TableColumn nameAnglePCol4;

    @FXML
    private TableColumn nameAnglePCol5;


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
    private TableView<Angle> angleTable, angleTable1, angleTable2, angleTable3, angleTable4, angleTable5;

    @FXML
    private TableColumn<Angle, Integer> idAngleCol, idAngleCol1, idAngleCol2, idAngleCol3, idAngleCol4, idAngleCol5;

    @FXML
    private TableColumn<Angle, String> vAngleCol, vAngleCol1, hAngleCol1, vAngleCol2, hAngleCol2,
            vAngleCol3, hAngleCol3, vAngleCol4, hAngleCol4, vAngleCol5, hAngleCol5;

    @FXML
    private TableColumn<Angle, String> hAngleCol;

    private ObservableList<Point> pointsData = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData1 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData2 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData3 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData4 = FXCollections.observableArrayList();
    private ObservableList<Angle> anglesData5 = FXCollections.observableArrayList();
    
    

    @FXML
    private void initialize() {

        Point point = new Point(this.pointTable.getItems().size() + 1, "A", "",
                "", "");
        pointsData.add(point);

        // Таблица Points
        idPCol.setCellValueFactory(new PropertyValueFactory<Point, String>("idPoint"));
        namePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("namePoint"));
        distancePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("distancePoint"));
        vAnglePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("vAnglePoint"));
        vAnglePCol.setCellValueFactory(new PropertyValueFactory<Point, String>("hAnglePoint"));

        pointTable.setItems(pointsData);

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

        angleTable.setItems(anglesData);
        angleTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles1
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol1.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol1.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol1.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable1.setItems(anglesData1);
        angleTable1.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol1.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol1.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles2
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol2.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol2.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol2.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable2.setItems(anglesData2);
        angleTable2.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol2.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol2.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles3
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol3.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol3.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol3.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable3.setItems(anglesData3);
        angleTable3.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol3.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol3.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles4
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol4.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol4.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol4.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable4.setItems(anglesData4);
        angleTable4.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol4.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol4.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Таблица Angles5
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol5.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol5.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol5.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable5.setItems(anglesData5);
        angleTable5.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol5.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol5.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

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
        if(!angleTable1.isDisabled()) {
            nameAnglePCol1.setText(pointsData.get(1).getNamePoint());
        }
        if(!angleTable2.isDisabled()){
            nameAnglePCol2.setText(pointsData.get(2).getNamePoint());
        }
        if(!angleTable3.isDisabled()) {
            nameAnglePCol3.setText(pointsData.get(3).getNamePoint());
        }
        if(!angleTable4.isDisabled()) {
            nameAnglePCol4.setText(pointsData.get(4).getNamePoint());
        }
        if(!angleTable5.isDisabled()) {
            nameAnglePCol5.setText(pointsData.get(5).getNamePoint());
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
        if (this.pointTable.getItems().size() < 6) {
            Point point = new Point(this.pointTable.getItems().size() + 1, "",
                    "", "", "");
            pointsData.add(point);

            switch (this.pointTable.getItems().size() - 1) {
                case 1 -> {
                    angleTable1.setDisable(false);
                    adderPoint1.setDisable(false);
                    removerPoint1.setDisable(false);
                }
                case 2 -> {
                    angleTable1.setDisable(false);
                    angleTable2.setDisable(false);
                    adderPoint1.setDisable(false);
                    adderPoint2.setDisable(false);
                    removerPoint1.setDisable(false);
                    removerPoint2.setDisable(false);
                    break;
                }
                case 3 -> {
                    angleTable1.setDisable(false);
                    angleTable2.setDisable(false);
                    angleTable3.setDisable(false);
                    adderPoint1.setDisable(false);
                    adderPoint2.setDisable(false);
                    adderPoint3.setDisable(false);
                    removerPoint1.setDisable(false);
                    removerPoint2.setDisable(false);
                    removerPoint3.setDisable(false);
                    break;
                }
                case 4 -> {
                    angleTable1.setDisable(false);
                    angleTable2.setDisable(false);
                    angleTable3.setDisable(false);
                    angleTable4.setDisable(false);
                    adderPoint1.setDisable(false);
                    adderPoint2.setDisable(false);
                    adderPoint3.setDisable(false);
                    adderPoint4.setDisable(false);
                    removerPoint1.setDisable(false);
                    removerPoint2.setDisable(false);
                    removerPoint3.setDisable(false);
                    removerPoint4.setDisable(false);
                    break;
                }
                case 5 -> {
                    angleTable1.setDisable(false);
                    angleTable2.setDisable(false);
                    angleTable3.setDisable(false);
                    angleTable4.setDisable(false);
                    angleTable5.setDisable(false);
                    adderPoint1.setDisable(false);
                    adderPoint2.setDisable(false);
                    adderPoint3.setDisable(false);
                    adderPoint4.setDisable(false);
                    adderPoint5.setDisable(false);
                    removerPoint1.setDisable(false);
                    removerPoint2.setDisable(false);
                    removerPoint3.setDisable(false);
                    removerPoint4.setDisable(false);
                    removerPoint5.setDisable(false);
                    break;
                }
            }
        }
    }

    // Удаление стоянки (Point)
    @FXML
    private void removeLinePoint() {

        if (pointsData.size() > 1) {
            switch (this.pointTable.getItems().size() - 1) {
                case 1 -> {
                    angleTable1.setDisable(true);
                    angleTable2.setDisable(true);
                    angleTable3.setDisable(true);
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    adderPoint1.setDisable(true);
                    adderPoint2.setDisable(true);
                    adderPoint3.setDisable(true);
                    adderPoint4.setDisable(true);
                    adderPoint5.setDisable(true);
                    removerPoint1.setDisable(true);
                    removerPoint2.setDisable(true);
                    removerPoint3.setDisable(true);
                    removerPoint4.setDisable(true);
                    removerPoint5.setDisable(true);
                    break;
                }
                case 2 -> {
                    angleTable2.setDisable(true);
                    angleTable3.setDisable(true);
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    adderPoint2.setDisable(true);
                    adderPoint3.setDisable(true);
                    adderPoint4.setDisable(true);
                    adderPoint5.setDisable(true);
                    removerPoint2.setDisable(true);
                    removerPoint3.setDisable(true);
                    removerPoint4.setDisable(true);
                    removerPoint5.setDisable(true);
                    break;
                }
                case 3 -> {
                    angleTable3.setDisable(true);
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    adderPoint3.setDisable(true);
                    adderPoint4.setDisable(true);
                    adderPoint5.setDisable(true);
                    removerPoint3.setDisable(true);
                    removerPoint4.setDisable(true);
                    removerPoint5.setDisable(true);
                    break;
                }
                case 4 -> {
                    angleTable4.setDisable(true);
                    angleTable5.setDisable(true);
                    adderPoint4.setDisable(true);
                    adderPoint5.setDisable(true);
                    removerPoint4.setDisable(true);
                    removerPoint5.setDisable(true);
                    break;
                }
                case 5 -> {
                    angleTable5.setDisable(true);
                    adderPoint5.setDisable(true);
                    removerPoint5.setDisable(true);
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

    /**
     * Возвращаемся в окно "Новый расчет"
     *
     * @param event
     */
    @FXML
    public void goingBackToNewCal(ActionEvent event) {

        goBackButton.getScene().getWindow().hide();
        Controller_Main controller_main = new Controller_Main();
        controller_main.openNewCalculationWindow("new_calculation-view.fxml", 768, 700);
    }
}
