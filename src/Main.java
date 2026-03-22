import java.util.Scanner;
import dao.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();

        // 🔐 LOGIN INPUT
        System.out.print("Enter Username: ");
        String username = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        String role = userDAO.login(username, password);



        // ❌ If login fails
        if (role == null) {
            System.out.println("Invalid Login! Exiting...");
            return;
        }

        System.out.println("Login Success! Role: " + role);


        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        GradeDAO gradeDAO = new GradeDAO();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. Enroll Student");
            System.out.println("5. View Grades");
            System.out.println("6. Add Grade");
            System.out.println("7. UpdateStudent");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.next();
                    System.out.print("Enter email: ");
                    String email = sc.next();
                    System.out.print("Enter phone: ");
                    String phone = sc.next();

                    studentDAO.addStudent(name, email, phone);
                    break;

                case 2:
                    studentDAO.getAllStudents();
                    break;

                case 3:
                    System.out.print("Enter course name: ");
                    String course = sc.next();

                    courseDAO.addCourse(course);
                    break;

                case 4:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Course ID: ");
                    int cid = sc.nextInt();

                    enrollmentDAO.enrollStudent(sid, cid);
                    break;

                case 5:
                    System.out.print("Student ID: ");
                    int id = sc.nextInt();

                    gradeDAO.getGrades(id);
                    gradeDAO.calculateGPA(id);
                    break;

                case 6:
                    System.out.print("Student ID: ");
                    int sid2 = sc.nextInt();

                    System.out.print("Course ID: ");
                    int cid2 = sc.nextInt();

                    System.out.print("Marks: ");
                    int marks = sc.nextInt();

                    gradeDAO.addGrade(sid2, cid2, marks);
                    break;

                case 7:
                    if (role.equals("ADMIN")) {

                        System.out.print("Enter Student ID to update: ");
                        int uid = sc.nextInt();

                        System.out.print("Enter new name: ");
                        String newName = sc.next();

                        System.out.print("Enter new email: ");
                        String newEmail = sc.next();

                        System.out.print("Enter new phone: ");
                        String newPhone = sc.next();

                        studentDAO.updateStudent(uid, newName, newEmail, newPhone);

                    } else {
                        System.out.println("Access Denied!");
                    }
                    break;



                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}