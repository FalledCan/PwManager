package com.github.falledcan.pwmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class ListsController {

    private int s = 0;
    @FXML
    private Button edit;

    @FXML
    private Button memo;

    @FXML
    private Label password;

    @FXML
    private Label service;

    @FXML
    private Label username;

    @FXML
    public void initialize() {
        Random rand = new Random();
        username.setText("aa" + rand.nextInt(100));
    }
}
