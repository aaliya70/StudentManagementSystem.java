import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root"; 
    static final String PASSWORD = "your_password"; 

    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to Database!");

            while (true) {
                System.out.println("\n=== Student Management System ===");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> updateStudent();
                    case 4 -> deleteStudent();
                    case 5 -> { conn.close(); System.exit(0); }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addStudent() throws SQLException {
        sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter grade: ");
        String grade = sc.nextLine();

        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO students(name, age, grade) VALUES (?, ?, ?)"
        );
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, grade);
        stmt.executeUpdate();
        System.out.println("Student added successfully!");
    }

    static void viewStudents() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
        while (rs.next()) {
            System.out.printf("%d | %s | %d | %s%n",
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("grade"));
        }
    }

    static void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new grade: ");
        String grade = sc.nextLine();

        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE students SET name=?, age=?, grade=? WHERE id=?"
        );
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, grade);
        stmt.setInt(4, id);

        if (stmt.executeUpdate() > 0) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student ID not found!");
        }
    }

    static void deleteStudent() throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE id=?");
        stmt.setInt(1, id);

        if (stmt.executeUpdate() > 0) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student ID not found!");
        }
    }
}
