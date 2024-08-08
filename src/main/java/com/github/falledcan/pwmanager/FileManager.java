package com.github.falledcan.pwmanager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            //暗号キーファイル作成
            filePath = localAppDataPath.resolve("key.json");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //key取得
    public static String jsonData_key() throws Exception {
        String userHome = System.getProperty("user.home");
        Path localAppDataPath = Paths.get(userHome, "AppData", "Local", "PasswordManager");
        Path filePath = localAppDataPath.resolve("key.json");
        ObjectMapper objectMapper = new ObjectMapper();
        //ファイルの内容を読み込む
        String jsonContent = new String(Files.readAllBytes(filePath));
        //ファイル内にkeyが存在するか確認し無ければ新しく生成する
        JsonNode jsonNode = objectMapper.readTree(jsonContent);
        if(!jsonNode.has("key")){
            jsonContent = "{\"key\": \"" + Encryption.generateRandomAESKey() + "\"}";
            // ファイルに書き込む
            Files.write(filePath, jsonContent.getBytes(), StandardOpenOption.CREATE);
        }
        //json読み込み
        KeyJson keyJson = objectMapper.readValue(jsonContent,KeyJson.class);
        return keyJson.getKey();
    }
}