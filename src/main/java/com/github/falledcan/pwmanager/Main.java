package com.github.falledcan.pwmanager;

import com.github.falledcan.pwmanager.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    static int Row_count = 0;
    static ArrayList<String[]> DataList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/github/falledcan/pwmanager/main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PasswordManager");
        Controller controller = new Controller();
        stage.setScene(scene);
        stage.show();
        controller.setStage(stage);
    }

    public static void main(String[] args) throws Exception {
        fast_load();
        launch();
    }

    //初期データロード
    private static void fast_load() throws Exception {
        //各ファイルの生成
        FileManager.createFiles();
        //データベースロード
        DatabaseManager.loadDB();
        DatabaseManager.createTable();
        //暗号化キーロード
        Encryption.loadKey();

        if(!Encryption.isCheckDataBase()){
            System.exit(0);
        }

        Row_count = DatabaseManager.getRowCount();
        if(Row_count != 0){
            DataList = DatabaseManager.getAllData();
        }
    }
}