package ru.geobuilder_2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Controller_NewCalculation {

    @FXML
    private TableView<Rib> tableRib;

    @FXML
    private TableColumn<Rib, Integer> tierColumn;

    @FXML
    private TableColumn<Rib, String> ribLengthColumn;

    private ObservableList<Rib> ribs = FXCollections.observableArrayList();

    @FXML
    private TextFlow messageField;

    @FXML
    private Label labelCount;
    @FXML
    private Button rotater;
    @FXML
    private RadioButton left;
    @FXML
    private RadioButton right;
    @FXML
    private RadioButton up;
    @FXML
    private RadioButton down;

    @FXML
    private Button goBack;

    @FXML
    private Button addTierRib;

    @FXML
    private Rectangle typeOfConstBorder;

    @FXML
    public void onCont(ActionEvent event) {
    //    typeOfConstBorder.setStroke(Color.RED);
    }

    @FXML
    public void goingBack(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(StartGeoApplication.getStage());
        alert.setTitle("Предупреждение");
        alert.setHeaderText("Вы уверены, что хотите вернуться назад?");
        alert.setContentText("Если да, нажмите \"ок\"");

        alert.showAndWait();
        goBack.getScene().getWindow().hide();
        StartGeoApplication startGeoApplication = new StartGeoApplication();
        startGeoApplication.iniRoot();
    }

    public void print() {

        messageField.getChildren().clear();
        for (Rib rib : ribs) {
            Text text = new Text("ярус: " + rib.getTier() + "    " + "грань:  " + rib.getRibLength() + "\n");
            messageField.getChildren().add(text);

        }
    }

    @FXML
    private void initialize() {

        tierColumn.setCellValueFactory(new PropertyValueFactory<Rib, Integer>("tier"));
        ribLengthColumn.setCellValueFactory(new PropertyValueFactory<Rib, String>("ribLength"));

        // указываем, что хотим использовать этот набор данных из коллекции RibsList
        tableRib.setItems(ribs);

        // Добавляем слушателя для автоматического отслеживания изменений в листе
        ribs.addListener((ListChangeListener<Rib>) c -> updateCountLabel());

        updateCountLabel();

        showRibsDetails(null);
         //Следим за выделеной строкой. То, что выделли, появляется в полях
        tableRib.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRibsDetails(newValue));

        // Разрешаем изменения в таблице
        tableRib.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        ribLengthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    int numb = 0;

    /**
     * Вызывается, когда пользователь кликаек по кнопке добавить
     *
     * @param event
     */
    @FXML
    public void addRib(ActionEvent event) {
        numb++;
        Rib rib = new Rib(numb, "");
        ribs.add(rib);
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void removeRib(){
        numb--;
        tableRib.getItems().remove(ribs.size() - 1);
        // если надо удалить выделенную строку, то - tableRib.getItems().remove(selectedIndex);
    }

    /**
     * Выводим в поля
     * @param rib
     */
    private void showRibsDetails(Rib rib) {
        if (rib != null) {
            valueTier.setText(String.valueOf(rib.getTier()));
            valueRib.setText(rib.getRibLength());
        } else {
            valueTier.setText("");
            valueRib.setText("");
        }
    }
    @FXML
    private Label valueTier;

    @FXML
    private Label valueRib;


    private void updateCountLabel() {
        labelCount.setText("Кол-во ярусов: " + ribs.size());
    }

    @FXML
    private void rotateFigure() {
        RotateTransition rotateTransitionSquare = new RotateTransition(Duration.ONE, square);
        rotateTransitionSquare.setByAngle(45);
        rotateTransitionSquare.play();

        RotateTransition rotateTransitionTriangle = new RotateTransition(Duration.ONE, triangle);
        rotateTransitionTriangle.setByAngle(180);
        rotateTransitionTriangle.play();

        TranslateTransition transition = new TranslateTransition(Duration.ONE, triangle);
        transition.setByX(0f);
        transition.setByY(0f);
        transition.play();

    }

    @FXML
    private void setSide() {
        if (left.isSelected()) {
            pTrForSqr.setVisible(true);
            pTrForSqr.setText(directionTwo.getText());
            pO.setVisible(false);
        } else {
            if (right.isSelected()) {
                pO.setVisible(true);
                pO.setText(directionTwo.getText());
                pTrForSqr.setVisible(false);
            }
        }
    }

    @FXML
    private void setUpDown() {
        if (down.isSelected()) {
            pT.setVisible(true);
            pT.setText(directionOne.getText());
            pF.setVisible(false);
        } else {
            if (up.isSelected()) {
                pF.setVisible(true);
                pF.setText(directionOne.getText());
                pT.setVisible(false);
            }
        }
    }

    @FXML
    private Rectangle square;

    @FXML
    private Polygon triangle;

    @FXML
    private Text pO, pT, pTrForAngl, pTrForSqr, pF;

    @FXML
    private Button inOrder;

    /**
     * Нажатие кнопки -ABCD- Получам стандартное расположение четырех точек съемки
     *
     * @param event
     */
    @FXML
    private void standardFollowing(ActionEvent event) {
        ourBorder.setSelected(true);
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        down.setVisible(false);
        directionOne.setText(litOfPointAOne.getText());
        directionTwo.setText(litOfPointBTwo.getText());
        directionThree.setText(litOfPointCThree.getText());
        directionFour.setText(litOfPointDFour.getText());
        pointOne.setText(dOne.getText());
        pointTwo.setText(dOne.getText());
        pointThree.setText(dOne.getText());
        pointFour.setText(dOne.getText());
        totalPoints.setText(totalThree.getText());
        directionThree.setDisable(false);
        pointThree.setDisable(false);
        directionFour.setDisable(false);
        pointFour.setDisable(false);
        pO.setVisible(true);
        pT.setVisible(true);
        pTrForSqr.setVisible(true);
        pF.setVisible(true);
        pO.setText(directionOne.getText());
        pT.setText(directionTwo.getText());
        pTrForAngl.setText(directionThree.getText());
        pTrForSqr.setText(directionThree.getText());
        pF.setText(directionFour.getText());
        triangle.setVisible(false);
        square.setVisible(true);
    }

    @FXML
    private CheckBox manualInput;

    /**
     * Говорим, что данные углов будем вводить вручную
     *
     * @param event
     */
    @FXML
    private void enterValueManually(ActionEvent event) {
        if (manualInput.isSelected()) { // если выбрано
            // adressFieldFile.setEditable(false); // запрет на ввод, но поле активно
            addressFieldFile.setDisable(true); // поле не активно
            openJOB.setText("Внести углы");
            addressFieldFile.setText("Для внесения углов нажмите - \"Внести углы\"");
            //openJOB.setDisable(true); // команда для деактивации кнопки
        } else {
            addressFieldFile.setText("");
            openJOB.setText("Открыть");
            addressFieldFile.setDisable(false);
        }
    }

    /**
     * Граница допуска поумолчанию
     */
    private int border = 1000;

    @FXML
    private RadioButton towerBorder, mastBorder, metalPoleBorder, reinforcedPoleBorder, ourBorder;

    @FXML
    private TextField borderField;

    @FXML
    private Text mm;

    /**
     * Задание допуска объекта
     *
     * @param event
     */
    @FXML
    private void setBorder(ActionEvent event) {
        if (towerBorder.isSelected() || metalPoleBorder.isSelected()) {
            totalTwo.setVisible(true);
            totalThree.setVisible(true);
            borderField.setVisible(true);
            mm.setVisible(true);
            borderField.setText("1000");
            borderField.setDisable(true);
            border = Integer.parseInt(borderField.getText());
        } else if (mastBorder.isSelected()) {
            totalTwo.setVisible(true);
            totalThree.setVisible(true);
            borderField.setVisible(true);
            mm.setVisible(true);
            borderField.setText("1500");
            borderField.setDisable(true);
            border = Integer.parseInt(borderField.getText());
        } else if (reinforcedPoleBorder.isSelected()) {
            borderField.setVisible(true);
            mm.setVisible(true);
            borderField.setText("600");
            borderField.setDisable(true);
            border = Integer.parseInt(borderField.getText());
        } else if (ourBorder.isSelected()) {
            totalTwo.setVisible(true);
            totalThree.setVisible(true);
            borderField.setVisible(true);
            mm.setVisible(true);
            borderField.setDisable(false);
            borderField.clear();
            border = Integer.parseInt(borderField.getText());
        }
        if (metalPoleBorder.isSelected() || reinforcedPoleBorder.isSelected()) {
            totalTwo.setVisible(false);
            totalThree.setVisible(false);
            setTextTotalOne(event);
        }
    }

    @FXML
    private TextField addressFieldFile;

    @FXML
    private TextField addressFieldDirectory;

    @FXML
    private MenuItem aOne, aTwo, bOne, bTwo, cOne, cTwo, dOne, dTwo;

    @FXML
    private SplitMenuButton directionOne, directionTwo, directionThree, directionFour;

    @FXML
    private MenuItem litOfPointAOne, litOfPointATwo, litOfPointAThree, litOfPointAFour,
            litOfPointBOne, litOfPointBTwo, litOfPointBThree, litOfPointBFour,
            litOfPointCOne, litOfPointCTwo, litOfPointCThree, litOfPointCFour, litOfPointNullThree,
            litOfPointDOne, litOfPointDTwo, litOfPointDThree, litOfPointDFour, litOfPointNullFour;

    @FXML
    private Button openJOB, pathDirectory, advancedSettingsButton;

    @FXML
    private SplitMenuButton pointOne, pointTwo, pointThree, pointFour, totalPoints;

    @FXML
    private MenuItem totalOne, totalTwo, totalThree;

    /**
     * Выбор файла с расчетом либо открытие окна с вводом данных
     *
     * @param event
     */
    @FXML
    public void openFileJob(ActionEvent event) {

        if (!manualInput.isSelected()) {
            FileChooser fileChooserJob = new FileChooser();
            fileChooserJob.setInitialDirectory(new File("C:\\Users\\Home")); // Указываем какую папку открыть изначально
            fileChooserJob.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("txt Files", "*.txt")); // Задаем расширения для выбора конкретных файлов
            File selectedFile = fileChooserJob.showOpenDialog(null);
            if (selectedFile != null) {
                addressFieldFile.setText(selectedFile.getAbsolutePath());
            } else {
                System.out.println("file is not valid");
            }
        } else {

            /**
             * Открываем окно для внесения углов вручную
             */
            try {
                FXMLLoader fxmlLoaderManualInput = new FXMLLoader(Controller_NewCalculation.class.getResource("manualInput-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoaderManualInput.load(), 905, 723);

                stage.setTitle("Table");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void toChange(ActionEvent event) {
    }


    /**
     * Выбор директории для сохранинения расчета
     *
     * @param event
     */
    @FXML
    void specifyDirectory(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:\\Users\\Home")); // Указываем, какую папку открыть изначально
        File selectedDir = directoryChooser.showDialog(null);
        if (selectedDir != null) {
            addressFieldDirectory.setText(selectedDir.getAbsolutePath());
        } else {
            System.out.println("file is not valid");
        }
    }

    // Точки съемки

    /**
     * Выбор первой стоянки, как А
     *
     * @param event
     */
    @FXML
    private void setLetterPointOfFirstDirectionA(ActionEvent event) {
        directionOne.setText(litOfPointAOne.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pO.setText(litOfPointAOne.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pO.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pO.setText(litOfPointAOne.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pO.setVisible(true);
                }
            }
        } else {
            if (down.isSelected()) {
                pT.setText(litOfPointAOne.getText());
                pT.setVisible(true);
                pF.setText("");
                pF.setVisible(false);
            } else {
                if (up.isSelected()) {
                    pT.setText("");
                    pT.setVisible(false);
                    pF.setText(litOfPointAOne.getText());
                    pF.setVisible(true);
                }
            }
        }
    }

    /**
     * Выбор первой стоянки, как В
     *
     * @param event
     */
    @FXML
    private void setLetterPointOfFirstDirectionB(ActionEvent event) {
        directionOne.setText(litOfPointBOne.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pO.setText(litOfPointBOne.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pO.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pO.setText(litOfPointBOne.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pO.setVisible(true);
                }
            }
        } else {
            if (down.isSelected()) {
                pF.setText("");
                pF.setVisible(false);
                pT.setText(litOfPointBOne.getText());
                pT.setVisible(true);
            } else {
                if (up.isSelected()) {
                    pF.setText(litOfPointBOne.getText());
                    pF.setVisible(true);
                    pT.setText("");
                    pT.setVisible(false);
                }
            }
        }
    }

    /**
     * Выбор первой стоянки, как С
     *
     * @param event
     */
    @FXML
    private void setLetterPointOfFirstDirectionC(ActionEvent event) {
        directionOne.setText(litOfPointCOne.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pO.setText(litOfPointCOne.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pO.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pO.setText(litOfPointCOne.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pO.setVisible(true);
                }
            }
        } else {
            if (down.isSelected()) {
                pT.setText(litOfPointCOne.getText());
                pT.setVisible(true);
                pF.setText("");
                pF.setVisible(false);
            } else {
                if (up.isSelected()) {
                    pF.setText(litOfPointCOne.getText());
                    pF.setVisible(true);
                    pT.setText("");
                    pT.setVisible(false);
                }
            }
        }
    }

    /**
     * Выбор первой стоянки, как D
     *
     * @param event
     */
    @FXML
    private void setLetterPointOfFirstDirectionD(ActionEvent event) {
        directionOne.setText(litOfPointDOne.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pO.setText(litOfPointDOne.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pO.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pO.setText(litOfPointDOne.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pO.setVisible(true);
                }
            }
        } else {
            if (down.isSelected()) {
                pT.setText(litOfPointDOne.getText());
                pT.setVisible(true);
                pF.setText("");
                pF.setVisible(false);
            } else {
                if (up.isSelected()) {
                    pF.setText(litOfPointDOne.getText());
                    pF.setVisible(true);
                    pT.setText("");
                    pT.setVisible(false);
                }
            }
        }
    }

    @FXML
    private void setLetterPointOfSecondDirectionA(ActionEvent event) {
        directionTwo.setText(litOfPointATwo.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pT.setText(litOfPointATwo.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pT.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pT.setText(litOfPointATwo.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pT.setVisible(true);
                }
            }
        } else { // если две стоянки
            if (right.isSelected()) {
                pO.setText(litOfPointATwo.getText());
                pO.setVisible(true);
            } else {
                if (left.isSelected()) {
                    pO.setVisible(false);
                    pTrForSqr.setText(litOfPointATwo.getText());
                    pTrForSqr.setVisible(true);
                }
            }
        }
    }

    @FXML
    private void setLetterPointOfSecondDirectionB(ActionEvent event) {
        directionTwo.setText(litOfPointBTwo.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pT.setText(litOfPointBTwo.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pT.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pT.setText(litOfPointBTwo.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pT.setVisible(true);
                }
            }
        } else {
            if (right.isSelected()) {
                pO.setText(litOfPointBTwo.getText());
                pO.setVisible(true);
            } else {
                if (left.isSelected()) {
                    pO.setVisible(false);
                    pTrForSqr.setText(litOfPointBTwo.getText());
                    pTrForSqr.setVisible(true);
                }
            }
        }
    }

    @FXML
    private void setLetterPointOfSecondDirectionC(ActionEvent event) {
        directionTwo.setText(litOfPointCTwo.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pT.setText(litOfPointCTwo.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pT.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pT.setText(litOfPointCTwo.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pT.setVisible(true);
                }
            }
        } else {
            if (right.isSelected()) {
                pO.setText(litOfPointCTwo.getText());
                pO.setVisible(true);
            } else {
                if (left.isSelected()) {
                    pO.setVisible(false);
                    pTrForSqr.setText(litOfPointCTwo.getText());
                    pTrForSqr.setVisible(true);
                }
            }
        }
    }

    @FXML
    private void setLetterPointOfSecondDirectionD(ActionEvent event) {
        directionTwo.setText(litOfPointDTwo.getText());
        if (totalPoints.getText() != totalOne.getText()) {
            if (totalPoints.getText() == totalTwo.getText()) {
                pT.setText(litOfPointDTwo.getText());
                square.setVisible(false);
                triangle.setVisible(true);
                pT.setVisible(true);
            } else {
                if (totalPoints.getText() == totalThree.getText()) {
                    pT.setText(litOfPointDTwo.getText());
                    square.setVisible(true);
                    triangle.setVisible(false);
                    pT.setVisible(true);
                }
            }
        } else {
            if (right.isSelected()) {
                pO.setText(litOfPointDTwo.getText());
                pO.setVisible(true);
            } else {
                if (left.isSelected()) {
                    pO.setVisible(false);
                    pTrForSqr.setText(litOfPointDTwo.getText());
                    pTrForSqr.setVisible(true);
                }
            }
        }
    }

    @FXML
    private void setLetterPointOfThirdDirectionA(ActionEvent event) {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        down.setVisible(false);
        pO.setText(directionOne.getText());
        pT.setText(directionTwo.getText());
        directionThree.setText(litOfPointAThree.getText());
        pointThree.setDisable(false);
        pTrForAngl.setText(litOfPointAThree.getText());
        pTrForSqr.setText(litOfPointAThree.getText());
        if (!directionFour.getText().isEmpty() & !directionFour.isDisabled()) {
            totalPoints.setText(totalThree.getText());
            square.setVisible(true);
            triangle.setVisible(false);
            pTrForSqr.setVisible(true);
            pTrForAngl.setVisible(false);
        } else {
            totalPoints.setText(totalTwo.getText());
            square.setVisible(false);
            triangle.setVisible(true);
            pTrForSqr.setVisible(false);
            pTrForAngl.setVisible(true);
        }
    }

    @FXML
    private void setLetterPointOfThirdDirectionB(ActionEvent event) {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        down.setVisible(false);
        pO.setText(directionOne.getText());
        pT.setText(directionTwo.getText());
        directionThree.setText(litOfPointBThree.getText());
        pointThree.setDisable(false);
        pTrForAngl.setText(litOfPointBThree.getText());
        pTrForSqr.setText(litOfPointBThree.getText());
        if (!directionFour.getText().isEmpty() & !directionFour.isDisabled()) {
            totalPoints.setText(totalThree.getText());
            square.setVisible(true);
            triangle.setVisible(false);
            pTrForSqr.setVisible(true);
            pTrForAngl.setVisible(false);
        } else {
            totalPoints.setText(totalTwo.getText());
            square.setVisible(false);
            triangle.setVisible(true);
            pTrForSqr.setVisible(false);
            pTrForAngl.setVisible(true);
        }
    }

    @FXML
    private void setLetterPointOfThirdDirectionC(ActionEvent event) {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        down.setVisible(false);
        pO.setText(directionOne.getText());
        pT.setText(directionTwo.getText());
        directionThree.setText(litOfPointCThree.getText());
        pointThree.setDisable(false);
        pTrForAngl.setText(litOfPointCThree.getText());
        pTrForSqr.setText(litOfPointCThree.getText());
        if (!directionFour.getText().isEmpty() & !directionFour.isDisabled()) {
            totalPoints.setText(totalThree.getText());
            square.setVisible(true);
            triangle.setVisible(false);
            pTrForSqr.setVisible(true);
            pTrForAngl.setVisible(false);
        } else {
            totalPoints.setText(totalTwo.getText());
            square.setVisible(false);
            triangle.setVisible(true);
            pTrForSqr.setVisible(false);
            pTrForAngl.setVisible(true);
        }
    }

    @FXML
    private void setLetterPointOfThirdDirectionD(ActionEvent event) {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        down.setVisible(false);
        pO.setText(directionOne.getText());
        pT.setText(directionTwo.getText());
        directionThree.setText(litOfPointDThree.getText());
        pointThree.setDisable(false);
        pTrForAngl.setText(litOfPointDThree.getText());
        pTrForSqr.setText(litOfPointDThree.getText());
        if (!directionFour.getText().isEmpty() & !directionFour.isDisabled()) {
            totalPoints.setText(totalThree.getText());
            square.setVisible(true);
            triangle.setVisible(false);
            pTrForSqr.setVisible(true);
            pTrForAngl.setVisible(false);
        } else {
            totalPoints.setText(totalTwo.getText());
            square.setVisible(false);
            triangle.setVisible(true);
            pTrForSqr.setVisible(false);
            pTrForAngl.setVisible(true);
        }
    }

    @FXML
    private void setLetterPointOfThirdDirectionNull(ActionEvent event) {
        setTextTotalOne(event);
//        left.setVisible(true);
//        right.setVisible(true);
//        up.setVisible(true);
//        down.setVisible(true);
//        directionThree.setText("");
//        directionFour.setText("");
//        directionFour.setDisable(true);
//        pointThree.setDisable(true);
//        pointFour.setDisable(true);
//        totalPoints.setText(totalOne.getText());
//        pT.setText(directionOne.getText());
//        pTrForAngl.setText("");
//        pTrForSqr.setText("");
//        pF.setText("");
//        pF.setVisible(false);
//        square.setVisible(true);
//        triangle.setVisible(true);
//        pTrForAngl.setVisible(false);
//        pTrForSqr.setVisible(false);
//        if (right.isSelected()) {
//            pO.setText(directionTwo.getText());
//        } else {
//            if (left.isSelected()) {
//                pO.setVisible(false);
//                pTrForSqr.setVisible(true);
//                pTrForSqr.setText(directionTwo.getText());
//            }
//        }
    }

    @FXML
    private void setLetterPointOfFourthDirectionA(ActionEvent event) {
        directionFour.setText(litOfPointAFour.getText());
        pointFour.setDisable(false);
        totalPoints.setText(totalThree.getText());
        pF.setText(litOfPointAFour.getText());
        square.setVisible(true);
        pF.setVisible(true);
        triangle.setVisible(false);
        pTrForAngl.setVisible(false);
        pTrForSqr.setVisible(true);
    }

    @FXML
    private void setLetterPointOfFourthDirectionB(ActionEvent event) {
        directionFour.setText(litOfPointBFour.getText());
        pointFour.setDisable(false);
        totalPoints.setText(totalThree.getText());
        pF.setText(litOfPointBFour.getText());
        square.setVisible(true);
        pF.setVisible(true);
        triangle.setVisible(false);
        pTrForSqr.setVisible(true);
    }

    @FXML
    private void setLetterPointOfFourthDirectionC(ActionEvent event) {
        directionFour.setText(litOfPointCFour.getText());
        pointFour.setDisable(false);
        totalPoints.setText(totalThree.getText());
        pF.setText(litOfPointCFour.getText());
        square.setVisible(true);
        pF.setVisible(true);
        triangle.setVisible(false);
        pTrForSqr.setVisible(true);
    }

    @FXML
    private void setLetterPointOfFourthDirectionD(ActionEvent event) {
        directionFour.setText(litOfPointDFour.getText());
        pointFour.setDisable(false);
        totalPoints.setText(totalThree.getText());
        pF.setText(litOfPointDFour.getText());
        square.setVisible(true);
        pF.setVisible(true);
        triangle.setVisible(false);
        pTrForSqr.setVisible(true);
    }

    @FXML
    private void setLetterPointOfFourthDirectionNull(ActionEvent event) {
        setTextTotalTwo(event);
//        directionFour.setText("");
//        pointFour.setDisable(true);
//        totalPoints.setText(totalTwo.getText());
//        pF.setText("");
//        square.setVisible(false);
//        pF.setVisible(false);
//        triangle.setVisible(true);
//        pTrForSqr.setVisible(false);
//        if (directionThree != null) {
//            pTrForAngl.setVisible(true);
//        } else {
//            pTrForAngl.setVisible(false);
//        }
    }

    @FXML
    void setTextPointOneO(ActionEvent event) {
        pointOne.setText(dOne.getText());
    }

    @FXML
    void setTextPointOneT(ActionEvent event) {
        pointOne.setText(dTwo.getText());
    }

    @FXML
    void setTextPointTwoO(ActionEvent event) {
        pointTwo.setText(dOne.getText());
    }

    @FXML
    void setTextPointTwoT(ActionEvent event) {
        pointTwo.setText(dTwo.getText());
    }

    @FXML
    void setTextPointThreeO(ActionEvent event) {
        pointThree.setText(dOne.getText());
    }

    @FXML
    void setTextPointThreeT(ActionEvent event) {
        pointThree.setText(dTwo.getText());
    }

    @FXML
    void setTextPointFourO(ActionEvent event) {
        pointFour.setText(dOne.getText());
    }

    @FXML
    void setTextPointFourT(ActionEvent event) {
        pointFour.setText(dTwo.getText());
    }

    @FXML
    private void setTextTotalOne(ActionEvent event) {
        left.setVisible(true);
        right.setVisible(true);
        up.setVisible(true);
        down.setVisible(true);
        totalPoints.setText(totalOne.getText());
        directionThree.setDisable(true);
        pointThree.setDisable(true);
        directionFour.setDisable(true);
        pointFour.setDisable(true);
        pT.setText(directionOne.getText());
        pO.setVisible(true);
        pT.setVisible(true);
        pTrForAngl.setVisible(false);
        pTrForSqr.setVisible(false);
        pF.setVisible(false);
        pTrForAngl.setText("");
        pF.setText("");
        square.setVisible(true);
        triangle.setVisible(true);
        if (right.isSelected()) {
            pO.setText(directionTwo.getText());
        } else {
            if (left.isSelected()) {
                pO.setVisible(false);
                pTrForSqr.setVisible(true);
                pTrForSqr.setText(directionTwo.getText());
            }
        }
    }

    @FXML
    private void setTextTotalTwo(ActionEvent event) {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        down.setVisible(false);
        totalPoints.setText(totalTwo.getText());
        pO.setText(directionOne.getText());
        pT.setText(directionTwo.getText());
        pTrForAngl.setText(directionThree.getText());
        directionThree.setDisable(false);
        pointThree.setDisable(false);
        directionFour.setDisable(true);
        pointFour.setDisable(true);
        pTrForAngl.setVisible(true);
        pTrForSqr.setVisible(false);
        pF.setText("");
        square.setVisible(false);
        triangle.setVisible(true);
    }

    @FXML
    private void setTextTotalThree(ActionEvent event) {
        left.setVisible(false);
        right.setVisible(false);
        up.setVisible(false);
        down.setVisible(false);
        totalPoints.setText(totalThree.getText());
        pO.setText(directionOne.getText());
        pT.setText(directionTwo.getText());
        pTrForSqr.setText(directionThree.getText());
        pF.setText(directionFour.getText());
        directionThree.setDisable(false);
        pointThree.setDisable(false);
        directionFour.setDisable(false);
        pointFour.setDisable(false);
        square.setVisible(true);
        triangle.setVisible(false);
        pTrForAngl.setVisible(false);
        pTrForSqr.setVisible(true);
        pF.setVisible(true);
    }
}
