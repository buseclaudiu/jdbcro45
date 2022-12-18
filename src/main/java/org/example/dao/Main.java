package org.example.dao;

import org.example.DatabaseConnection;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {

        MarketingCampaign myMarketingCampaign = new MarketingCampaign(null, "Name4", Date.valueOf("2022-12-11"), 3579d);
        MarketingCampaignDAO myMarketingCampaignDAO = new MarketingCampaignDAO(DatabaseConnection.getConnection());

        myMarketingCampaignDAO.createTable();
        myMarketingCampaignDAO.initialize();
//        myMarketingCampaignDAO.create(myMarketingCampaign);
        MarketingCampaign marketingCampaign = new MarketingCampaign (null, "Name5", Date.valueOf("2022-12-31"),10000d);
//        myMarketingCampaignDAO.createWithPreparedStatement(marketingCampaign);

        System.out.println(myMarketingCampaignDAO.findAll());
        System.out.println("-------------------------------FIND BY NAME--------------------------------");
        System.out.println(myMarketingCampaignDAO.searchByName("ame5"));
        System.out.println("-------------------------------SEARCH BY DATE--------------------------------");
        System.out.println(
                myMarketingCampaignDAO.searchBetween(
                        LocalDate.of(2022, 11, 1),
                        LocalDate.of(2024, 11, 1)
                )
        );
    }
}
