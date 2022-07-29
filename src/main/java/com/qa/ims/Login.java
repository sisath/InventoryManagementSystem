package com.qa.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    static Scanner sc = new Scanner(System.in);

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "ims";
    private static final String parameters = "?verifyServerCertificate=false&useSSL=true";

    public static Connection getConnection() {
        System.out.print("User > ");
        String user = sc.next();
        System.out.print("Password > ");
        String password = sc.next();

        Connection con = null;

        try {
            con = DriverManager.getConnection(URL + dbName + parameters, user, password);
        } catch (SQLException e) {
            System.out.println("Access Denied!");
        }
        return con;
    }
}


