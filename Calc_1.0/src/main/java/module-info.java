module org.zeros.calc {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.zeros.calc to javafx.fxml;
    exports org.zeros.calc;
}