/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
public class Connect {

    public static void main(String[] args) throws SQLException {
        Connect c = new Connect();
        c.getConnection();
    }

    public Connection getConnection() throws SQLException {
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=crud;";
        String usuario = "sa";
        String password = "123456";
        String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, usuario, password);
            System.out.println("Connected!");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Error!");
            throw new SQLException(e.getMessage());
        }
    }
}
