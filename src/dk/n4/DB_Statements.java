package dk.n4;

import java.sql.*;

public class DB_Statements {

    // Declare a statement
    private static Statement stmt = null;

    // Declare a connection
    private static Connection con = DB_Connector.connect();

    // Declare a result set
    private static ResultSet rs = null;

    // Declare a PreparedStatement
    private static PreparedStatement pst = null;

    // Method to create a new database
    public void createNewDB(String DB_Name) {

        // SQL Statement
        String query = "create database if not exists " + DB_Name;

        try {
            // Connection
            stmt = con.createStatement();

            // Execute statement
            stmt.executeUpdate(query);
            System.out.println("\n--Database " + DB_Name + " created--");
        }
        catch (SQLException ex) {
            // Handle SQL Exceptions
            System.out.println("\n--Statement did not execute--");
            ex.printStackTrace();
        }

    }

    // Method to use a database
    public void useDB(String DB_Name) {

        // SQL Statement
        String query = "use " + DB_Name;
        try {
            // Connection
            stmt = con.createStatement();

            // Execute query
            stmt.executeUpdate(query);
            System.out.println("\n--Using " + DB_Name + " --");
        }
        catch (SQLException ex) {
            // Handle SQL Exceptions
            System.out.println("\n--Query did not execute--");
            ex.printStackTrace();
        }

    }

    // Method to create a table in db
    public void createTable(String tableName) {

        // SQL statement
        String query = "create table if not exists " + tableName +
                "(" +
                "id int not null auto_increment, " +
                "myName varchar(28), " +
                "address varchar(28), " +
                "primary key (id)" +
                ")";
        try {
            // Connection
            stmt = con.createStatement();
            //Execute query
            stmt.executeUpdate(query);
            System.out.println("\n--Table " + tableName + " created--");
        }
        catch (SQLException ex) {
            // Handle SQL Exceptions
            System.out.println("\n--Query did not execute--");
            ex.printStackTrace();
        }

    }

    // Method to inset data in db
    public void insertData(String tableName) {

        // SQL query
        String query = "insert into " + tableName +
                "(" +
                "myName, address) " +
                "values ('Daniel', 'My address'), " +
                "('Homer', 'His address'), " +
                "('John', 'Their address')";
        try {
            // Connect
            stmt = con.createStatement();
            //Execute query
            stmt.executeUpdate(query);
            System.out.println("\n--Data inserted into table " + tableName + "--");
        }
        catch (SQLException ex) {
            // Handle SQL Exceptions
            System.out.println("\n--Query did not execute--");
            ex.printStackTrace();
        }

    }

    // Method to show data from table
    public void selectFromTable(String tableName) {

        // SQL query
        String query = "select * from " + tableName;
        try {
            // Connection
            stmt = con.createStatement();
            // Execute
            rs = stmt.executeQuery(query);
            System.out.println("\nid\t\tmyName\t\taddress\n___________________________________");
            //Get data
            while (rs.next()) {
                int id = rs.getInt(1); // Returns the id (first column)
                String myName = rs.getString("myName"); // Returns myName (second column)
                String address = rs.getString("address"); // Returns address (third colmn)
                System.out.println(id + "\t\t" + myName + "\t\t" + address);
            }

        }
        catch (SQLException ex) {
            // Handle SQL Exceptions
            System.out.println("\n--Query did not execute--");
            ex.printStackTrace();
        }

    }

    // Method to check for user credentials
    public Boolean checkLogin(String username, String password) {

        boolean check = false;
        String query = "select * from thisdatabase.user where username = (?) and password = (?)";
        try {
            //stmt = con.createStatement();
            pst = con.prepareStatement(query);
            //rs = stmt.executeQuery(query);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while (rs.next()) {
                check = true;
                System.out.println("\n--Yoohoo! It works!!--");
            }
        } catch (SQLException e) {
            System.out.println("\n--Darn!!--");
            e.printStackTrace();
        }

        return check;

    }

}
