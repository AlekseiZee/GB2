package ru.geobuilder_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller_DownloadFromBD {

    @FXML
    private Button goBackButton;

    @FXML
    private TextField newOperator;
    @FXML
    private SplitMenuButton operator;

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
    public void setMegafon(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(megafon.getText());
    }

    @FXML
    public void setMts(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(mts.getText());
    }

    @FXML
    public void setT2(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(t2.getText());
    }

    @FXML
    public void setVimpel(ActionEvent event){
        newOperator.setDisable(true);
        operator.setText(vimpel.getText());
    }

    @FXML
    public void setNon(ActionEvent event){
        newOperator.setDisable(false);
        operator.setText(non.getText());
    }

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
}
