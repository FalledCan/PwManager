package com.github.falledcan.pwmanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    private static Connection conn = null;

    //データベースロード
    public static void loadDB(){

        String userHome = System.getProperty("user.home");
        Path localAppDataPath = Paths.get(userHome, "AppData", "Local", "PasswordManager");
        String dbUrl = "jdbc:sqlite:" + localAppDataPath.resolve("pw.db");
        try {
            Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection(dbUrl);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Database: list
     * - id
     * - name : service name is not null
     * - url : service url
     * - username : service username is not null
     * - email : E-mail is not null
     * - password : Password is not null
     * - memo
     */

    //テーブル作成
    public static void createTable() throws SQLException {
        String sql_1 = "create table if not exists list(id integer primary key,name not NULL,"
                + "url text,username text,email text,password text not NULL,memo text);";
        String sql_2 = "create table if not exists key(num integer,check_key text not NULL);";
        Statement stmt = conn.createStatement();

        stmt.execute(sql_1);
        stmt.execute(sql_2);
        stmt.close();
    }

    //key
    public static String dataKey() throws Exception {
        String sql = "select check_key from key where num = 1";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String getKey;
        if(rs.next()){
            getKey = rs.getString("check_key");
        }else {
            String checkKey = "ajS932FdkmL49De5vkG";
            sql = "insert into key(num, check_key) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,1);
            getKey = Encryption.encrypt(checkKey);
            pstmt.setString(2,getKey);
            pstmt.executeUpdate();
            pstmt.close();
        }
        return getKey;
    }

    //行数取得
    public static int getRowCount() throws SQLException {
        String sql = "select count(*) as count from list";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        int count = 0;
        if(rs.next()){
            count = rs.getInt("count");
        }
        rs.close();
        stmt.close();
        return count;
    }

    //データの挿入
    public static void insertData(String name, String url,String userName, String email, String password, String memo) throws SQLException {
        String sql = "insert into list(name, url, username, email, password, memo) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,name);
        pstmt.setString(2,url);
        pstmt.setString(3,userName);
        pstmt.setString(4,email);
        pstmt.setString(5,password);
        pstmt.setString(6,memo);

        pstmt.executeUpdate();
        pstmt.close();

    }

    //データの更新
    public static void updataData(int id, String nName, String nUrl,String nUserName, String nEmail, String nPassword) throws SQLException {

        String sql = "update list set name = ?, url = ?, username = ?,email = ? ,password = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,nName);
        pstmt.setString(2,nUrl);
        pstmt.setString(3,nUserName);
        pstmt.setString(4,nEmail);
        pstmt.setString(5,nPassword);
        pstmt.setInt(6,id);

        pstmt.executeUpdate();
        pstmt.close();
    }

    public static void updataMemo(int id, String nMemo) throws SQLException {

        String sql = "update list set memo = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,nMemo);
        pstmt.setInt(2,id);

        pstmt.executeUpdate();
        pstmt.close();
    }

    //データの削除
    public static boolean deleteDate(Integer id) throws SQLException {
        if(id == null)
            return false;
        String sql = "delete from list where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1,id);

        pstmt.executeUpdate();
        pstmt.close();
        return true;
    }

    //Password取得
    public static String getPassword(int id) throws Exception {
        String sql = "select password from list where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        String pw = rs.getString("password");
        rs.close();
        pstmt.close();
        return Encryption.decrypt(pw);
    }

    //id指定データの取得
    public static String[] getData(int id) throws SQLException {
        String sql = "select name, url, username, email, password, memo from list where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();

        String[] DataList = new String[6];
        if(rs.next()){
            DataList[0] = rs.getString("name");
            DataList[1] = rs.getString("url");
            DataList[2] = rs.getString("username");
            DataList[3] = rs.getString("email");
            DataList[4] = rs.getString("password");
            DataList[5] = rs.getString("memo");
        }

        rs.close();
        pstmt.close();

        return DataList;
    }

    //全てのデータ取得
    public static ArrayList<String[]> getAllData() throws SQLException {
        String sql = "select id, name, username, email from list";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<String[]> list = new ArrayList<>();
        while (rs.next()) {
            String[] DataList = new String[4];
            DataList[0] = String.valueOf(rs.getInt("id"));
            DataList[1] = rs.getString("name");
            DataList[2] = rs.getString("username");
            DataList[3] = rs.getString("email");
            list.add(DataList);
        }
        rs.close();
        stmt.close();
        return list;
    }

    //データサーチ
    public static ArrayList<String[]> searchData(String name) throws SQLException {
        String sql = "SELECT id, name, url, username, email, password, memo from list where name = ? order by id";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<String[]> list = new ArrayList<>();
        while (rs.next()) {
            String[] DataList = new String[7];
            DataList[0] = String.valueOf(rs.getInt("id"));
            DataList[1] = rs.getString("name");
            DataList[2] = rs.getString("url");
            DataList[3] = rs.getString("username");
            DataList[4] = rs.getString("email");
            DataList[5] = rs.getString("password");
            DataList[6] = rs.getString("memo");
            list.add(DataList);
        }
        rs.close();
        pstmt.close();
        return list;
    }
    //データベースを閉じる
    public static void close() throws SQLException {

        if(conn != null){
            conn.close();
        }
    }
}
