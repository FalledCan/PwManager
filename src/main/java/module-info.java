module com.github.falledcan.pwmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.github.falledcan.pwmanager to javafx.fxml;
    exports com.github.falledcan.pwmanager;
}