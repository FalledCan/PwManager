package com.github.falledcan.pwmanager;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        // ユーザーのローカルアプリケーションデータディレクトリを取得
        String userHome = System.getProperty("user.home");
        Path localAppDataPath = Paths.get(userHome, "AppData", "Local", "PasswordManager");

        try {
            // ディレクトリが存在しない場合は作成
            if (!Files.exists(localAppDataPath)) {
                Files.createDirectories(localAppDataPath);
            }

            Path filePath = localAppDataPath.resolve("pw.db");
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }
            filePath = localAppDataPath.resolve("key.json");
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
