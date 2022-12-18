package org.example;

import java.sql.*;

public class JdbcConnectionExample {

    public static void main(String[] args) throws SQLException {
        try{
            System.out.println("Trying to connect to database.");
        Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flower_shop",
                "root",
                "root");
            System.out.println("Connection to database successful");
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");
            while(resultSet.next()){
                String name = resultSet.getString(2);
                Integer quantity = resultSet.getInt(5);
                Double price = resultSet.getDouble(4);
                System.out.println("Produsul " + name + " cu pretul " + price + " maxim " + quantity + " bucati");
            }
        }catch ( SQLException e) {
        System.out.println("Wrong pass exception :D");
        throw new RuntimeException(e);
        }
    }
}
