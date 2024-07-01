package com.github.falledcan.pwmanager;

import javax.swing.plaf.nimbus.State;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

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
        String sql = "create table if not exists list(id integer primary key,name not NULL,"
                + "url text,username text not NULL,password text not NULL,memo text);";
        Statement stmt = conn.createStatement();

        stmt.execute(sql);
    }

    //データの挿入
    public void insertData(String name, String url,String userName, String password, String memo) throws SQLException {

        String sql = "insert into list(name, url, username, password, memo) values(?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,name);
        pstmt.setString(2,url);
        pstmt.setString(3,userName);
        pstmt.setString(4,password);
        pstmt.setString(5,memo);

        pstmt.executeUpdate();
        pstmt.close();
    }

    //データの更新
    public void updataData(int id, String nName, String nUrl,String nUserName, String nPassword, String nMemo) throws SQLException {

        String sql = "update list set name = ?, url = ?, username = ?, password = ?, memo = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,nName);
        pstmt.setString(2,nUrl);
        pstmt.setString(3,nUserName);
        pstmt.setString(4,nPassword);
        pstmt.setString(5,nMemo);
        pstmt.setInt(6,id);

        pstmt.executeUpdate();
        pstmt.close();
    }

    //データの削除
    public void deleteDate(int id) throws SQLException {
        String sql = "delete from list where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1,id);

        pstmt.executeUpdate();
        pstmt.close();
    }

    //データの取得
    public String[] getData(int id) throws SQLException {
        String sql = "SELECT name, url, username, password, memo FROM list WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();

        String[] DataList = new String[5];
        if(rs.next()){
            DataList[0] = rs.getString("name");
            DataList[1] = rs.getString("url");
            DataList[2] = rs.getString("username");
            DataList[3] = rs.getString("password");
            DataList[4] = rs.getString("memo");
        }

        rs.close();
        pstmt.close();

        return DataList;
    }

    //データサーチ
    public ArrayList searchData(String name) throws SQLException {
        String sql = "SELECT id, name, url, password, memo FROM your_table_name WHERE name = ? ORDER BY id";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<String[]> list = new ArrayList<>();
        while (rs.next()) {
            String[] DataList = new String[6];
            DataList[0] = String.valueOf(rs.getInt("id"));
            DataList[1] = rs.getString("name");
            DataList[2] = rs.getString("url");
            DataList[3] = rs.getString("username");
            DataList[4] = rs.getString("password");
            DataList[5] = rs.getString("memo");
            list.add(DataList);
        }
        return list;
    }
    //データベースを閉じる
    public void close() throws SQLException {

        if(conn != null){
            conn.close();
        }
    }
}
