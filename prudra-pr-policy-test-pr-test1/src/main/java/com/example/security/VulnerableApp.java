package com.example.security;

import java.sql.*;
import java.io.*;
import java.util.Objects;

public class VulnerableApp {

    // Hardcoded credentials - SAST issue
    private static final String DB_PASSWORD = Objects.requireNonNull(
        System.getenv("DB_PASSWORD"),
        "DB_PASSWORD environment variable must be set"
    );
    private static final String API_KEY = Objects.requireNonNull(
        System.getenv("API_KEY"),
        "API_KEY environment variable must be set"
    );

    // SQL Injection vulnerability
    public User getUserById(String userId) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        // Vulnerable to SQL injection
        String query = "SELECT * FROM users WHERE id = '" + userId + "'";
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            return new User(rs.getString("name"), rs.getString("email"));
        }
        return null;
    }

    // Command Injection vulnerability
    public String executeCommand(String userInput) throws IOException {
        Runtime runtime = Runtime.getRuntime();

        // Vulnerable to command injection
        Process process = runtime.exec("ls " + userInput);
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream())
        );

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }

    // Path Traversal vulnerability
    public String readFile(String filename) throws IOException {
        // Vulnerable to path traversal
        File file = new File("/app/data/" + filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/testdb",
            "admin",
            DB_PASSWORD
        );
    }

    static class User {
        String name;
        String email;

        User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}
