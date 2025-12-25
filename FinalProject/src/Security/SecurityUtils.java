/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import DataBase.AdminDataBase;
import DataBase.CustomerDataBase;
import model.Admin;
import model.Customer;
/**
 *
 * @author Mahmoud Ehab
 */
public class SecurityUtils {
    public static boolean authenticateAdmin(String username, String password)
    {
        Admin tryingToLogin = AdminDataBase.findByUsername(username);
        if (tryingToLogin != null)
        {
            return tryingToLogin.getHashedPassword().equals(hash(password));
        }
        else
        {
            return false;
        }
    }
    
    public static boolean authenticateCustomer(String username, String password)
    {
        Customer tryingToLogin = CustomerDataBase.findByUsername(username);
        if (tryingToLogin != null)
        {
            if(tryingToLogin.getHashedPassword().equals(hash(password)))
            {
                CustomerDataBase.activeUser = tryingToLogin;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
    public static String hash(String originalPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing hash function", e);
        }
    }
}
