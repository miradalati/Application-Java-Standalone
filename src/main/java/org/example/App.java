package org.example;

import java.sql.*;
import java.util.Scanner;

public class App {

    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_demo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "pass123";

    public static void main(String[] args) {
        System.out.println("=== JDBC PostgreSQL Demo ===");
        System.out.println("============================");

        Connection conn = null;
        Scanner sc = new Scanner(System.in);

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL!");

            while (true) {
                System.out.println("\n1) List students");
                System.out.println("2) Add student");
                System.out.println("3) Update student age");
                System.out.println("4) Delete student");
                System.out.println("0) Exit");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        listStudents(conn);
                        break;
                    case 2:
                        addStudent(conn, sc);
                        break;
                    case 3:
                        updateStudentAge(conn, sc);
                        break;
                    case 4:
                        deleteStudent(conn, sc);
                        break;
                    case 0:
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException ignore) {}
            sc.close();
        }
    }

    private static void listStudents(Connection conn) throws SQLException {
        String sql = "SELECT id, name, age FROM students ORDER BY id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- students ---");
        while (rs.next()) {
            System.out.println(
                    "id=" + rs.getInt("id") +
                            " | name=" + rs.getString("name") +
                            " | age=" + rs.getInt("age")
            );
        }

        rs.close();
        ps.close();
    }

    private static void addStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine().trim());

        String sql = "INSERT INTO students(name, age) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);

        int rows = ps.executeUpdate();
        System.out.println("Inserted rows: " + rows);

        ps.close();
    }

    private static void updateStudentAge(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Student ID to update: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("New age: ");
        int newAge = Integer.parseInt(sc.nextLine().trim());

        String sql = "UPDATE students SET age=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, newAge);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();
        System.out.println("Updated rows: " + rows);

        ps.close();
    }

    private static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Student ID to delete: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        System.out.println("Deleted rows: " + rows);

        ps.close();
    }
}

