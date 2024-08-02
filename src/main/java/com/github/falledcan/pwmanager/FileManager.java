package com.github.falledcan.pwmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static void createFiles() {
        // ユーザーのローカルアプリケーションデータディレクトリを取得
        String userHome = System.getProperty("user.home");
        Path localAppDataPath = Paths.get(userHome, "AppData", "Local", "PasswordManager");
        try {
            // ディレクトリが存在しない場合は作成
            if (!Files.exists(localAppDataPath)) {
                Files.createDirectories(localAppDataPath);
            }
            //データベースファイル作成
            Path filePath = localAppDataPath.resolve("pw.db");
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }
            //暗号キーファイル作成
            filePath = localAppDataPath.resolve("key.json");
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jsonData_key(){
        String userHome = System.getProperty("user.home");
        Path localAppDataPath = Paths.get(userHome, "AppData", "Local", "PasswordManager");
        Path filePath = localAppDataPath.resolve("key.json");
        //json読み込み
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // JSONファイルの読み込み
        KeyData keyData = readJsonFile(gson, filePath);

        if (keyData == null) {
            // データがない場合、新しいデータを書き込む
            keyData = new KeyData();
            keyData.setKey("default_key");
            writeJsonFile(gson, filePath, keyData);
        } else {
            // 読み込んだデータを表示
            System.out.println("Key: " + keyData.getKey());
        }
    }

    private static KeyData readJsonFile(Gson gson, Path filePath) {
        try (FileReader reader = new FileReader(filePath.toFile())) {
            return gson.fromJson(reader, KeyData.class);
        } catch (IOException e) {
            System.out.println("ファイルが存在しないか、読み込めませんでした: " + e.getMessage());
            return null;
        }
    }

    private static void writeJsonFile(Gson gson, Path filePath, KeyData keyData) {
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            gson.toJson(keyData, writer);
        } catch (IOException e) {
            System.out.println("ファイルに書き込めませんでした: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        jsonData_key();
    }
    // キーデータのクラス
    public static class KeyData {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}

