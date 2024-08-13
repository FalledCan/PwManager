package com.github.falledcan.pwmanager.Utils;

import java.util.ArrayList;

public class Utils {

    //カラム数
    public static int Row_count = 0;

    //passwordリスト管理用
    public static ArrayList<String[]> DataList = new ArrayList<>();

    public static int listCount = 0;

    //passwordEditor
    public static int id;
    public static boolean edit;

    //passwordEditor Defaultテキスト
    public static String service;
    public static String url;
    public static String username;
    public static String email;
    public static String password;

    //ポップアップテキスト
    public static String popText;

    //ポップアップcancelボタンデフォルト:false
    public static boolean cancelButton= false;

}
