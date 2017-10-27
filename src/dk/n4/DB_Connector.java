package dk.n4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {

    // Declare a connection
    private static Connection con = null;

    // Declare JDBC driver
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // The URL = jdbc:dbms//hostname:port#/dbname
    private static String url = "jdbc:mysql://localhost:3306/"; //Don't use dbname here right now

    // Declare username
    private static String user = "root";

    // Declare password
    private static String password = "1234";

    // Create the connection method
    public static Connection connect() {
        System.out.println("\n--Connecting to MySQL JDBC--");

        // Locate MySQL JDBC Driver
        try {
            Class.forName(DRIVER);
            System.out.println("\n--MySQL JDBC Driver registered--");
        }
        // Catch exceptions if JDBC is not found
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("\n--JDBC driver is missing--");
        }

        try {
            // Connect to MySQL DB = URL, userName, password
            con = DriverManager.getConnection(url, user, password);
        }
        // Catch exceptions on connection error
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("\n--Did not connect try again--");
        }

        // If connection successful
        if (con != null) {
            System.out.println("\n--Connection successful--");
        } else {
            // If connection fails
            System.out.println("\n--Failed to connect--");
        }

        return con;

    }

}