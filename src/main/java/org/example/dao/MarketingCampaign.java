package org.example.dao;

import javax.xml.crypto.Data;
import java.sql.Date;

public class MarketingCampaign {

    private Integer id;
    private String name;
    private Date startDate;
    private Double budget;

    public MarketingCampaign(Integer id, String name, Date startDate, Double budget) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "MarketingCampaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", budget=" + budget +
                '}';
    }
}
