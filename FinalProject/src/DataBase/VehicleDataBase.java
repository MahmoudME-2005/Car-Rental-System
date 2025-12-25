/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.util.ArrayList;
import java.util.List;
import model.Bike;
import model.Car;
import model.Van;
import model.Vehicle;

/**
 *
 * @author Mahmoud Ehab
 */
public class VehicleDataBase {
    private static final List<Vehicle> vehicles = new ArrayList<>();
    
    static {
        vehicles.add(new Car("Toyota", 50));
        vehicles.add(new Bike("Yamaha", 20));
        vehicles.add(new Van("Ford", 80));
    }
    
    public static Vehicle findById(String id)
    {
        for (Vehicle v : vehicles)
        {
            if (v.getId().equals(id))
            {
                return v;
            }
        }
        return null;
    }
    
    public static boolean exists(String id)
    {
        return (findById(id) != null);
    }
    
    public static List<Vehicle> getVehicles()
    {
        return vehicles;
    }
}
