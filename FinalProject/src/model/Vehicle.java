/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mahmoud Ehab
 */
public abstract class Vehicle implements Rentable, Comparable<Vehicle> {
    protected String id;
    protected String brand;
    protected double pricePerDay;
    protected boolean available = true;
    
    public Vehicle(String id, String brand, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
    }
    
    public double calculateRentalPrice(int days)
    {
        return days * this.pricePerDay;
    }
    
    @Override
    public void rent() throws VehicleNotAvailableException {
        if (!available) {
            throw new VehicleNotAvailableException("Vehicle is not available");
        }
        available = false;
    }
    
    @Override
    public void returnVehicle() {
        available = true;
    }
    
    @Override
    public boolean isAvailable() {
        return available;
    }
    
    public String getId() {
        return id;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public double getPricePerDay() {
        return pricePerDay;
    }
    
    @Override
    public int compareTo(Vehicle v)
    {
        return (int)(this.pricePerDay - v.pricePerDay);
    }
}
