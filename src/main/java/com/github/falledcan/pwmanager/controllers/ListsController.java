package com.github.falledcan.pwmanager.controllers;

import com.github.falledcan.pwmanager.DatabaseManager;
import com.github.falledcan.pwmanager.Encryption;
import com.github.falledcan.pwmanager.Main;
import com.github.falledcan.pwmanager.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

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
            String[] list = Main.DataList.get(Utils.listCount);
            service.setText(list[1]);
            service.setOnMouseClicked(mouseEvent -> {
                String text = service.getText();
                if(!text.isEmpty())
                    onCopyBoard(text);
            });
            username.setText(Encryption.decrypt(list[2]));
            username.setOnMouseClicked(mouseEvent -> {
                String text = username.getText();
                if(!text.isEmpty())
                    onCopyBoard(text);
            });
            email.setText(Encryption.decrypt(list[3]));
            email.setOnMouseClicked(mouseEvent -> {
                String text = email.getText();
                if(!text.isEmpty())
                    onCopyBoard(text);
            });
            password.setOnMouseClicked(mouseEvent -> {
                try {
                    onCopyBoard(DatabaseManager.getPassword(Integer.parseInt(list[0])));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Utils.listCount++;
        if(Utils.listCount == Main.Row_count){
            Main.DataList.clear();
        }
    }

    private void onCopyBoard(String text){
        Clipboard clipboard = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }
}
