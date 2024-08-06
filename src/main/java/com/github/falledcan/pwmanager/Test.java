package com.github.falledcan.pwmanager;

import java.sql.Statement;
import java.util.ArrayList;

public class Test {

    public static void main(String[] ars) throws Exception {
        fast_load();

        System.out.println(Encryption.isCheckDataBase());
        //System.out.println(DatabaseManager.insertData("aaa",null,"Beercna","adfadsdf",null));
        //System.out.println(DatabaseManager.deleteDate(10));
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

        if(!Encryption.isCheckDataBase()){
            System.exit(0);
        }

        int Row_count = DatabaseManager.getRowCount();
        if(Row_count != 0){

            ArrayList<String[]> list = DatabaseManager.getAllData();
            for(String[] datas: list){
                System.out.println(datas[0]);
                System.out.println(datas[1]);
                System.out.println(datas[2]);
                System.out.println(datas[3]);
                System.out.println(datas[4]);

            }

        }
        System.out.println(Row_count);
    }

}
