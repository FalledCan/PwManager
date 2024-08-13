package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.DatabaseManager;
import com.github.falledcan.pwmanager.Encryption;
import com.github.falledcan.pwmanager.Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MemoController {

    @FXML
    private Button close;

    @FXML
    private HBox titleBar;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextArea textarea;

    @FXML
    public void initialize() {
        makeStageDraggable();
        try {
            textarea.setText(Encryption.decrypt(DatabaseManager.getData(Utils.id)[5]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    @FXML
    private void onCloseButton() {
        try {
            DatabaseManager.updataMemo(Utils.id,Encryption.encrypt(textarea.getText()));
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
