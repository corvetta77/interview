package com.enefit.interview.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class BillingEntity {
    private @Id
    @GeneratedValue Long id;

    private String clientId;
    private String name;
    private String country;
    private String date;
    private BigDecimal amountToPay;

    public BillingEntity(String clientId, String name, String country, String date, BigDecimal amountToPay) {
        this.clientId = clientId;
        this.name = name;
        this.country = country;
        this.date = date;
        this.amountToPay = amountToPay;
    }

    public BillingEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", clientId='" + this.clientId + '\'' + ", name='" + this.name + '\'' + '}';
    }
}
