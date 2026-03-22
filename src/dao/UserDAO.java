package dao;

import java.sql.*;
import utils.DBConnection;

public class UserDAO {

    public String login(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role"); // ADMIN or STUDENT
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // login failed
    }
    public String loginStudent(int studentId, String password) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, String.valueOf(studentId));
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}