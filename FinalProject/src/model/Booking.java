/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mahmoud Ehab
 */
public class Booking {
    private String id;
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    
    public Booking(String id, Customer customer, Vehicle vehicle, int days) throws VehicleNotAvailableException {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.vehicle.rent();
        this.days = days;
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public Customer getCustomer()
    {
        return this.customer;
    }
    
    public Vehicle getVehicle()
    {
        return this.vehicle;
    }
    
    public int getDays()
    {
        return this.days;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setDays(int days) {
        this.days = days;
    }
    
    public double getTotalPrice() {
        return vehicle.calculateRentalPrice(days);
    }
}
