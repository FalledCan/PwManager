package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.DatabaseManager;
import com.github.falledcan.pwmanager.Encryption;
import com.github.falledcan.pwmanager.Utils.FxmlUtils;
import com.github.falledcan.pwmanager.Utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class PasswordController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField email;

    @FXML
    private Label email_label;

    @FXML
    private TextField password;

    @FXML
    private Label password_label;

    @FXML
    private TextField service;

    @FXML
    private Label service_label;

    @FXML
    private TextField url;

    @FXML
    private TextField username;

    @FXML
    private Label username_label;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private HBox titleBar;

    @FXML
    private Button deleteButton;

    @FXML
    public void initialize() {
        makeStageDraggable();
        setDefaultText();
        deleteButton.setVisible(Utils.edit);
    }

    private void setDefaultText(){
        if(Utils.edit) {
            service.setText(Utils.service);
            url.setText(Utils.url);
            username.setText(Utils.username);
            email.setText(Utils.email);
            password.setText(Utils.password);
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
    private void onDeleteButton(){
        //削除処理
        try {
            FxmlUtils.showPopUp("本当に削除しますか？",true);
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onCancelButton(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onOkButton(){
        //空白の場合の処理
        int count = 0;
        if(service.getText().isEmpty()){
            service_label.setStyle("-fx-text-fill: red;");
            count++;
        }else {
            service_label.setStyle("-fx-text-fill: black;");
        }
        if(username.getText().isEmpty() && email.getText().isEmpty()){
            username_label.setStyle("-fx-text-fill: red;");
            email_label.setStyle("-fx-text-fill: red;");
            count++;
        }else {
            username_label.setStyle("-fx-text-fill: black;");
            email_label.setStyle("-fx-text-fill: black;");
        }
        if(password.getText().isEmpty()){
            password_label.setStyle("-fx-text-fill: red;");
            count++;
        }else {
            password_label.setStyle("-fx-text-fill: black;");
        }

        if(count == 0){
            try {
                //true:編集false:追加
                String password_ = Encryption.encrypt(password.getText());
                String username_ = Encryption.encrypt(username.getText());
                String email_ = Encryption.encrypt(email.getText());
                if(Utils.edit){
                    //編集
                    DatabaseManager.updataData(Utils.id,service.getText(),url.getText(),username_,email_,password_);
                    FxmlUtils.showPopUp("データを更新しました。",false);
                }else {
                    //追加
                    DatabaseManager.insertData(service.getText(),url.getText(),username_,email_,password_,null);
                    FxmlUtils.showPopUp("登録が完了しました。",false);
                }
                Utils.edit = false;
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
        } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
