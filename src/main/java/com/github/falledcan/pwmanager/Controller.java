package com.github.falledcan.pwmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
                        for(int i = 0; i< 400; i++) {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lists.fxml"));
                                HBox hBox = fxmlLoader.load();
                                Button button_meno = (Button) hBox.lookup("#memo");
                                int finalI = i;
                                //ボタンにonActionを追加する
                                button_meno.setOnAction(actionEvent -> {
                                        onClickButton(finalI);
                                });
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
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("password-add.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage addStage = new Stage();
                    addStage.initStyle(StageStyle.UNDECORATED);
                    addStage.setTitle("PasswordEditor");
                    addStage.initModality(Modality.APPLICATION_MODAL);
                    addStage.setScene(new Scene(root));
                    addStage.showAndWait();


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
