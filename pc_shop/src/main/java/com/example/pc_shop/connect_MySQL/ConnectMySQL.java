package com.example.pc_shop.connect_MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    private String jdbcURL = "jdbc:mysql://localhost:3306/case_module_3?userSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    public Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
