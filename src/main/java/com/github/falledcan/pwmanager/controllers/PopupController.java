package com.github.falledcan.pwmanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PopupController {

    @FXML
    private Label poptext;

    @FXML
    private Button popbutton;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private HBox titleBar;

    @FXML
    public void initialize() {
        poptext.setText("登録しました。");
        makeStageDraggable();
    }

    @FXML
    private void closeButton() {
        Stage stage = (Stage) popbutton.getScene().getWindow();
        stage.close();
    }
    //カーソルで移動
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
