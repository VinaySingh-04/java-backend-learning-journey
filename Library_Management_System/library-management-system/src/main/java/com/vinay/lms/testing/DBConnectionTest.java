package com.vinay.lms.testing;

import com.vinay.lms.util.DBConnections;

import java.sql.Connection;

public class DBConnectionTest {
    public static void main(String[] args) {

        Connection connection = DBConnections.getConnection();

        if (connection != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Database connection failed.");
        }
    }

}
