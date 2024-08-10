package com.github.falledcan.pwmanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupController {

    @FXML
    private Label poptext;

    @FXML
    private Button popbutton;

    @FXML
    public void initialize() {
        poptext.setText("登録しました。");
    }

    @FXML
    private void closeButton() {
        Stage stage = (Stage) popbutton.getScene().getWindow();
        stage.close();
    }
}
