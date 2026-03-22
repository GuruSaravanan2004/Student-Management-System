package dao;

import java.sql.*;
import utils.DBConnection;

public class StudentDAO {

    // ADD
    public void addStudent(String name, String email, String phone) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO students(name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            ps.executeUpdate();

            System.out.println("Student Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW
    public void getAllStudents() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("student_id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("email") + " " +
                                rs.getString("phone")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateStudent(int id, String name, String email, String phone) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE students SET name=?, email=?, phone=? WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Updated!");
            } else {
                System.out.println("Student Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteStudent(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM students WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Deleted!");
            } else {
                System.out.println("Student Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}