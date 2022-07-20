package com.hotel.villa;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class ReorderingColumnsOfTable {
    public static void main(String[] args)throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new java.sql.Driver() {
            @Override
            public Connection connect(String url, Properties info) {
                return null;
            }

            @Override
            public boolean acceptsURL(String url) {
                return false;
            }

            @Override
            public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
                return new DriverPropertyInfo[0];
            }

            @Override
            public int getMajorVersion() {
                return 0;
            }

            @Override
            public int getMinorVersion() {
                return 0;
            }

            @Override
            public boolean jdbcCompliant() {
                return false;
            }

            @Override
            public Logger getParentLogger() {
                return null;
            }
        });
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost:3306/hotel";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "admin");
        System.out.println("Connection established......");
        //Creating a Statement object
        Statement stmt = con.createStatement();
        //Query to re-order the table
        String query = "ALTER TABLE users MODIFY username varchar(255) AFTER id";
        String query1 = "ALTER TABLE users MODIFY email varchar(255) AFTER username";
        //Executing the query
        stmt.execute(query);
        stmt.execute(query1);
        //Retrieving the contents of the dispatches_data table
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");
        while(rs.next()) {
            System.out.print("ID: "+rs.getString("id")+", ");
            System.out.print("Username: "+rs.getString("username")+", ");
            System.out.print("Email: "+rs.getString("email")+", ");
            System.out.print("First Name: "+rs.getString("first_name")+", ");
            System.out.print("Last Name: "+rs.getString("last_name")+", ");
            System.out.println();
        }
    }
}
