/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cqrealestatepackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Francis Renzaho
 * Student ID: 12170110
 */
public class CQRealEstate {
    private final ArrayList<Seller> sellersArray = new ArrayList<>();
    private final ArrayList<Buyer> buyersArray = new ArrayList<>();

    private final ArrayList<Land> propertiesArray = new ArrayList<>();
    private final ArrayList<Sale> salesArray = new ArrayList<>();
    private final Scanner scannerInput = new Scanner(System.in);
    
    private int itemCount = 0;
    private String selection;
    int lotNumber;
    String address;
    double landArea;
    
    public void addDefaultValues(){
        buyersArray.add(new Buyer("bob", "128 helloworld street", "0411235813"));
        sellersArray.add(new Seller("John", "64 bit rd", "0421345589"));
        sellersArray.add(new Seller("Jeff", "32 bunny street", "0415927585"));
        buyersArray.add(new Buyer("David", "16 carrot street", "0421716312"));
        
        propertiesArray.add(new Land(123213131, 5000, "512 first street"));
        propertiesArray.add(new HouseAndLand(1515311212, 1500, "69 That street", 1000, 32, 128));
        
//        salesArray.add(new Sale(propertiesArray.get(1), "10/1/2023", 559000, sellersArray.get(1), buyersArray.get(1)));
//        salesArray.get(0).getProperty().setSold(true);
    }
    
    //Displays the first menu and calls functions basing on user inputs
    public void displayWelcomeMenu(){
        do{
            System.out.println("\n------------ Welcome To CQRealEstate ------------ ");
            System.out.println("__________ Created By Francis Renzaho ___________\n");
            System.out.println("""
                               [0] Make A Sale
                               [1] Add Seller
                               [2] Add Buyer
                               [3] Add Property
                               [4] Find Sale
                               [5] Show Previous Sales
                               [69] Exit
                               """);
            System.out.print("Select An Option [0-5]: ");
            selection = scannerInput.nextLine();
            
          //the loop ends when user input is not empty, and is between 0 5
        }while(!isEmpty(selection) && !isBetween(selection,0,5));
        
        switch(selection){
            case "0" -> addSale();
            case "1" -> addSeller();
            case "2" -> addBuyer();
            case "3" -> addProperty();
            case "4" -> findSale();
            case "5" -> showPreviousSales();
            case "69" -> exitMethod();
            default -> displayWelcomeMenu();
        }

    }
    
    public void addSale(){
        //bet
        if(!BuyersAndSellersExist() || !propertiesExist()){
            displayWelcomeMenu();
            return;
        }
        getSaleInfo();
    }
    
    public void addSeller(){
        System.out.println("\n--------- ADDING SELLER --------");
        getClientsInfo("Seller");
    }
    
    public void addBuyer(){
        System.out.println("\n--------- ADDING BUYER ---------");
        getClientsInfo("Buyer");
    }
    
    //Show property Menu
    public void addProperty(){
        System.out.println("\n--------- ADDING PROPERTY ---------");
        do{    
            System.out.print("""
                             [0] Land
                             [1] Land And House
                             [99] Goto Main Menu
                             """);
            System.out.print("Enter Selection: ");
            selection = scannerInput.nextLine();
            
          //the loop ends when user input is not empty, and is between 0 1
        }while(!isEmpty(selection) && !isBetween(selection,0,1));
         switch(selection){
            case "0" -> getLandInfo(true);
            case "1" -> getLandAndHouseInfo();
            case "99" -> displayWelcomeMenu();
            default -> addProperty();
        }
        
    }
    
    public void findSale(){
        System.out.println("\n--------- SEARCHING FOR SALE ---------");
        if(salesArray.isEmpty()){
            System.out.println("\n[-] No Existing Sales Found..");
            return;
        }
        boolean isRunning = true;
        
        while(isRunning){
            do{    
                System.out.println("""
                                   [66] Display All Sales
                                   [99] Go to Main Menu
                                   """);
                System.out.print("Enter SaleID: ");
                selection = scannerInput.nextLine();
                if(selection.equals("99")){
                    displayWelcomeMenu();
                    return;
                    
               }
            }while(!isEmpty(selection) || !isNumber(selection));
            findSaleWithId(Integer.parseInt(selection));
            
            //check selection equals 66, if true display all sales
            if(selection.equals("66")){
                displayAllSales();
            }
            //
            else if(itemCount == salesArray.size()){
                System.out.println("[-] Sale with SaleID: "+selection+ " is not Found\n");
            } else {
                findSale();
            }
        }
       
        
    }
    public void findSaleWithId(int id){
        itemCount = 0;
        salesArray.forEach(sale -> {
            if(sale.getSaleID() == id){
                System.out.println("[+] "+sale.toString()+"\n");
            }else{
                itemCount++;
                
            }
        });
        
    
    }
    
    //prints recent sales
    public void showPreviousSales(){
        System.out.println("\n--------- SHOWING RECENT SALES ---------");

        if(salesArray.isEmpty()){
            System.out.println("[-] No Recent Sales Found..");
            displayWelcomeMenu();
            return;
        }
        
        int temp = 0;
        //checks for sales with sold properties and prints them
        for(Sale sale : salesArray){
            if(sale.getProperty().isSold()){
                temp +=1;
                System.out.println("[+] "+sale.toString());
            }
        }
        if(temp == 0){
            System.out.println("[-] No Recent Sales Found..");
        }
        displayWelcomeMenu();
    }
    
    public void getSaleInfo(){
        System.out.println("\n--------- MAKING A SALE ---------");

        int month;
        int day;
        int year;
        String date;
        double soldPrice;
        Land property;
        Seller seller;
        Buyer buyer;
        Sale temp;
        
        
        //Get Seller info
        do{
           System.out.println("\n******* AVAILABLE SELLERS *******");
           //Display all Available Properties
           showAllSellers();
           System.out.println("\n[99] Goto Main Menu");
           System.out.print("Select A Seller: ");
           selection = scannerInput.nextLine();
           if(selection.equals("99")){
               displayWelcomeMenu();
               return;
           }
       }while(!isEmpty(selection) || !isBetween(selection,0,sellersArray.size()-1));

        seller = getSeller(Integer.parseInt(selection));
        
        //Get property info
        do{
           System.out.println("\n******* AVAILABLE PROPERTIES *******");
           //Display all Available Properties
           showAllProperties();
           System.out.print("Select A Property: ");
           selection = scannerInput.nextLine();
           if(selection.equals("99")){
               displayWelcomeMenu();
               return;
           }
       }while(!isEmpty(selection) || !isBetween(selection,0,propertiesArray.size()-1));

        property = getProperty(Integer.parseInt(selection));
        
        //Mark property as sold
        property.setSold(true);
        
        
        
        
        //Get Buyer info
        do{
            System.out.println("\n******* AVAILABLE BUYERS *******");
            //Display all Available Properties
            showAllBuyers();
            System.out.print("Select A Buyer: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
       }while(!isEmpty(selection) || !isBetween(selection,0,buyersArray.size()-1));

        buyer = getBuyer(Integer.parseInt(selection));
        
        
        
        //Get month Value
        do{
           
           System.out.print("\nEnter Month: ");
           selection = scannerInput.nextLine();
           if(selection.equals("99")){
               displayWelcomeMenu();
               return;
           }
       }while(!isEmpty(selection) || !isBetween(selection, 1, 12));

        month = Integer.parseInt(selection); 
        
        //Get day value
        do{
           System.out.print("\nEnter Day: ");
           selection = scannerInput.nextLine();
           if(selection.equals("99")){
               displayWelcomeMenu();
               return;
           }
       }while(!isEmpty(selection) || !isBetween(selection, 1, 31));
        day = Integer.parseInt(selection); 
        
        //Get year value
        do{
          System.out.print("\nEnter Year: ");
          selection = scannerInput.nextLine();
          if(selection.equals("99")){
              displayWelcomeMenu();
              return;
          }
       }while(!isEmpty(selection) || !isNumber(selection));

        year = Integer.parseInt(selection);
        
        //convert day, month, and year to String
        //Asign them to date
        date = String.valueOf(day+"/"+month+"/"+year);
        
        //Get soldPrice info
        do{
          System.out.print("\nEnter soldPrice: ");
          selection = scannerInput.nextLine();
          if(selection.equals("99")){
              displayWelcomeMenu();
              return;
          }
       }while(!isEmpty(selection) || !isNumber(selection));

        soldPrice = Double.parseDouble(selection);
        
        
        
        temp = new Sale(property, date, soldPrice, seller, buyer);
        salesArray.add(temp);
        //Print the created Object
        System.out.println("\n[+] "+temp.toString());
        System.out.println("******* Sale Object Created! *********\n");

        //check if user wants to add another object, goto main menu or exit
        lastExitComformation("createSale");
        
    }
    
    //collects sellers and buyers info
    public void getClientsInfo(String client){
        String name;
        String phoneNumber;
        
        //gets client's Name
        do{
            System.out.println("\n[99] Goto Main Menu");
            System.out.print("Enter "+client+"'s Name: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) && !isString(selection));
        
        name = selection;
        
        
        //gets client's address
        do{
            System.out.print("\nEnter "+client+"'s Address: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) && !isString(selection));
        
        address = selection;
        
        //gets clients's phone number
         do{
            System.out.print("\nEnter "+client+"'s Phone Number: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) || !isNumber(selection));
         
        phoneNumber = selection;
        
        //check who called the function and create the object
        if(client.equals("Seller")){
            sellersArray.add(new Seller(name, address, phoneNumber));
        }else{
            buyersArray.add(new Buyer(name, address, phoneNumber));
        }
        System.out.println("\n[+] New "+client + " Added\n");
        
        //check if user wants to add another object, goto main menu or exit
        lastExitComformation(client);
            
            
    
        
    
    }
    //collect land info and create object
    public void getLandInfo(boolean isLand){
        
        var landOrLandAndHouse = isLand? "Land":"LandAndHouse";
        if(landOrLandAndHouse.equals("Land")){
            System.out.println("\n[+] Adding Land");
        }else{
            System.out.println("\n[+] Adding Land And House");
        }
        Land temp;
        //get Lot Number
        do{
            System.out.println("\n[99] Goto Main Menu");
            System.out.print("Enter Lot Number: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) || !isNumber(selection));
        lotNumber = Math.abs(Integer.parseInt(selection));
        
        //get Address
        do{
            System.out.print("\nEnter Address: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) && !isString(selection));
        address = selection;
        
        //get Land Area
        do{
            System.out.print("\nEnter Land Area: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) || !isNumber(selection));
        landArea = Math.abs(Double.parseDouble(selection));
        
        temp = new Land(lotNumber, landArea, address);
        //check who called the function and create the object
        if(landOrLandAndHouse.equals("Land")){
            propertiesArray.add(temp);
            
            //print the created object
            System.out.println("\n[+] "+temp.toString());
            System.out.println("******* "+landOrLandAndHouse + " Object Created! *********\n");
        
            //check if user wants to add another object, goto main menu or exit
            lastExitComformation(landOrLandAndHouse);
        }
        
        
        
    }
    
    
    //collects land and House infomation
    public void getLandAndHouseInfo(){
        double constructionArea;
        int numberOfBedrooms;
        int numberOfToilets;
        HouseAndLand temp;
        //
        getLandInfo(false);
        
        //get Construction Area
        do{
            System.out.print("\nEnter Construction Area: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) || !isNumber(selection));
        constructionArea = Math.abs(Double.parseDouble(selection));
        
        //Get Number Of Toilets
        do{
            System.out.print("\nEnter Number Of Bedrooms: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) || !isNumber(selection));
        numberOfBedrooms = Math.abs(Integer.parseInt(selection));
        
        //Get Number Of Toilets
        do{
            System.out.print("\nEnter Number Of Toilets: ");
            selection = scannerInput.nextLine();
            if(selection.equals("99")){
                displayWelcomeMenu();
                return;
            }
        }while(!isEmpty(selection) || !isNumber(selection));
        numberOfToilets = Math.abs(Integer.parseInt(selection));
        
        temp = new HouseAndLand(lotNumber, landArea, address, constructionArea, numberOfBedrooms, numberOfToilets);
        propertiesArray.add(temp);
        //Print the created Object
        System.out.println("\n[+] "+temp.toString());
        System.out.println("******* New Land And House Object Created! *********\n");

        //check if user wants to add another object, goto main menu or exit
        lastExitComformation("LandAndHouse");

    }
    
    public boolean propertiesExist(){
        int temp = 0;
        for(Land property : propertiesArray){
            if(property.isSold()){
                temp += 1;
            }
        }
        
        if(temp == propertiesArray.size()){
            System.out.println("[-] No Available Properties"); 
            return false;
        }else{
            return true;
        }
        
    }
    
     public boolean BuyersAndSellersExist(){
        if(buyersArray.isEmpty()){
            System.out.println("[-] No Buyers Found..");
            return false;
        }else if(sellersArray.isEmpty()){
            System.out.println("[-] No Sellers Found..");
            return false;
        }else{
            return true;
        }
        
    }
    
   public void displayAllSales(){
        System.out.print("\n");
        salesArray.forEach(sale -> {
            System.out.println("[+] "+sale.toString());
        });
        System.out.print("\n");
        

   }
    
   
    public Land getProperty(int number){
        return propertiesArray.get(number);
    }
    public Seller getSeller(int number){
        return sellersArray.get(number);
    }
    public Buyer getBuyer(int number){
        return buyersArray.get(number);
    }
    
    public void showAllProperties(){
        for(int i = 0; i < propertiesArray.size(); i++){
            if(!propertiesArray.get(i).isSold()){
                System.out.println("["+i+"] "+propertiesArray.get(i).toString());
            } 
        
        }
    }
    
    public void showAllSellers(){
        for(int i = 0; i < sellersArray.size(); i++){
                System.out.println("["+i+"] "+sellersArray.get(i).toString());
            }
        
    }
    
    public void showAllBuyers(){
        for(int i = 0; i < buyersArray.size(); i++){
                System.out.println("["+i+"] "+buyersArray.get(i).toString());
        }
    }
    
    //Check if input in Empty
    public boolean isEmpty(String value){
        if(value.equals("")){
            System.out.println("[-] Your Response is Empty. Try Again..");
            return false;
        }else{
            return true;
        }
      
    }
    
    public void lastExitComformation(String value){
        do{
            System.out.printf("""
                                [11] Add AnOther %s
                                [99] Go to Main Menu
                                [69] Exit
                                """, value);
            System.out.print("\nEnter Selection: ");
            selection = scannerInput.nextLine();
        }while(!isEmpty(selection) || !isNumber(selection));
        
        switch(selection){
            case "11" ->  methodSelector(value);
            case "99" -> displayWelcomeMenu();
            case "69" -> System.out.print("GoodBye!!");
            default -> lastExitComformation(value);
        }
        
    }
    
    public int exitMethod(){
        System.out.println("GoodBye!!");
        return 0;
    }
    
    
    //A shortcut for executing functions
    public void methodSelector(String name){
        switch(name){
            case "Seller" -> getClientsInfo(name);
            case "Buyer" -> getClientsInfo(name);
            case "Land" -> getLandInfo(true);
            case "LandAndHouse" -> getLandAndHouseInfo();
            case "createSale" -> addSale();
        }
    }
    
    //Check whether value is an integer
    public boolean isNumber(String value){
        //first, it tries to convert the given value to int
        //if it fails then the provided value is not an Integer
        try{
            Integer.valueOf(value);
            return true;
        }catch(NumberFormatException e){
            System.out.println("[-] Enter a valid number!!");
            return false;
        }
    }
    
    public boolean isString(String value){
        try{
            Integer.valueOf(value);
            System.out.println("[-] Error: No Numbers Allowed. Try again..");
            return false;
        }catch(NumberFormatException e){
            return true;
        }
    }
    
    
    //check if input is a number and is between the provided values
    public boolean isBetween(String value, int minimumValue, int maximumValue){
         if(isNumber(value) && (Integer.parseInt(value) >= minimumValue && Integer.parseInt(value) <= maximumValue)){
             return true;
         }else{
             System.out.println("[-] The input should be between "+minimumValue+" And "+ maximumValue);
             return false;
         }
    }
    
  
    
    
    public static void main(String[] args) {
        
        CQRealEstate run = new CQRealEstate();
        run.addDefaultValues();
        run.displayWelcomeMenu();
    }
}
