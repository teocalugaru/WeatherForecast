module ro.mta.se.lab {
    requires javafx.controls;
    requires java.net.http;
    requires org.json;
    requires javafx.fxml;
    opens ro.mta.se.lab.controller to javafx.fxml;
    exports ro.mta.se.lab;
}