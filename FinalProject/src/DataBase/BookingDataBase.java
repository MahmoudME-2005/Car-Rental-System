/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import model.Booking;
import model.VehicleNotAvailableException;
/**
 *
 * @author Mahmoud Ehab
 */
public class BookingDataBase {
    private static final List<Booking> bookings = new ArrayList<>();
    
    static {
        try
        {
            bookings.add(new Booking(CustomerDataBase.getCustomers().get(0), VehicleDataBase.getVehicles().get(0), 12));
            bookings.add(new Booking(CustomerDataBase.getCustomers().get(1), VehicleDataBase.getVehicles().get(1), 20));
        }
        catch (VehicleNotAvailableException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Occurred");
            alert.setHeaderText(null); // Setting this to null makes it look cleaner
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    public static Booking findById(String id)
    {
        for (Booking b : bookings)
        {
            if (b.getId().equals(id))
            {
                return b;
            }
        }
        return null;
    }
    
    public static boolean exists(String id)
    {
        return (findById(id) != null);
    }
    
    public static List<Booking> getBookings()
    {
        return bookings;
    }
    
    public static List<Booking> getActiveUserBookings()
    {
        List<Booking> activeUserBookings = new ArrayList<>();
        for (Booking b : bookings)
        {
            if (b.getCustomer().getUserName().equals(CustomerDataBase.getActiveUser().getUserName()))
            {
                activeUserBookings.add(b);
            }
        }
        return activeUserBookings;
    }
}
