package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.Utils.FxmlUtils;
import com.github.falledcan.pwmanager.Utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

        private double xOffset = 0;
        private double yOffset = 0;

        private static Stage stage;

        public void setStage(Stage stage) {
                Controller.stage = stage;
        }

        @FXML
        private HBox titleBar;

        @FXML
        private VBox listBox;

        @FXML
        private Button addPw;


        @FXML
        public void initialize() {



                try {
                        //Listを並べる処理
                        for(int i = 0; i< Utils.Row_count; i++) {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/github/falledcan/pwmanager/lists.fxml"));
                                HBox hBox = fxmlLoader.load();
                                listBox.getChildren().add(hBox);
                        }
                }catch (Exception e){
                        e.printStackTrace();
                }

                makeStageDraggable();
                Tooltip tooltip = new Tooltip("add Password");
                Tooltip.install(addPw, tooltip);
        }

        private void onClickButton(int i) {

                System.out.println(i);
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

        //password追加ボタンを押した時の処理
        @FXML
        private void addPwButton(){
            try {
                FxmlUtils.showPasswordEditor(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //閉じるボタン処理
        @FXML
        private void handleClose(){
                if (stage != null) {
                        stage.close();
                }
        }

        //最小化ボタン処理
        @FXML
        private void minimizeButton(){
                if(stage != null){
                        stage.setIconified(true);
                }
        }

}
