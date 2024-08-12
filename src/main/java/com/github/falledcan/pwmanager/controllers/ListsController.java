package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.DatabaseManager;
import com.github.falledcan.pwmanager.Encryption;
import com.github.falledcan.pwmanager.Main;
import com.github.falledcan.pwmanager.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ListsController {

    @FXML
    private Button edit;

    @FXML
    private Button memo;

    @FXML
    private Label password;

    @FXML
    private Label service;

    @FXML
    private Label email;

    @FXML
    private Label username;

    @FXML
    public void initialize() {
        try {
            //クリック時にコピー
            String[] list = Utils.DataList.get(Utils.listCount);
            //サービス名
            service.setText(list[1]);
            service.setOnMouseClicked(mouseEvent -> {
                String text = service.getText();
                if(!text.isEmpty())
                    onCopyBoard(text);
            });
            //ユーザー名
            username.setText(Encryption.decrypt(list[2]));
            username.setOnMouseClicked(mouseEvent -> {
                String text = username.getText();
                if(!text.isEmpty())
                    onCopyBoard(text);
            });
            //Eメール
            email.setText(Encryption.decrypt(list[3]));
            email.setOnMouseClicked(mouseEvent -> {
                String text = email.getText();
                if(!text.isEmpty())
                    onCopyBoard(text);
            });
            password.setOnMouseClicked(mouseEvent -> {
                try {
                    //データベースから参照
                    onCopyBoard(DatabaseManager.getPassword(Integer.parseInt(list[0])));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            //Actionボタン
            memo.setOnAction(actionEvent -> {
                onMemo(Integer.parseInt(list[0]));
            });
            edit.setOnAction(actionEvent -> {
                onEdit(Integer.parseInt(list[0]));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Utils.listCount++;
        if(Utils.listCount == Utils.Row_count){
            Utils.DataList.clear();
        }
    }

    //クリップボードにコピー
    private void onCopyBoard(String text){
        Clipboard clipboard = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }

    private void onMemo(int id){
        //めも操作
    }

    private void onEdit(int id){
        //Password編集
    }
}
