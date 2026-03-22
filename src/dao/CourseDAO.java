package dao;

import java.sql.*;
import utils.DBConnection;

public class CourseDAO {

    public void addCourse(String courseName) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO courses(course_name) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, courseName);

            ps.executeUpdate();

            System.out.println("Course Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}