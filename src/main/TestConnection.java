package main;

import java.sql.Connection;
import util.DBConnection;

public class TestConnection {
    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("JDBC connection test passed.");
        } else {
            System.out.println("JDBC connection test failed.");
        }
    }
}