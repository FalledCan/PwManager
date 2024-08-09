package com.github.falledcan.pwmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
                        for(int i = 0; i< 3; i++) {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lists.fxml"));
                                System.out.println("a");
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
        private void testB(){
                System.out.println("aaa");
        }

        @FXML
        private void handleClose(){
                if (stage != null) {
                        stage.close();
                }
        }

}
