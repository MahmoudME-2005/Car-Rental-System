/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package model;

/**
 *
 * @author Mahmoud Ehab
 */
public class VehicleNotAvailableException extends Exception {

    /**
     * Creates a new instance of <code>VehicleNotAvailableException</code>
     * without detail message.
     */
    public VehicleNotAvailableException() {
        
    }

    /**
     * Constructs an instance of <code>VehicleNotAvailableException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public VehicleNotAvailableException(String msg) {
        super(msg);
    }
}
