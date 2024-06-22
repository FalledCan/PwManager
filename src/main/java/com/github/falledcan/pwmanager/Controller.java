package com.github.falledcan.pwmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

        @FXML
        private Label welcomeText;

        @FXML
        private Button myButton;

        @FXML
        protected void onHelloButtonClick() {
            welcomeText.setText("Welcome to JavaFX Application!");
        }

}
