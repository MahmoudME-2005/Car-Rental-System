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
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    
    public Booking(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }
    
    public double getTotalPrice() {
        return vehicle.calculateRentalPrice(days);
    }
}
