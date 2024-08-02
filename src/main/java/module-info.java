module com.github.falledcan.pwmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires com.google.gson;

    opens com.github.falledcan.pwmanager to javafx.fxml;
    exports com.github.falledcan.pwmanager;

}