package com.github.falledcan.pwmanager;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class Test {
    private static Statement stmt = null;
    public static void main(String[] ars){
        loadDB();
    }

    public static void loadDB(){

        String userHome = System.getProperty("user.home");
        Path localAppDataPath = Paths.get(userHome, "AppData", "Local", "PasswordManager");

        System.out.println(localAppDataPath +"\\pw.db");
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + localAppDataPath + "\\pw.db");
            stmt = conn.createStatement();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
