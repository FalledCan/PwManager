package com.github.falledcan.pwmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;


public class PasswordController {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField service;

    @FXML
    private TextField url;

    @FXML
    private TextField username;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        email.setText("aaaaa");
    }

    @FXML
    private void cancelButton(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void okButton(){
        System.out.println(email.getText());
        if(Objects.equals(url.getText(), "")){//空白の場合の処理
            System.out.println("null");
        }
    }
}
