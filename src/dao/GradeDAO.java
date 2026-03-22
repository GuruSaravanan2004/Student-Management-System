package dao;

import java.sql.*;
import utils.DBConnection;

public class GradeDAO {

    public void addGrade(int studentId, int courseId, int marks) {
        try {
            Connection con = DBConnection.getConnection();

            String grade;

            if (marks >= 90) grade = "A";
            else if (marks >= 75) grade = "B";
            else if (marks >= 50) grade = "C";
            else grade = "F";

            String query = "INSERT INTO grades(student_id, course_id, marks, grade) VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE marks=VALUES(marks), grade=VALUES(grade)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setInt(3, marks);
            ps.setString(4, grade);

            ps.executeUpdate();

            System.out.println("Grade Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ CLOSE METHOD PROPERLY
    public void getGrades(int studentId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT c.course_name, g.marks, g.grade " +
                    "FROM grades g " +
                    "JOIN courses c ON g.course_id = c.course_id " +
                    "WHERE g.student_id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println("Grades for Student ID: " + studentId);

            while (rs.next()) {
                System.out.println(
                        rs.getString("course_name") + " - " +
                                rs.getInt("marks") + " - " +
                                rs.getString("grade")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    } // ✅ METHOD ENDS HERE

    // ✅ NOW THIS IS OUTSIDE (CORRECT)
    public void calculateGPA(int studentId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT grade FROM grades WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            double totalPoints = 0;
            int count = 0;

            while (rs.next()) {
                String grade = rs.getString("grade");

                switch (grade) {
                    case "A": totalPoints += 4; break;
                    case "B": totalPoints += 3; break;
                    case "C": totalPoints += 2; break;
                    default: totalPoints += 0;
                }
                count++;
            }

            double gpa = (count > 0) ? totalPoints / count : 0;

            System.out.println("GPA: " + gpa);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}