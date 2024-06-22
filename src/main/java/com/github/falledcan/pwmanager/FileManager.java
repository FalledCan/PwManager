package com.github.falledcan.pwmanager;

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
}
