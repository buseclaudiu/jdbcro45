package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        // jdbc:mysql://localhost:3306/flower_shop
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flower_shop", "root", "vxsticlete123");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * from produgct where id = 1");
            rs.next();
            System.out.println(rs.getString(2)+ " " + rs.getString(4));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
