/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author Mahmoud Ehab
 */
public interface Rentable {
    void rent() throws VehicleNotAvailableException;
    void returnVehicle();
    boolean isAvailable();
}
