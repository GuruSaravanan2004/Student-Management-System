package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sms",  // change port if needed
                    "root",
                    "root123"
            );
            //System.out.println("Connected to Database!");
        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
        return con;
    }
}
