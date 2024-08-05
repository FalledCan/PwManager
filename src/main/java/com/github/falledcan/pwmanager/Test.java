package com.github.falledcan.pwmanager;

import java.sql.Statement;

public class Test {

    public static void main(String[] ars) throws Exception {
        fast_load();

        System.out.println(Encryption.isCheckDataBase());
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


    }

}
