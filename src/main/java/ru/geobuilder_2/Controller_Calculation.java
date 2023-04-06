package ru.geobuilder_2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import ru.geobuilder_2.model.DataPreparer;
import ru.geobuilder_2.model.ShootingOnTheBelts;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Controller_Calculation {

    @FXML
    private ListView<Double> listHeights, listVerticalityOnThe_X_Axis, listverticalityOnThe_Y_Axis,
            listCalculatedOffset, listVerticalityBorder, listAngleDisplacementVector, listHeightsTwo,
            listVerticalityOnThe_X_AxisTwo, listverticalityOnThe_Y_AxisTwo, listCalculatedOffsetTwo,
            listVerticalityBorderTwo, listHeightsThree, listBeltA, listBeltB, listBeltC, listBeltD, listStraightnessBorder;

    @FXML
    private LineChart<Number, Number> chartX, chartY,
            chartVerticalDeviation, chartDeviationInPlan,
            chartBeltA, chartBeltB, chartBeltC, chartBeltD;

    private int border;

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    private LinkedHashMap<String, Integer> mapQuantityPointsForEachDirection = new LinkedHashMap<>();

    public void setMapQuantityPointsForEachDirection(LinkedHashMap<String, Integer> mapQuantityPointsForEachDirection) {
        this.mapQuantityPointsForEachDirection = mapQuantityPointsForEachDirection;
    }

    /**
     * Количество стоянок (2, 3 или 4)
     */
    private int quantityOfPoints;

    /**
     * Переменная, которая указывает, какой вид входных данных
     * Либо из файла, включая индексы 100.., 200.., 300..
     * Либо из введенных вручную, без дополнительных индексов 100, 200, 300.
     */
    Boolean fromFailData = true;
    private ArrayList<String> inputData;

    private ArrayList<Double> listRibs;

    public void setListRibs(ArrayList<Double> listRibs) {
        this.listRibs = listRibs;
    }

    public void setFromFailData(Boolean fromFailData) {
        this.fromFailData = fromFailData;
    }

    public int getQuantityOfPoints() {
        return quantityOfPoints;
    }

    public void setQuantityOfPoints(int quantityOfPoints) {
        this.quantityOfPoints = quantityOfPoints;
    }

    @FXML
    private TextFlow mesField;

//    public void setListRibs(ArrayList<String> lRibs){
//        ArrayList<Double> lr = new ArrayList<>();
//
//        for (String val : lRibs){
//            lr.add(Double.valueOf(val));
//        }
//        listRibs = lr;
//    }

    private Stage stage;

    public ArrayList<String> getInputData() {
        return inputData;
    }

    public void setInputData(ArrayList<String> inputData) {
        this.inputData = inputData;
    }

    @FXML
    private void pr() throws DataPreparer.DateIsNotReadyException, IOException {
        mesField.getChildren().clear();
        for (String in : this.inputData) {
            Text text = new Text(in + "\n");
            mesField.getChildren().add(text);
        }
        for (Double val : listRibs) {
            Text text = new Text(val + "\n");
            mesField.getChildren().add(text);
        }
        Text text = new Text(fromFailData + "\n");
        mesField.getChildren().add(text);

        geoBuild();
    }

    /**
     * Считаем вертикальность и прямолинейность
     *
     * @throws IOException
     */
    @FXML
    private void geoBuild() throws IOException, DataPreparer.DateIsNotReadyException {

        DataPreparer dataPreparer;
        if (getQuantityOfPoints() > 2) {
            Text text = new Text("Рёбра АМС: " + listRibs + "\n" +
                    "Количества стоянок в направлениях: " + mapQuantityPointsForEachDirection + "\n" +
                    "Общее количество стоянок: " + quantityOfPoints + "\n");
            mesField.getChildren().add(text);

            dataPreparer = new DataPreparer(inputData, fromFailData, quantityOfPoints,
                    mapQuantityPointsForEachDirection, listRibs);
        } else {
            Text text = new Text(
                    "Количества стоянок в направлениях: " + mapQuantityPointsForEachDirection + "\n" +
                            "Общее количество стоянок: " + quantityOfPoints + "\n");
            mesField.getChildren().add(text);
            dataPreparer = new DataPreparer(inputData, fromFailData, quantityOfPoints, mapQuantityPointsForEachDirection);
        }
        ShootingOnTheBelts shootingOnBelt = new ShootingOnTheBelts(dataPreparer);

        /**
         * Средние высоты
         */
        shootingOnBelt.buildAverageHeights();
        List<Double> heights = shootingOnBelt.getAverageHeights();

        /**
         * Вертикальность
         */
        List<Double> verticalityX = shootingOnBelt.getVerticality("X");
        List<Double> verticalityY = shootingOnBelt.getVerticality("Y");

        /**
         * смещение центра
         */
        List<Double> calculatedOffset = shootingOnBelt.getCenterOffset();

        /**
         * угол вектора смещения
         */
        List<Double> angleDisplacementVector = shootingOnBelt.getAngleDisplacementVector();

        /**
         * Допуск вертикальности
         */
        Double[][] lineVerticalityBorder = shootingOnBelt.getLineVerticalityBorder(getBorder());
        List<Double> verticalityBorder = new ArrayList<>();
        for (
                int i = 0; i < heights.size(); i++) {
            verticalityBorder.add(lineVerticalityBorder[0][i]);
        }

        /**
         * Допуск прямолинейности
         */
        Double[][] lineStraightnessBorder = shootingOnBelt.getLineStraightnessBorder();
        List<Double> straightnessBorder = new ArrayList<>();
        for (
                int i = 0; i < heights.size(); i++) {
            straightnessBorder.add(lineStraightnessBorder[0][i]);
        }

        /**
         * Данные прямолинейности по поясам
         */
        List<Double> straightnessA = shootingOnBelt.getStraightness("A");
        List<Double> straightnessB = shootingOnBelt.getStraightness("B");
        List<Double> straightnessC = shootingOnBelt.getStraightness("C");

        XYChart.Series<Number, Number> seriesVerticalityOnXAxis = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesVerticalityOnYAxis = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesVerticalityBorderRightOnXAxis = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesVerticalityBorderLeftOnXAxis = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesVerticalityBorderRightOnYAxis = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesVerticalityBorderLeftOnYAxis = new XYChart.Series<>();

        XYChart.Series<Number, Number> seriesСalculatedOffset = new XYChart.Series<>();
        XYChart.Series<Number, Number> serieslineVerticalityBorder = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesDeviationInPlan = new XYChart.Series<>();

        XYChart.Series<Number, Number> seriesBeltA = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesBeltB = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesBeltC = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesBeltD = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderRightA = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderLeftA = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderRightB = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderLeftB = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderRightC = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderLeftC = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderRightD = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesStraightnessBorderLeftD = new XYChart.Series<>();


        for (
                int i = 0; i < shootingOnBelt.getNumberOfMeasurements(); i++) {
            // График вертикальности по оси Х
            seriesVerticalityOnXAxis.getData().add(new XYChart.Data<>(verticalityX.get(i), heights.get(i)));
            // График веткальности по оси Y
            seriesVerticalityOnYAxis.getData().add(new XYChart.Data<>(verticalityY.get(i), heights.get(i)));
            // Правая линия допуска на "Ось Х"
            seriesVerticalityBorderRightOnXAxis.getData().add(new XYChart.Data<>(lineVerticalityBorder[0][i], heights.get(i)));
            // Левая линия допуска на "Ось Х"
            seriesVerticalityBorderLeftOnXAxis.getData().add(new XYChart.Data<>(lineVerticalityBorder[1][i], heights.get(i)));
            // Правая линия допуска на "Ось Y"
            seriesVerticalityBorderRightOnYAxis.getData().add(new XYChart.Data<>(lineVerticalityBorder[0][i], heights.get(i)));
            // Левая линия допуска на "Ось Y"
            seriesVerticalityBorderLeftOnYAxis.getData().add(new XYChart.Data<>(lineVerticalityBorder[1][i], heights.get(i)));
            // Отклонение вертикали
            seriesСalculatedOffset.getData().add(new XYChart.Data<>(calculatedOffset.get(i), heights.get(i)));
            // Линия допуукска
            serieslineVerticalityBorder.getData().add(new XYChart.Data<>(lineVerticalityBorder[0][i], heights.get(i)));
            // Отклонение в плане
            seriesDeviationInPlan.getData().add(new XYChart.Data<>(verticalityX.get(i), verticalityY.get(i)));
            // Прямолинейности поясов
            seriesBeltA.getData().add(new XYChart.Data<>(straightnessA.get(i), heights.get(i)));
            seriesBeltB.getData().add(new XYChart.Data<>(straightnessB.get(i), heights.get(i)));
            seriesBeltC.getData().add(new XYChart.Data<>(straightnessC.get(i), heights.get(i)));

            seriesStraightnessBorderRightA.getData().add(new XYChart.Data<>(lineStraightnessBorder[0][i],
                    heights.get(i))); // Допуск прямолинейности правая линия
            seriesStraightnessBorderLeftA.getData().add(new XYChart.Data<>(lineStraightnessBorder[1][i],
                    heights.get(i))); // Допуск прямолинейности левая линия

            seriesStraightnessBorderRightB.getData().add(new XYChart.Data<>(lineStraightnessBorder[0][i],
                    heights.get(i))); // Допуск прямолинейности правая линия
            seriesStraightnessBorderLeftB.getData().add(new XYChart.Data<>(lineStraightnessBorder[1][i],
                    heights.get(i))); // Допуск прямолинейности левая линия

            seriesStraightnessBorderRightC.getData().add(new XYChart.Data<>(lineStraightnessBorder[0][i],
                    heights.get(i))); // Допуск прямолинейности правая линия
            seriesStraightnessBorderLeftC.getData().add(new XYChart.Data<>(lineStraightnessBorder[1][i],
                    heights.get(i))); // Допуск прямолинейности левая линия

        }
        //Отчищаем старые значения с графиков
        chartX.getData().setAll();
        chartY.getData().setAll();
        chartVerticalDeviation.getData().setAll();
        chartDeviationInPlan.getData().setAll();
        chartBeltA.getData().setAll();
        chartBeltB.getData().setAll();
        chartBeltC.getData().setAll();
        chartBeltD.getData().setAll();

        // Передаем (новые) данные на наши графики
        chartX.getData().add(seriesVerticalityOnXAxis);
        chartX.getData().add(seriesVerticalityBorderRightOnXAxis);
        chartX.getData().add(seriesVerticalityBorderLeftOnXAxis);
        chartY.getData().add(seriesVerticalityOnYAxis);
        chartY.getData().add(seriesVerticalityBorderRightOnYAxis);
        chartY.getData().add(seriesVerticalityBorderLeftOnYAxis);

        chartVerticalDeviation.getData().add(seriesСalculatedOffset);
        chartVerticalDeviation.getData().add(serieslineVerticalityBorder);

        chartDeviationInPlan.getData().add(seriesDeviationInPlan);

        chartBeltA.getData().add(seriesBeltA);
        chartBeltA.getData().add(seriesStraightnessBorderRightA);
        chartBeltA.getData().add(seriesStraightnessBorderLeftA);
        chartBeltB.getData().add(seriesBeltB);
        chartBeltB.getData().add(seriesStraightnessBorderRightB);
        chartBeltB.getData().add(seriesStraightnessBorderLeftB);
        chartBeltC.getData().add(seriesBeltC);
        chartBeltC.getData().add(seriesStraightnessBorderRightC);
        chartBeltC.getData().add(seriesStraightnessBorderLeftC);

        // Задаем цвета линиям
        seriesVerticalityOnXAxis.getNode().setStyle("-fx-stroke: #03fc0b; ");
        seriesVerticalityOnYAxis.getNode().setStyle("-fx-stroke: #0730fa; ");
        seriesVerticalityBorderRightOnXAxis.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesVerticalityBorderLeftOnXAxis.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesVerticalityBorderRightOnYAxis.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesVerticalityBorderLeftOnYAxis.getNode().setStyle("-fx-stroke: #fc0320; ");

        seriesСalculatedOffset.getNode().setStyle("-fx-stroke: #03fc0b; ");
        serieslineVerticalityBorder.getNode().setStyle("-fx-stroke: #fc0320; ");

        seriesBeltA.getNode().setStyle("-fx-stroke: #03fc0b; ");
        seriesBeltB.getNode().setStyle("-fx-stroke: #03fc0b; ");
        seriesBeltC.getNode().setStyle("-fx-stroke: #03fc0b; ");
        seriesStraightnessBorderRightA.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesStraightnessBorderLeftA.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesStraightnessBorderRightB.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesStraightnessBorderLeftB.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesStraightnessBorderRightC.getNode().setStyle("-fx-stroke: #fc0320; ");
        seriesStraightnessBorderLeftC.getNode().setStyle("-fx-stroke: #fc0320; ");

        /**
         * Заполняем таблицу во вкладке "СХЕМА ВЕРТИКАЛЬНОСТИ"
         */
        ObservableList<Double> nameHeights = FXCollections.observableArrayList(heights);
        listHeights.setItems(nameHeights);
        listHeights.getItems();

        ObservableList<Double> nameVerticalityOnThe_X_Axis = FXCollections.observableArrayList(verticalityX);
        listVerticalityOnThe_X_Axis.setItems(nameVerticalityOnThe_X_Axis);
        listVerticalityOnThe_X_Axis.getItems();

        ObservableList<Double> nameVerticalityOnThe_Y_Axis = FXCollections.observableArrayList(verticalityY);
        listverticalityOnThe_Y_Axis.setItems(nameVerticalityOnThe_Y_Axis);
        listverticalityOnThe_Y_Axis.getItems();
        // Вычисленное
        ObservableList<Double> nameCalculatedOffset = FXCollections.observableArrayList(calculatedOffset);
        listCalculatedOffset.setItems(nameCalculatedOffset);
        listCalculatedOffset.getItems();
        // Допуск
        ObservableList<Double> nameVerticalityBorder = FXCollections.observableArrayList(verticalityBorder);
        listVerticalityBorder.setItems(nameVerticalityBorder);
        listVerticalityBorder.getItems();
        // Угол разворота
        ObservableList<Double> nameAngleDisplacementVector = FXCollections.observableArrayList(angleDisplacementVector);
        listAngleDisplacementVector.setItems(nameAngleDisplacementVector);
        listAngleDisplacementVector.getItems();

        /**
         * Заполняем таблицу во вкладке "СХЕМА ВЕРТ В ПЛАНЕ"
         */
        ObservableList<Double> nameHeightsTwo = FXCollections.observableArrayList(heights);
        listHeightsTwo.setItems(nameHeightsTwo);
        listHeightsTwo.getItems();

        ObservableList<Double> nameVerticalityOnThe_X_AxisTwo = FXCollections.observableArrayList(verticalityX);
        listVerticalityOnThe_X_AxisTwo.setItems(nameVerticalityOnThe_X_AxisTwo);
        listVerticalityOnThe_X_AxisTwo.getItems();

        ObservableList<Double> nameVerticalityOnThe_Y_AxisTwo = FXCollections.observableArrayList(verticalityY);
        listverticalityOnThe_Y_AxisTwo.setItems(nameVerticalityOnThe_Y_AxisTwo);
        listverticalityOnThe_Y_AxisTwo.getItems();

        ObservableList<Double> nameCalculatedOffsetTwo = FXCollections.observableArrayList(calculatedOffset);
        listCalculatedOffsetTwo.setItems(nameCalculatedOffsetTwo);
        listCalculatedOffsetTwo.getItems();

        ObservableList<Double> nameVerticalityBorderTwo = FXCollections.observableArrayList(verticalityBorder);
        listVerticalityBorderTwo.setItems(nameVerticalityBorderTwo);
        listVerticalityBorderTwo.getItems();

        /**
         * Заполняем таблицу на вкладке "Протокол прямолинейности"
         */
        ObservableList<Double> nameHieghtthree = FXCollections.observableArrayList(heights);
        listHeightsThree.setItems(nameHieghtthree);
        listHeightsThree.getItems();

        ObservableList<Double> nameBeltA = FXCollections.observableArrayList(straightnessA);
        listBeltA.setItems(nameBeltA);
        listBeltA.getItems();

        ObservableList<Double> nameBeltB = FXCollections.observableArrayList(straightnessB);
        listBeltB.setItems(nameBeltB);
        listBeltB.getItems();

        ObservableList<Double> nameBeltC = FXCollections.observableArrayList(straightnessC);
        listBeltC.setItems(nameBeltC);
        listBeltC.getItems();

        ObservableList<Double> nameStraightnessBorder = FXCollections.observableArrayList(straightnessBorder);
        listStraightnessBorder.setItems(nameStraightnessBorder);
        listStraightnessBorder.getItems();

        if (dataPreparer.getQuantityOfPoints() == 4) {
            List<Double> straightnessD = shootingOnBelt.getStraightness("D");
            for (int i = 0; i < shootingOnBelt.getNumberOfMeasurements(); i++) {
                seriesBeltD.getData().add(new XYChart.Data<>(straightnessD.get(i), heights.get(i)));
                seriesStraightnessBorderRightD.getData().add(new XYChart.Data<>(lineStraightnessBorder[0][i],
                        heights.get(i))); // Допуск прямолинейности правая линия
                seriesStraightnessBorderLeftD.getData().add(new XYChart.Data<>(lineStraightnessBorder[1][i],
                        heights.get(i))); // Допуск прямолинейности левая линия
            }
            chartBeltD.getData().add(seriesBeltD);
            chartBeltD.getData().add(seriesStraightnessBorderRightD);
            chartBeltD.getData().add(seriesStraightnessBorderLeftD);

            seriesBeltD.getNode().setStyle("-fx-stroke: #03fc0b; ");
            seriesStraightnessBorderRightD.getNode().setStyle("-fx-stroke: #fc0320; ");
            seriesStraightnessBorderLeftD.getNode().setStyle("-fx-stroke: #fc0320; ");

            ObservableList<Double> nameBeltD = FXCollections.observableArrayList(straightnessD);
            listBeltD.setItems(nameBeltD);
            listBeltD.getItems();
        }
    }
}

