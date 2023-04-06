package ru.geobuilder_2;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.geobuilder_2.model.DataPreparer;
import ru.geobuilder_2.model.SceneName;

public class Controller_NewCalculation  implements Serializable {

//    public Controller_NewCalculation(int border, int quantityOfPoints,
//                                     LinkedHashMap<String, Integer> mapQuantityPointsForEachDirection,
//                                     Boolean fromFailData, ArrayList<String> inputData){
//        this.border = border;
//        this.quantityOfPoints = quantityOfPoints;
//        this.mapQuantityPointsForEachDirection = mapQuantityPointsForEachDirection;
//        this.fromFailData = fromFailData;
//        this.inputData = inputData;
//    }

    public Controller_NewCalculation(){
    }

    /**Допуск
     *
     */
    private int border = 1000;

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    /**
     * Количество стоянок (2, 3 или 4)
     */
    private int quantityOfPoints;

    public int getQuantityOfPoints() {
        return quantityOfPoints;
    }

    private void setQuantityOfPoints() {
        this.quantityOfPoints = Integer.parseInt(totalPoints.getText());
    }

    private LinkedHashMap<String, Integer> mapQuantityPointsForEachDirection = new LinkedHashMap<>();

    public LinkedHashMap<String, Integer> getMapQuantityPointsForEachDirection() {
        return mapQuantityPointsForEachDirection;
    }

    public void setMapQuantityPointsForEachDirection(LinkedHashMap<String, Integer> mapQuantityPointsForEachDirection) {
        this.mapQuantityPointsForEachDirection = mapQuantityPointsForEachDirection;
    }

    Boolean fromFailData = true;



    public Boolean getFromFailData() {
        return fromFailData;
    }

    public void setFromFailData(Boolean fromFailData) {
        this.fromFailData = fromFailData;
    }

    private ArrayList<String> inputData = new ArrayList<>();

    public ArrayList<String> getInputData() {
        return inputData;
    }

    public void setInputData(ArrayList<String> inputData) {
        this.inputData = inputData;
    }

    private Stage stage;

    @FXML
    private Button saveCloseButton;

    @FXML
    private TableView<RibJFX> tableRib;

    @FXML
    private TableColumn<RibJFX, Integer> tierColumn;

    @FXML
    private TableColumn<RibJFX, String> ribLengthColumn;

    private ObservableList<RibJFX> ribsJFX = FXCollections.observableArrayList();

    public void setQuantityOfPoints(int quantityOfPoints) {
        this.quantityOfPoints = quantityOfPoints;
    }

    public ArrayList<String> getListRs() {
        return listRs;
    }

    public void setListRs(ArrayList<String> listRs) {
        this.listRs = listRs;
    }

    public ArrayList<Double> getListRibs() {
        return listRibs;
    }

    public void setListRibs(ArrayList<Double> listRibs) {
        this.listRibs = listRibs;
    }

    private ArrayList<String> listRs = new ArrayList<>();

    private ArrayList<Double> listRibs = new ArrayList<>();

    public ArrayList<Double> getListRibsDouble() {
        setListRibsDouble(listRs);
        return listRibs;
    }

    public void setListRibsDouble(ArrayList<String> lRibs) {
        ArrayList<Double> lr = new ArrayList<>();

        for (String val : lRibs){
            lr.add(Double.valueOf(val));
        }
        listRibs = lr;
    }

    private void generatelistRs(){
        listRs.clear();
    for (int i = 0; i < ribsJFX.size(); i ++){
        listRs.add(ribsJFX.get(i).getRibLength());
        }
        getListRibsDouble();
    }

    @FXML
    private TextFlow messageField;

    @FXML
    private Label labelCount;

    public Label getLabelCount() {
        return labelCount;
    }

    public void setLabelCount(Label labelCount) {
        this.labelCount = labelCount;
    }

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
    public void createСalculation(ActionEvent event) {
        //    typeOfConstBorder.setStroke(Color.RED);
    }

//    @FXML
//    public void goingBack(ActionEvent event) {
//        save();
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.initOwner(StartGeoApplication.getStage());
//        alert.setTitle("Предупреждение");
//        alert.setHeaderText("Вы уверены, что хотите вернуться назад?");
//        alert.setContentText("Если да, нажмите \"ок\"");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//            goBack.getScene().getWindow().hide();
//            StartGeoApplication startGeoApplication = new StartGeoApplication();
//            startGeoApplication.iniRoot();
//        } else {
//            // ... user chose CANCEL or closed the dialog
//            alert.close();
//        }
//    }

    public void print() {

        messageField.getChildren().clear();
        for (RibJFX ribJFX : ribsJFX) {
            Text text = new Text("ярус: " + ribJFX.getTier() + "    " + "грань:  " + ribJFX.getRibLength() + "\n");
            messageField.getChildren().add(text);
        }
            for (String in : inputData) {
                Text text = new Text(in + "\n");
                messageField.getChildren().add(text);
            }
    }

    @FXML
    private void initialize() {

        borderField.textProperty().addListener((observable, oldValue, newValue) -> {
            setBorder(Integer.parseInt(newValue));
        });

        loadRibs();

        tierColumn.setCellValueFactory(new PropertyValueFactory<RibJFX, Integer>("tier"));
        ribLengthColumn.setCellValueFactory(new PropertyValueFactory<RibJFX, String>("ribLength"));

        // указываем, что хотим использовать этот набор данных из коллекции ribs
        tableRib.setItems(this.ribsJFX);

        // Разрешаем изменения в таблице
        tableRib.setEditable(true);

        // Разрешаем вносить изменение в определенную колонку
        ribLengthColumn.setCellFactory((TextFieldTableCell.forTableColumn()));

//        // Добавляем слушателя для автоматического отслеживания изменений в листе
//        ribs.addListener((ListChangeListener<Rib>) c -> openCalculation());
//
//        updateCountLabel();

//        showRibsDetails(null);
//         //Следим за выделеной строкой. То, что выделли, появляется в полях
//        tableRib.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showRibsDetails(newValue));


    }

    /**
     * Вызывается, когда пользователь кликаек по кнопке добавить
     *
     * @param event
     */
    @FXML
    public void addRib(ActionEvent event) {
        if (ribsJFX.isEmpty()) {
            RibJFX ribJFX = new RibJFX(this.tableRib.getItems().size() + 1, "");
            tableRib.getItems().add(ribJFX);
        } else {
        RibJFX ribJFX = new RibJFX(this.tableRib.getItems().size() + 1,
                (tableRib.getItems().get(tableRib.getItems().size() - 1)).getRibLength());
        tableRib.getItems().add(ribJFX);
    }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void removeRib() {

        tableRib.getItems().remove(ribsJFX.size() - 1);
        // если надо удалить выделенную строку, то - tableRib.getItems().remove(selectedIndex);
    }

//    /**
//     * Выводим в поля
//     * @param rib
//     */
//    private void showRibsDetails(Rib rib) {
//        if (rib != null) {
//            valueTier.setText(String.valueOf(rib.getTier()));
//            valueRib.setText(rib.getRibLength());
//        } else {
//            valueTier.setText("");
//            valueRib.setText("");
//        }
//    }

    // Нужно для инициализации измененных значений в ячейках. Без него данные не воспринимаются
    public void onEditChanger(TableColumn.CellEditEvent<RibJFX, String> ribStringCellEditEvent) {
        RibJFX ribJFX = tableRib.getSelectionModel().getSelectedItem();
        ribJFX.setRibLength(ribStringCellEditEvent.getNewValue());
    }

    private void updateCountLabel() {
        labelCount.setText("Кол-во ярусов: " + ribsJFX.size());
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

    public Text getpO() {
        return pO;
    }

    public void setpO(Text pO) {
        this.pO = pO;
    }

    public Text getpT() {
        return pT;
    }

    public void setpT(Text pT) {
        this.pT = pT;
    }

    public Text getpTrForAngl() {
        return pTrForAngl;
    }

    public void setpTrForAngl(Text pTrForAngl) {
        this.pTrForAngl = pTrForAngl;
    }

    public Text getpTrForSqr() {
        return pTrForSqr;
    }

    public void setpTrForSqr(Text pTrForSqr) {
        this.pTrForSqr = pTrForSqr;
    }

    public Text getpF() {
        return pF;
    }

    public void setpF(Text pF) {
        this.pF = pF;
    }

    @FXML
    private Button inOrder;

    /**
     * Нажатие кнопки -ABCD- Получам стандартное расположение четырех точек съемки
     *
     * @param event
     */
    @FXML
    private void standardFollowing(ActionEvent event) {
        if (metalPoleBorder.isSelected() || reinforcedPoleBorder.isSelected()) {
            towerBorder.setSelected(true);
        }
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

//    @FXML
//    private CheckBox manualInput;

    @FXML
    private RadioButton downloadFromFileBut;

    @FXML
    private RadioButton manualInputBut;

    @FXML
    private RadioButton downloadFromBDBut;

    @FXML
    private void downloadingFromFile(ActionEvent event) {
        if (downloadFromFileBut.isSelected()) {
            this.fromFailData = true;
            addressFieldFile.setText("");
            openJOB.setText("Открыть");
            addressFieldFile.setDisable(false);
        } else {

        }
    }

    @FXML
    private void downloadingFromBD(ActionEvent event) {
        if (downloadFromBDBut.isSelected()) {
            this.fromFailData = false;
            addressFieldFile.setText("");
            addressFieldFile.setDisable(true);
            openJOB.setText("Загрузить из БД");
        }
    }

    /**
     * Говорим, что данные углов будем вводить вручную
     *
     * @param event
     */
    @FXML
    private void enterValueManually(ActionEvent event) {
        if (manualInputBut.isSelected()) { // если выбрано
            this.fromFailData = false;
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
            setBorder(Integer.parseInt(borderField.getText()));
        } else if (mastBorder.isSelected()) {
            totalTwo.setVisible(true);
            totalThree.setVisible(true);
            borderField.setVisible(true);
            mm.setVisible(true);
            borderField.setText("1500");
            borderField.setDisable(true);
            setBorder(Integer.parseInt(borderField.getText()));
        } else if (reinforcedPoleBorder.isSelected()) {
            borderField.setVisible(true);
            mm.setVisible(true);
            borderField.setText("600");
            borderField.setDisable(true);
            setBorder(Integer.parseInt(borderField.getText()));
        } else if (ourBorder.isSelected()) {
            totalTwo.setVisible(true);
            totalThree.setVisible(true);
            borderField.setVisible(true);
            mm.setVisible(true);
            borderField.setDisable(false);
            borderField.clear();
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
    public void openFileJob(ActionEvent event) throws IOException {

        if (downloadFromFileBut.isSelected()) {
            FileChooser fileChooserJob = new FileChooser();
//            fileChooserJob.setInitialDirectory(new File("C:\\Users\\Home")); // Указываем какую папку открыть изначально
            fileChooserJob.setInitialDirectory(new File("D:\\TestGB")); // Указываем какую папку открыть изначально
            fileChooserJob.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("txt Files", "*.txt")); // Задаем расширения для выбора конкретных файлов
            File selectedFile = fileChooserJob.showOpenDialog(null);
            if (selectedFile != null) {
                addressFieldFile.setText(selectedFile.getAbsolutePath());
            } else {
                System.out.println("file is not valid");
            }

//            try {
//				this.tableRib.setItems(this.deserializeData());
//			} catch (ClassNotFoundException | IOException e) {
//				e.printStackTrace();
//			}
        } else {
            if (manualInputBut.isSelected()) {
                inputData.clear();
                //Открываем окно для внесения углов вручную
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Controller_NewCalculation.class.getResource("manualInput-view.fxml"));
                    Stage stage = new Stage();
                    Scene sceneBD = new Scene(fxmlLoader.load(), 1300, 930);
                    stage.setMinWidth(1300);
                    stage.setMinHeight(930);
                    stage.setMaxWidth(1300);
                    stage.setMaxHeight(930);
                    stage.setTitle("Ввод данных вручную");
                    stage.setScene(sceneBD);
                    Controller_manualInput controllerManualInput = fxmlLoader.getController();
                    controllerManualInput.setStage(stage);
                    //controller.getTextPanelForRibs();
                    controllerManualInput.setInputData(inputData);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (downloadFromBDBut.isSelected()) {
                    inputData.clear();
                    //Открываем окно для получение данных из БД
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Controller_NewCalculation.class.getResource("downloadFromBD-view.fxml"));
                        Stage stage = new Stage();
                        Scene sceneBD = new Scene(fxmlLoader.load(), 1194, 854);
                        stage.setMinWidth(1194);
                        stage.setMinHeight(854);
                        stage.setMaxWidth(1194);
                        stage.setMaxHeight(854);
                        stage.setTitle("Database");
                        stage.setScene(sceneBD);
                        Controller_DownloadFromBD controllerDownloadFromBD = fxmlLoader.getController();
                        controllerDownloadFromBD.setStage(stage);
                        controllerDownloadFromBD.setInputData(inputData);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    private void openCalculation() throws DataPreparer.DateIsNotReadyException, IOException {
        generatelistRs();
        if (fromFailData) {
            setQuantityOfPoints();
            setQuantityPointsForEachDirection();
            DataPreparer dataPreparer = new DataPreparer(addressFieldFile.getText(), quantityOfPoints,
                    mapQuantityPointsForEachDirection, getListRibsDouble());
            dataPreparer.buildInputData();
            this.inputData = dataPreparer.getInputData();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Controller_NewCalculation.class.getResource("calculation-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1300, 930);
            stage.setMinWidth(1300);
            stage.setMinHeight(930);
            stage.setMaxWidth(1300);
            stage.setMaxHeight(930);
            stage.setTitle("Расчет");
            stage.setScene(scene);
            Controller_Calculation controller = fxmlLoader.getController();
                controller.setInputData(inputData);
                controller.setQuantityOfPoints(quantityOfPoints);
                controller.setMapQuantityPointsForEachDirection(mapQuantityPointsForEachDirection);
                controller.setListRibs(getListRibsDouble());
                controller.setFromFailData(fromFailData);
                controller.setBorder(border);
            //controller.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//                /**
//                 * Диалоговое окно авторизации
//                 */
//                // Create the custom dialog.
//                Dialog<Pair<String, String>> dialog = new Dialog<>();
//                dialog.setTitle("Login Dialog");
//                dialog.setHeaderText("Look, a Custom Login Dialog");
//
//// Set the icon (must be included in the project).
//                //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
//
//// Set the button types.
//                ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
//                dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
//
//// Create the username and password labels and fields.
//                GridPane grid = new GridPane();
//                grid.setHgap(10);
//                grid.setVgap(10);
//                grid.setPadding(new Insets(20, 150, 10, 10));
//
//                TextField username = new TextField();
//                username.setPromptText("Username");
//                PasswordField password = new PasswordField();
//                password.setPromptText("Password");
//
//                grid.add(new Label("Username:"), 0, 0);
//                grid.add(username, 1, 0);
//                grid.add(new Label("Password:"), 0, 1);
//                grid.add(password, 1, 1);
//
//// Enable/Disable login button depending on whether a username was entered.
//                Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
//                loginButton.setDisable(true);
//
//// Do some validation (using the Java 8 lambda syntax).
//                username.textProperty().addListener((observable, oldValue, newValue) -> {
//                    loginButton.setDisable(newValue.trim().isEmpty());
//                });
//
//                dialog.getDialogPane().setContent(grid);
//
//// Request focus on the username field by default.
//                Platform.runLater(() -> username.requestFocus());
//
//// Convert the result to a username-password-pair when the login button is clicked.
//                dialog.setResultConverter(dialogButton -> {
//                    if (dialogButton == loginButtonType) {
//                        return new Pair<>(username.getText(), password.getText());
//                    }
//                    return null;
//                });
//
//                Optional<Pair<String, String>> result = dialog.showAndWait();
//
//                result.ifPresent(usernamePassword -> {
//                    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//                });

    /**
     * Метод для открытия окна с нужной сценой
     *
     * @param fxml   - fxml файл с интерфейсом окна
     * @param width  - ширина окна
     * @param Height - высота окна
     * @param title  - название окна
     */
    public void openWindow(String fxml, int width, int Height, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Controller_NewCalculation.class.getResource(fxml));
            Stage stage = new Stage();
            Scene sceneBD = new Scene(fxmlLoader.load(), width, Height);
            stage.setMinWidth(1194);
            stage.setMinHeight(854);
            stage.setMaxWidth(1194);
            stage.setMaxHeight(854);
            stage.setTitle(title);
            stage.setScene(sceneBD);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveAndGoingBack() {
        save();
        stage.setScene(StartGeoApplication.getScenes().get(SceneName.MAIN_GB2));
    }

    @FXML
    public void saveAndExit() {
        save();
        //stage.close();
    }

    @FXML
    void save() {
        try {
            this.serializeDataTwo();
            //this.serializeDataCon(controller_newCalculation);
            this.serializeData(this.ribsJFX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void loadCon() {
//        try {
//            this.border = deserializeDataCon().getBorder();
//            this.quantityOfPoints = deserializeDataCon().getQuantityOfPoints();
//            this.mapQuantityPointsForEachDirection = deserializeDataCon().getMapQuantityPointsForEachDirection();
//            this.fromFailData = deserializeDataCon().getFromFailData();
//            this.inputData = deserializeDataCon().getInputData();
//            generatelistRs();
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private void loadRibs() {
        try {
            this.ribsJFX = this.deserializeData();
            this.tableRib.setItems(this.ribsJFX);
            generatelistRs();
            this.addressFieldDirectory.setText(this.deserializeDataTwo().get(0));
            this.addressFieldFile.setText(this.deserializeDataTwo().get(1));
            this.totalPoints.setText(this.deserializeDataTwo().get(2));
            this.directionOne.setText(this.deserializeDataTwo().get(3));
            this.directionTwo.setText(this.deserializeDataTwo().get(4));
            this.directionThree.setText(this.deserializeDataTwo().get(5));
            this.directionFour.setText(this.deserializeDataTwo().get(6));
            this.pointOne.setText(this.deserializeDataTwo().get(7));
            this.pointTwo.setText(this.deserializeDataTwo().get(8));
            this.pointThree.setText(this.deserializeDataTwo().get(9));
            this.pointFour.setText(this.deserializeDataTwo().get(10));
            //loadCon();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

//    Controller_NewCalculation controller_newCalculation = new Controller_NewCalculation(this.border,
//            this.quantityOfPoints, this.mapQuantityPointsForEachDirection, this.fromFailData, this.inputData);

//    private void serializeDataCon(Controller_NewCalculation controller_newCalculation) throws FileNotFoundException, IOException {
//        File f = new File("D:\\TestGB\\cache\\Con.txt");
//        try (FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(controller_newCalculation);
//            generatelistRs();
//        }
//    }

    private void serializeData(ObservableList<RibJFX> ribsJFX) throws FileNotFoundException, IOException {
        File f = new File("D:\\TestGB\\cache\\ribs.txt");
        try (FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            ArrayList<RibJFX> al = new ArrayList<RibJFX>(ribsJFX);
            oos.writeObject(new ArrayList<RibJFX>(al));
            generatelistRs();
        }
    }
    private ArrayList<String> serializeDataTwo() throws FileNotFoundException, IOException {
        ArrayList<String> listData = new ArrayList<>();
        listData.add(this.addressFieldDirectory.getText());
        listData.add(this.addressFieldFile.getText());
        listData.add(this.totalPoints.getText());
        listData.add(this.directionOne.getText());
        listData.add(this.directionTwo.getText());
        listData.add(this.directionThree.getText());
        listData.add(this.directionFour.getText());
        listData.add(this.pointOne.getText());
        listData.add(this.pointTwo.getText());
        listData.add(this.pointThree.getText());
        listData.add(this.pointFour.getText());
        File f = new File("D:\\TestGB\\cache\\ld.txt");
        try (FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new ArrayList<String>(listData));
        }
        return listData;
    }

//    private Controller_NewCalculation deserializeDataCon() throws FileNotFoundException, IOException, ClassNotFoundException {
//        Controller_NewCalculation controller_newCalculation = new Controller_NewCalculation();
//        File f = new File("D:\\TestGB\\cache\\Con.txt");
//        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
//            controller_newCalculation = (Controller_NewCalculation) ois.readObject();
//        }
//        return controller_newCalculation;
//    }

private ArrayList<String> deserializeDataTwo() throws FileNotFoundException, IOException, ClassNotFoundException {
    ArrayList<String> listData = new ArrayList<>();
    File f = new File("D:\\TestGB\\cache\\ld.txt");
    try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
        listData = (ArrayList<String>) ois.readObject();
    }
    return listData;
}


    private ObservableList<RibJFX> deserializeData() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<RibJFX> ribsJFX = new ArrayList<RibJFX>();
        File f = new File("D:\\TestGB\\cache\\ribs.txt");
        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
            ribsJFX = (ArrayList<RibJFX>) ois.readObject();
        }
        return FXCollections.observableArrayList(ribsJFX);
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
        directoryChooser.setInitialDirectory(new File("D:\\TestGB")); // Указываем, какую папку открыть изначально
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


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Заполняем мапу с направлениями и количеством стоянок
     */
    private void setQuantityPointsForEachDirection() {
        // Отчищаем мапу
        this.mapQuantityPointsForEachDirection.clear();
        // Заполняем значениямми
        this.mapQuantityPointsForEachDirection.put((directionOne.getText()), Integer.parseInt(pointOne.getText()));
        this.mapQuantityPointsForEachDirection.put((directionTwo.getText()), Integer.parseInt(pointTwo.getText()));
        if (Integer.parseInt(pointThree.getText()) != 0 && !pointThree.isDisable()) {
            this.mapQuantityPointsForEachDirection.put((directionThree.getText()), Integer.parseInt(pointThree.getText()));
            if (Integer.parseInt(pointFour.getText()) != 0 && !pointFour.isDisable()) {
                this.mapQuantityPointsForEachDirection.put((directionFour.getText()), Integer.parseInt(pointFour.getText()));
            }
        }
    }
}
