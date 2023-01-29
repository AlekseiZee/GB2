module geobuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.geobuilder_2 to javafx.fxml;
    exports ru.geobuilder_2;
}