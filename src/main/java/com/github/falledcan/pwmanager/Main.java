package com.github.falledcan.pwmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {

    static int Row_count = 0;
    static ArrayList<String[]> DataList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        fast_load();
        launch();
    }

    //初期データロード
    private static void fast_load() throws SQLException {
        FileManager.createFiles();
        DatabaseManager.loadDB();
        DatabaseManager.createTable();
        Row_count = DatabaseManager.getRowCount();
        if(Row_count != 0){
            DataList = DatabaseManager.getAllData();
        }
    }
}