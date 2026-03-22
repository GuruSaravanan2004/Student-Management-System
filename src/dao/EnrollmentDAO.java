package dao;

import java.sql.*;
import utils.DBConnection;

public class EnrollmentDAO {

    public void enrollStudent(int studentId, int courseId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT IGNORE INTO enrollments(student_id, course_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ps.executeUpdate();

            System.out.println("Student Enrolled!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getStudentCourses(int studentId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT c.course_name FROM courses c " +
                    "JOIN enrollments e ON c.course_id = e.course_id " +
                    "WHERE e.student_id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println("Courses for Student ID: " + studentId);

            while (rs.next()) {
                System.out.println(rs.getString("course_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}