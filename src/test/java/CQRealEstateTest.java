/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import cqrealestatepackage.Buyer;
import cqrealestatepackage.HouseAndLand;
import cqrealestatepackage.Land;
import cqrealestatepackage.Sale;
import cqrealestatepackage.Seller;
import java.util.ArrayList;

/**
 * @author Francis Renzaho
 * Student ID: 12170110
 */
public class CQRealEstateTest {
    private final ArrayList<Seller> sellersArray = new ArrayList<>();
    private final ArrayList<Buyer> buyersArray = new ArrayList<>();
    private final ArrayList<Land> propertiesArray = new ArrayList<>();
    private final ArrayList<Sale> salesArray = new ArrayList<>();
    
    public void allTests(){
        buyersArray.add(new Buyer("bob", "128 helloworld street", 041316524));
        sellersArray.add(new Seller("John", "64 bit rd", 041316524));
        sellersArray.add(new Seller("Jeff", "32 bunny street", 041316524));
        buyersArray.add(new Buyer("David", "16 carrot street", 041316524));
        
        sellersArray.forEach(client -> {
            System.out.println(client.toString());
        });
        System.out.println("-------------------------------");
        
        propertiesArray.add(new Land(123213131, 5000, "512 first street"));
        propertiesArray.add(new HouseAndLand(1515311212, 1500, "69 That street", 1000, 32, 128));
        
        propertiesArray.forEach(property -> {
               System.out.println(property.toString());
        });
        System.out.println("-------------------------------");

            
        salesArray.add(new Sale(propertiesArray.get(0), "30/12/2020", 1000000, sellersArray.get(0), buyersArray.get(0)));
        salesArray.add(new Sale(propertiesArray.get(1), "10/1/2023", 559000, sellersArray.get(1), buyersArray.get(1)));
        salesArray.get(1).getProperty().setSold(true);
        
        salesArray.forEach(sale -> {
            System.out.println(sale.toString());
        });
        
    }
    
    public static void main(String[] args){
        CQRealEstateTest run = new CQRealEstateTest();
        run.allTests();
    }
}
