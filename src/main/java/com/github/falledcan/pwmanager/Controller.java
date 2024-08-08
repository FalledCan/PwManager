package com.github.falledcan.pwmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Controller {

        @FXML
        private HBox titleBar;

        private double xOffset = 0;
        private double yOffset = 0;

        @FXML
        public void initialize() {
                makeStageDraggable();
        }

        private void makeStageDraggable() {
                titleBar.setOnMousePressed(event -> {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                });

                titleBar.setOnMouseDragged(event -> {
                        Stage stage = (Stage) titleBar.getScene().getWindow();
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                });
        }

}
