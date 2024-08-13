package com.github.falledcan.pwmanager.Utils;

import com.github.falledcan.pwmanager.DatabaseManager;
import com.github.falledcan.pwmanager.Encryption;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class FxmlUtils {

    public static void setDefaultTextPE(String[] data){

        try {
            Utils.service = data[0];
            Utils.url = data[1];
            Utils.username = Encryption.decrypt(data[2]);
            Utils.email = Encryption.decrypt(data[3]);
            Utils.password = Encryption.decrypt(data[4]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void showPasswordEditor(boolean edit_mode) throws IOException {

        Utils.edit = edit_mode;

        FXMLLoader fxmlLoader = new FXMLLoader(FxmlUtils.class.getResource("/com/github/falledcan/pwmanager/password-add.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("PasswordEditor");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void showPopUp(String text,boolean cancel_button) throws IOException {

        Utils.popText = text;
        Utils.cancelButton = cancel_button;

        FXMLLoader fxmlLoader = new FXMLLoader(FxmlUtils.class.getResource("/com/github/falledcan/pwmanager/popup.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("PoP");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void setList(){
        try {
            Utils.Row_count = DatabaseManager.getRowCount();
            if(Utils.Row_count != 0){
                Utils.listBox.getChildren().clear();
                Utils.DataList = DatabaseManager.getAllData();
                Utils.listCount = 0;
                for(int i = 0;i<Utils.Row_count;i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader(FxmlUtils.class.getResource("/com/github/falledcan/pwmanager/lists.fxml"));
                    HBox hBox = fxmlLoader.load();
                    Utils.listBox.getChildren().add(hBox);
                }
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
