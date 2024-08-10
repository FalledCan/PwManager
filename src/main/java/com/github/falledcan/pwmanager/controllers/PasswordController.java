package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.DatabaseManager;
import com.github.falledcan.pwmanager.Encryption;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class PasswordController {

    public static boolean edit = false;

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
    public void initialize() {
        makeStageDraggable();
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
    private void cancelButton(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void okButton(){
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
                if(edit){

                }else {
                    String password_ = Encryption.encrypt(password.getText());
                    String username_ = Encryption.encrypt(username.getText());
                    String email_ = Encryption.encrypt(email.getText());
                    DatabaseManager.insertData(service.getText(),url.getText(),username_,email_,password_,null);
                }
                edit = false;
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
        } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
