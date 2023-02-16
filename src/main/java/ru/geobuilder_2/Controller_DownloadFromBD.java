package ru.geobuilder_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller_DownloadFromBD {

    @FXML
    private Button goBackButton;

    @FXML
    private MenuItem megafon, mts, vimpel, t2, non;

    @FXML
    private MenuItem maintenance, emergencyWork;

    @FXML
    private CheckBox аddressCheck, operatorCheck, typeOfWorkCheck, basisOfWorksCheck, authorCheck, dateOfShootingCheck;

    @FXML
    private TextField addressTextField, basisOfWorksTextField, authorTextField;

    @FXML
    private TextField specifiedOperatorTextField;

    @FXML
    private SplitMenuButton operatorSplMenu, typeOfWorkSplMenu;

    @FXML
    private DatePicker dateOfShootingDatePicker;

    @FXML
    private Label numberOfObjects, numberOfInstance;

    private void updateNumberOfObjects() {
        numberOfObjects.setText("Найдено объектов: " );
    }

    private void updateNumberOfInstance() {
        numberOfObjects.setText("Найдено состояний: " );
    }

    @FXML
    private void setAddressCheck(){
        if (аddressCheck.isSelected()){
            addressTextField.setDisable(false);
        } else {
            addressTextField.setDisable(true);
        }
    }

    @FXML
    private void setOperatorCheck(){
        if (operatorCheck.isSelected()){
            operatorSplMenu.setDisable(false);
        } else {
            operatorSplMenu.setDisable(true);
        }
    }

    @FXML
    public void setMegafon(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(megafon.getText());
    }

    @FXML
    public void setMts(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(mts.getText());
    }

    @FXML
    public void setT2(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(t2.getText());
    }

    @FXML
    public void setVimpel(ActionEvent event){
        specifiedOperatorTextField.setDisable(true);
        operatorSplMenu.setText(vimpel.getText());
    }

    @FXML
    public void setNon(ActionEvent event){
        specifiedOperatorTextField.setDisable(false);
        operatorSplMenu.setText(non.getText());
    }

    /**
     * Возвращаемся на предыдущую страницу
     * @param event
     */
    @FXML
    public void goingBackToNewCal(ActionEvent event) {

        goBackButton.getScene().getWindow().hide();
        Controller_Main controller_main = new Controller_Main();
        controller_main.getNewCal(event);
    }

    public void setTypeOfWork(ActionEvent event) {
        if(typeOfWorkCheck.isSelected()){
            typeOfWorkSplMenu.setDisable(false);
        } else {
            typeOfWorkSplMenu.setDisable(true);
        }
    }

    public void setBasisOfWorks(ActionEvent event) {
        if(basisOfWorksCheck.isSelected()){
            basisOfWorksTextField.setDisable(false);
        } else {
            basisOfWorksTextField.setDisable(false);
        }
    }

    public void setAuthor(ActionEvent event) {
        if(authorCheck.isSelected()){
            authorTextField.setDisable(false);
        } else {
            authorTextField.setDisable(true);
        }
    }

    public void setDateOfShooting(ActionEvent event) {
       if(dateOfShootingCheck.isSelected()){
           dateOfShootingDatePicker.setDisable(false);
       }
    }

    public void setMaintenance(ActionEvent event) {
        typeOfWorkSplMenu.setText(maintenance.getText());
    }

    public void setEmergencyWork(ActionEvent event) {
        typeOfWorkSplMenu.setText(emergencyWork.getText());
    }
}
