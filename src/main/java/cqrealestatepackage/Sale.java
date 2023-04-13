/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage;

/**
 * @author Francis Renzaho
 * Student ID: 12170110
 */
public class Sale {
    private static int tempID;
    private int saleID = 1101;
    private String date;
    private double soldPrice;
    private Land property;
    private Seller seller;
    private Buyer buyer;

    public Sale(Land property, String date, double soldPrice,  Seller seller, Buyer buyer) {
        this.saleID += tempID;
        this.date = date;
        this.soldPrice = soldPrice;
        this.property = property;
        this.seller = seller;
        this.buyer = buyer;
        tempID += 1;
    }

    public int getSaleID() {
        return saleID;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public Land getProperty() {
        return property;
    }

    public void setProperty(Land property) {
        this.property = property;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    
    @Override
    public String toString() {
        return  "SaleID: " + saleID +", Seller Name: " + seller.getName() + ", Buyer Name: " + buyer.getName()+ ", Date: " + date + ", Sold Price: " + soldPrice + ", Property: " + property;
    }
    
    
    
    
}
