/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage;

/**
 * @author Francis Renzaho
 * Student ID: 12170110
 */
public class Clients {
    private static int tempID;
    private int clientID = 1;
    private String clientType = getClass().getSimpleName();

    private String name;
    private String address;
    private String phoneNumber;
    
    public Clients(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.clientID += tempID;
        tempID += 1;
        
    }
    
    public String getClientType() {
        return clientType;
    }
    public int getClientID() {
        return clientID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString(){
        return "ClientID: "+clientID+", Name: "+name+", Address: "+address+", Phone Number: "+phoneNumber+", Client Type: "+clientType;
    }
    
}
