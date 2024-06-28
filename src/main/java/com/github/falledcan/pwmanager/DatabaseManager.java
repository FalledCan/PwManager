package com.github.falledcan.pwmanager;

import javax.swing.plaf.nimbus.State;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseManager {

    private  Connection conn = null;

    //データベースロード
    public void loadDB(){

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

    //テーブル作成
    public void createTable() throws SQLException {
        String sql = "create table if not exists list(id integer primary key,name not NULL,url text,password text not NULL,memo text);";
        Statement stmt = conn.createStatement();

        stmt.execute(sql);
    }

    //データの挿入
    public void insertData(String name, String url, String password, String memo) throws SQLException {

        String sql = "insert into list(name, url, password, memo) values(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,name);
        pstmt.setString(2,url);
        pstmt.setString(3,password);
        pstmt.setString(4,memo);

        pstmt.executeUpdate();
    }

    //データの更新
    public void updataData(int id, String nName, String nUrl, String nPassword, String nMemo) throws SQLException {

        String sql = "update list set name = ?, url = ?, password = ?, memo = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,nName);
        pstmt.setString(2,nUrl);
        pstmt.setString(3,nPassword);
        pstmt.setString(4,nMemo);
        pstmt.setInt(5,id);

        pstmt.executeUpdate();

    }

    //データの削除
    public void deleteDAte(int id) throws SQLException {
        String sql = "delete from list where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1,id);

        pstmt.executeUpdate();
    }

    //データベースを閉じる
    public void close() throws SQLException {

        if(conn != null){
            conn.close();
        }
    }
}
