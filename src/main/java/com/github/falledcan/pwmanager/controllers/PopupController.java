package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.DatabaseManager;
import com.github.falledcan.pwmanager.Utils.FxmlUtils;
import com.github.falledcan.pwmanager.Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

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
        Utils.checkVersion = false;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //Okを押した時の処理
    @FXML
    private void onOkButton() {
        if (Utils.cancelButton && !Utils.checkVersion) {
            try {
                DatabaseManager.deleteDate(Utils.id);
                FxmlUtils.showPopUp("データを削除しました。", false);
                FxmlUtils.setList();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        } else if (Utils.checkVersion) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/FalledCan/PwManager/releases/latest"));
                    Utils.checkVersion = false;
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }

        }
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
