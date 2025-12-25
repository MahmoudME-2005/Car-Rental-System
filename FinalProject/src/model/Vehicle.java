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
    protected static int numberOfVehicles = 0;
    protected String id;
    protected String brand;
    protected double pricePerDay;
    protected boolean available = true;
    
    public Vehicle(String brand, double pricePerDay) {
        this.id = Integer.toString(++numberOfVehicles);
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void changeAvail()
    {
        this.available = !this.available;
    }
    
    @Override
    public int compareTo(Vehicle v)
    {
        return (int)(this.pricePerDay - v.pricePerDay);
    }
}
