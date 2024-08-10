module com.github.falledcan.pwmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    requires com.fasterxml.jackson.databind;

    opens com.github.falledcan.pwmanager to javafx.fxml;
    exports com.github.falledcan.pwmanager;
    exports com.github.falledcan.pwmanager.controllers;
    opens com.github.falledcan.pwmanager.controllers to javafx.fxml;


}