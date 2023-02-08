package ru.geobuilder_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import static ru.geobuilder_2.Angle.setCountAngle;
//import static ru.geobuilder_2.Point.setCountPoint;

public class Controller_manualInput {

    @FXML
    private TableView<Point> pointTable;

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
    private TableView<Angle> angleTable;

    @FXML
    private TableColumn<Angle, Integer> idAngleCol;

    @FXML
    private TableColumn<Angle, String> vAngleCol;

    @FXML
    private TableColumn<Angle, String> hAngleCol;

    private ObservableList<Point> pointsData = FXCollections.observableArrayList();

    private ObservableList<Angle> anglesData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

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

        // Таблица Angles
        // Инициализация таблицы адресатов с двумя столбцами.
        idAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, Integer>("idAngle"));
        vAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, String>("vAngle"));
        hAngleCol.setCellValueFactory(new PropertyValueFactory<Angle, String>("hAngle"));

        angleTable.setItems(anglesData);

        angleTable.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        vAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());

        // Следим за изменение в ячейке namePCol. Если изменилось значение то запускается метод getNameP() с
        // с новым значением.
        pointTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> getNameP(newValue));

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

    int val = 0;
    // Добавление стоянки (Point)
    @FXML
    private void addLinePoint(){
        val++;
        Point point = new Point(val, "", "", "", "");
        pointsData.add(point);
    }

    // Удаление стоянки (Point)
    @FXML
    private void removeLinePoint() {
        val--;
        pointTable.getItems().remove(pointsData.size() - 1);

//        setCountPoint();
    }

    // Добавление углов (Angles)
    @FXML
    private void addLineAngles() {
        Angle angle = new Angle("", "");
        anglesData.add(angle);
    }

    // Удаление углов (Angles)
    @FXML
    private void removeLineAngls() {
        angleTable.getItems().remove(anglesData.size() - 1);

        setCountAngle();
    }

    // Добавление новой стоянки
    @FXML
    public void addColumnPoint() {
        TableColumn nameAnglePCol = new TableColumn(nameP);
        TableColumn vAngleCol = new TableColumn("Верт. угол");
        TableColumn hAngleCol = new TableColumn("Гор. угол");
        nameAnglePCol.getColumns().setAll(vAngleCol, hAngleCol);
        angleTable.getColumns().addAll(nameAnglePCol);


        angleTable.setEditable(true);
        // Разрешение на ввод данных в ячейку
        vAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());
        hAngleCol.setCellFactory(TextFieldTableCell.<Angle>forTableColumn());


    }

    // Удаление стоянки
    @FXML
    private void removeColumnPoint() {
        angleTable.getColumns().remove(2);
    }

    private String nameP = "";

    public String getNameP(Point point) {
        nameP = point.getNamePoint();
        return nameP;
    }

    // Нужно для инициализации измененных значений в ячейках. Без него данные не воспринимаются
    public void onEditChangerPoint(TableColumn.CellEditEvent<Point, String> pointStringCellEditEvent) {
        Point point = pointTable.getSelectionModel().getSelectedItem();
        point.setNamePoint(pointStringCellEditEvent.getNewValue());
        point.setDistancePoint(pointStringCellEditEvent.getNewValue());
        point.setVAnglePoint(pointStringCellEditEvent.getNewValue());
        point.setHAnglePoint(pointStringCellEditEvent.getNewValue());
    }

    public void onEditChangerAngle(TableColumn.CellEditEvent<Angle, String> angleStringCellEditEvent) {
        Angle angle = angleTable.getSelectionModel().getSelectedItem();
        angle.setVAngle(angleStringCellEditEvent.getNewValue());
        angle.setHAngle(angleStringCellEditEvent.getNewValue());
    }
}
