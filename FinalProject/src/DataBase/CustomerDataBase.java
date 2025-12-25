/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import model.Customer;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mahmoud Ehab
 */
public class CustomerDataBase {
    private static final List<Customer> customers = new ArrayList<>();
    private static Customer activeUser = null;
    
    static {
        customers.add(new Customer("mahmoud", "securePass"));
        customers.add(new Customer("salsabil", "securePass"));
    }
    
    public static boolean register(String username, String password) 
    {
        if (exists(username))
        {
            return false;
        }

        customers.add(new Customer(username, password));
        return true;
    }
    
    public static Customer findByUsername(String username) {
        for (Customer c : customers) {
            if (c.getUserName().equals(username)) {
                return c;
            }
        }
        return null;
    }
    
    private static boolean exists(String username) {
        return (findByUsername(username) != null);
    }
    
    public static List<Customer> getCustomers()
    {
        return customers;
    }

    public static Customer getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(Customer activeUser) {
        CustomerDataBase.activeUser = activeUser;
    }
}
