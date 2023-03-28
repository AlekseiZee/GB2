package ru.geobuilder_2;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


import java.util.ArrayList;


public class Controller_Calculation {

    @FXML
    private TextFlow mesField;

    private ArrayList<String> listRs;

    private ArrayList<Double> lr = adapterForListRibs(this.listRs);

    private ArrayList<Double> adapterForListRibs(ArrayList<String> lRibs){
        ArrayList<Double> listRibs = new ArrayList<>();

        for (String val : lRibs){
            listRibs.add(Double.valueOf(val));
        }
        return listRibs;
    }

    public void setListRs(ArrayList<String> listRs) {
        this.listRs = listRs;
    }

    private ArrayList<String> inputData;
    private Stage stage;

//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }

    public ArrayList<String> getInputData() {
        return inputData;
    }

    public void setInputData(ArrayList<String> inputData) {
        this.inputData = inputData;
    }

    @FXML
    private void pr(){
        for (String in : this.inputData) {
            Text text = new Text(in + "\n");
            mesField.getChildren().add(text);
        }
        for (Double val : this.lr){
            Text text = new Text(val + "\n");
            mesField.getChildren().add(text);
        }
    }
}
