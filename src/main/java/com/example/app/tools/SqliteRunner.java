package com.example.app.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteRunner {
    public static void main(String[] args) throws Exception {
        // Path to project module (run from project root or module root)
        String modulePath = System.getProperty("user.dir");
        // If executed from repository root, adjust to javafx-desktop-skeleton
        File schemaFile = Paths.get(modulePath, "src", "main", "resources", "sql", "schema_sqlite.sql").toFile();
        if (!schemaFile.exists()) {
            // try module subfolder
            schemaFile = Paths
                    .get(modulePath, "javafx-desktop-skeleton", "src", "main", "resources", "sql", "schema_sqlite.sql")
                    .toFile();
        }
        if (!schemaFile.exists()) {
            System.err.println("schema_sqlite.sql not found. Looked at: " + schemaFile.getAbsolutePath());
            System.exit(2);
        }

        // Create/open app.db next to module (javafx-desktop-skeleton/app.db)
        File dbFile = Paths.get(modulePath, "app.db").toFile();
        if (!dbFile.exists()) {
            // try module subfolder
            dbFile = Paths.get(modulePath, "javafx-desktop-skeleton", "app.db").toFile();
        }

        String dbUrl = "jdbc:sqlite:" + dbFile.getAbsolutePath();
        System.out.println("Using DB: " + dbUrl);

        // Read SQL file
        StringBuilder sql = new StringBuilder();
        try (BufferedReader r = new BufferedReader(new FileReader(schemaFile))) {
            String line;
            while ((line = r.readLine()) != null) {
                sql.append(line).append('\n');
            }
        }

        // Split statements by semicolon; this is simple but OK for our generated schema
        String[] statements = sql.toString().split(";\\s*\\n");

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            conn.setAutoCommit(false);
            try (Statement st = conn.createStatement()) {
                for (String s : statements) {
                    String stmt = s.trim();
                    if (stmt.isEmpty())
                        continue;
                    try {
                        st.execute(stmt);
                    } catch (Exception ex) {
                        // print and continue
                        System.err.println("Failed to execute statement: " + stmt);
                        ex.printStackTrace(System.err);
                    }
                }
                conn.commit();
            }

            // Simple verification queries
            try (Statement st2 = conn.createStatement()) {
                System.out.println("\\n---- document_type ----");
                try (ResultSet rs = st2.executeQuery("SELECT id, code, name FROM document_type LIMIT 20")) {
                    while (rs.next())
                        System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
                }

                System.out.println("\\n---- gender ----");
                try (ResultSet rs = st2.executeQuery("SELECT id, code, name FROM gender LIMIT 20")) {
                    while (rs.next())
                        System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
                }

                System.out.println("\\n---- education_level ----");
                try (ResultSet rs = st2.executeQuery("SELECT id, code, name FROM education_level LIMIT 50")) {
                    while (rs.next())
                        System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
                }
            }
        }

        System.out.println("\\nSchema execution complete.");
    }
}
