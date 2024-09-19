package vn.hcmute.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String USERNAME = "root";
    private static String PASSWORD = "0799722822";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/ltweb";

    public static Connection getDatabaseConnection() throws ClassNotFoundException {
        Class.forName(DRIVER);
        try {
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Test connection
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            new DBConnection();
            System.out.printf(DBConnection.getDatabaseConnection().toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}