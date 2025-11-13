package com.mycompany.recursoshumanos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionConfig {
    
    private static final String URL = "jdbc:mysql://localhost:3306/recursoshumanosiudigital";
    private static final String USER = "root";
    private static final String PASSWORD = "Laz1018343643";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de MySQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
