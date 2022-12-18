package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseConnectionExample {
    public static void main(String[] args) {
        Connection connection;
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Error on retrieving connection.");
            throw new RuntimeException(e);
        }
        createTableMarketingCampaign(connection);
        }

        public static void createTableMarketingCampaign(Connection connection) {
        try{
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists marketing_campaign (" +
                    "id int primary key auto_increment," +
                    "name varchar(200)," +
                    "start_date date," +
                    "budget double);");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void initializeMarketingCampaign(Connection connection){

        try {
            Statement statement = DatabaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from marketing_campaign;");
            if(resultSet.next() && resultSet.getInt(1) ==0){
                statement.execute("Insert into marketing_campaign (name, start_date, budget) values" +
                        "('Name1','2022-12-18',1000), ('Name2','2022-10-15',2000), ('Name3','2021-10-20',500)");
                System.out.println("The table marketing_campaign was initialized!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
