package com.example.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.io.InputStream;

public class Database {
    private static String sqliteUrl() {
        return "jdbc:sqlite:" + getProperty("sqlite.file", "app.db");
    }

    private static Properties loadAppProperties() {
        Properties p = new Properties();
        try (InputStream in = Database.class.getResourceAsStream("/application.properties")) {
            if (in != null)
                p.load(in);
        } catch (Exception e) {
            // ignore, use defaults
        }
        return p;
    }

    private static String getProperty(String key, String defaultValue) {
        try {
            Properties p = loadAppProperties();
            return p.getProperty(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Connection getConnection() throws SQLException {
        String type = getProperty("db.type", "sqlite");
        if ("mysql".equalsIgnoreCase(type)) {
            String host = getProperty("mysql.host", "localhost");
            String port = getProperty("mysql.port", "3306");
            String db = getProperty("mysql.database", "RHIUdigital");
            String params = getProperty("mysql.params",
                    "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
            String user = getProperty("mysql.user", "root");
            String pass = getProperty("mysql.password", "");
            String url = String.format("jdbc:mysql://%s:%s/%s%s", host, port, db, params);
            return DriverManager.getConnection(url, user, pass);
        }
        return DriverManager.getConnection(sqliteUrl());
    }

    public static void init() {
        String type = getProperty("db.type", "sqlite");
        try {
            if ("mysql".equalsIgnoreCase(type)) {
                initMySql();
            } else {
                // default: sqlite
                try (Connection conn = DriverManager.getConnection(sqliteUrl())) {
                    executeSqlScript(conn, Database.class.getResourceAsStream("/sql/schema_sqlite.sql"));
                    executeSqlScript(conn, Database.class.getResourceAsStream("/sql/seed_sqlite_data.sql"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando la base de datos", e);
        }
    }

    private static void initMySql() throws SQLException {
        // Connect to server (no DB) and run schema which contains CREATE DATABASE and
        // USE
        String host = getProperty("mysql.host", "localhost");
        String port = getProperty("mysql.port", "3306");
        String user = getProperty("mysql.user", "root");
        String pass = getProperty("mysql.password", "");
        String params = getProperty("mysql.params", "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        String serverUrl = String.format("jdbc:mysql://%s:%s/%s", host, port, "");
        // driver accepts empty database segment; ensure we remove trailing slash
        if (serverUrl.endsWith("/"))
            serverUrl = serverUrl.substring(0, serverUrl.length() - 1) + params;
        else
            serverUrl = serverUrl + params;

        try (Connection conn = DriverManager.getConnection(serverUrl, user, pass)) {
            executeSqlScript(conn, Database.class.getResourceAsStream("/sql/schema_mysql.sql"));
        }

        // After DB created, connect to database and run seed
        String db = getProperty("mysql.database", "RHIUdigital");
        String dbUrl = String.format("jdbc:mysql://%s:%s/%s%s", host, port, db, params);
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass)) {
            executeSqlScript(conn, Database.class.getResourceAsStream("/sql/seed_mysql.sql"));
        }
    }

    private static void executeSqlScript(Connection conn, java.io.InputStream in) {
        if (in == null)
            return;
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(in))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("--") || line.isEmpty())
                    continue;
                sb.append(line).append(' ');
                if (line.endsWith(";")) {
                    String sql = sb.toString();
                    sql = sql.substring(0, sql.length() - 1); // remove semicolon
                    try (java.sql.Statement st = conn.createStatement()) {
                        st.execute(sql);
                    }
                    sb.setLength(0);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
