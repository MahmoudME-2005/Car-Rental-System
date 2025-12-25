/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;


import model.Admin;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mahmoud Ehab
 */
public abstract class AdminDataBase {
    private static final List<Admin> admins = new ArrayList<>();

    static {
        // ✅ MANUALLY added admins
        admins.add(new Admin("Mahmoud", "admin123"));
        admins.add(new Admin("Salsabil", "admin123"));
    }
    
    public static Admin findByUsername(String username) {
        for (Admin c : admins) {
            if (c.getUserName().equals(username)) {
                return c;
            }
        }
        return null;
    }
}
