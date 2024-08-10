package com.github.falledcan.pwmanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

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
        username.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + rand.nextInt(100));

        Tooltip tooltip = new Tooltip("Memo");
        Tooltip.install(memo, tooltip);

        tooltip = new Tooltip("Edit");
        tooltip.install(edit, tooltip);
    }
}
