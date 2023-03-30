package ru.geobuilder_2;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


import java.util.ArrayList;


public class Controller_Calculation {

    /**
     * Переменная, которая указывает, какой вид входных данных
     * Либо из файла, включая индексы 100.., 200.., 300..
     * Либо из введенных вручную, без дополнительных индексов 100, 200, 300.
     */
    Boolean fromFailData = true;
    private ArrayList<String> inputData;

    private ArrayList<Double> listRibs;

    public void setListRibs(ArrayList<Double> listRibs){
        this.listRibs = listRibs;
    }

    public void setFromFailData(Boolean fromFailData) {
        this.fromFailData = fromFailData;
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
    private void pr(){
        mesField.getChildren().clear();
        for (String in : this.inputData) {
            Text text = new Text(in + "\n");
            mesField.getChildren().add(text);
        }
        for (Double val : listRibs){
            Text text = new Text(val + "\n");
            mesField.getChildren().add(text);
        }
        Text text = new Text( fromFailData + "\n");
        mesField.getChildren().add(text);
    }
}
