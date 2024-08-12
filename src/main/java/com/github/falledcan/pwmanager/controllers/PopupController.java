package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PopupController {

    @FXML
    private Label poptext;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private HBox titleBar;

    @FXML
    public void initialize() {
        poptext.setText(Utils.popText);
        makeStageDraggable();
        cancelButton.setVisible(Utils.cancelButton);
    }

    //Cancelを押した時の処理
    @FXML
    private void onCancelButton(){

    }


    //Okを押した時の処理
    @FXML
    private void onOkButton() {
        Stage stage = (Stage) okButton.getScene().getWindow();
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
