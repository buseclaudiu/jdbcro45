package org.example.dao;

import org.example.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MarketingCampaignDAO {

    private Connection connection;

    public MarketingCampaignDAO(Connection connection){
        this.connection = connection;
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists marketing_campaign (" +
                    "id int primary key auto_increment," +
                    "name varchar(200)," +
                    "start_date date," +
                    "budget double);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(){

        try {
            Statement statement = connection.createStatement();
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

    public void create(MarketingCampaign marketingCampaign){
        try {
            Statement statement = connection.createStatement();
            statement.execute("insert into marketing_campaign (name,start_date,budget) values" +
                    "('" + marketingCampaign.getName() + "', '" + marketingCampaign.getStartDate() + "', " + marketingCampaign.getBudget()+ ");" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createWithPreparedStatement(MarketingCampaign marketingCampaign){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into marketing_campaign (name, start_date, budget) values (?,?,?);");
            preparedStatement.setString(1, marketingCampaign.getName());
            preparedStatement.setDate(2, marketingCampaign.getStartDate());
            preparedStatement.setDouble(3, marketingCampaign.getBudget());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MarketingCampaign> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from marketing_campaign");
            List<MarketingCampaign> marketingCampaignList = new ArrayList<>();

            while (resultSet.next()){
                MarketingCampaign marketingCampaign = new MarketingCampaign(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getDouble(4));

                marketingCampaignList.add(marketingCampaign);
            }
            return marketingCampaignList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //
//    "select * from marketing_campaign " +
//            "where name like '%Andrei%';"

    //    "select * from marketing_campaign " +
//            "where name like '%' join users--%';"
    public List<MarketingCampaign> searchByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "select * from marketing_campaign " +
                    "where name like '%" + name + "%';"
            );
            ResultSet resultSet = statement.executeQuery();
            List<MarketingCampaign> marketingCampaignList = new ArrayList<>();

            while (resultSet.next()){
                MarketingCampaign marketingCampaign = new MarketingCampaign(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getDouble(4)
                );

                marketingCampaignList.add(marketingCampaign);
            }
            return marketingCampaignList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MarketingCampaign> searchBetween(LocalDate start, LocalDate end) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from marketing_campaign " +
                        "where start_date >= '" + start + "'" +
                        "and start_date <=' " + end + "';"
            );
            ResultSet resultSet = statement.executeQuery();
            List<MarketingCampaign> marketingCampaignList = new ArrayList<>();

            while (resultSet.next()){
                MarketingCampaign marketingCampaign = new MarketingCampaign(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getDouble(4)
                );

                marketingCampaignList.add(marketingCampaign);
            }
            return marketingCampaignList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //search by name - returneaza toate marketing campaign care includ in numele lor, numele dat de utilizator
    //searchByName("year") - ("New Year's Campaign", "2023 Year Campaign")
    // folositi pentru implementare PreparedStatement

    //search by date (before, after) - returneaza toate marketing campaign care au start_date intre datele date de utilizator
    //searchBetween(2022-12-01, 2022-12-31) toate marketing campaigns care incep in decembrie 2022
    //PreparedStatement.
}
