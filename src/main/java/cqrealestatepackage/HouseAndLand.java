/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage;

/**
 * @author Francis Renzaho
 * Student ID: 12170110
 */
public class HouseAndLand extends Land {
    private double constructionArea;
    private int numberOfBedrooms;
    private int numberOfToilets;

    
    public HouseAndLand(int lotNumber, double landArea, String address, double constructionArea, int numberOfBedrooms, int numberOfToilets) {
        super(lotNumber, landArea, address);
        this.constructionArea = constructionArea;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfToilets = numberOfToilets;
        
        
    }
    
    public double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(double constructionArea) {
        this.constructionArea = constructionArea;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfToilets() {
        return numberOfToilets;
    }

    public void setNumberOfToilets(int numberOfToilets) {
        this.numberOfToilets = numberOfToilets;
    }
    @Override
    public String toString(){
        return super.toString()+", Construction Area: " +constructionArea+", Bedrooms: "+numberOfBedrooms +", Toilets: "+numberOfToilets;
    }
    
}
