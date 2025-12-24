/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mahmoud Ehab
 */
public abstract class User {
    protected String name;
    protected String username;
    protected String password;
    
    protected User(String name, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = SecurityUtils.hash(password);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUserName()
    {
        return this.username;
    }
    
    public void setUserName(String username)
    {
        this.username = username;
    }
    
    public void setPassword(String password)
    {
        this.password = SecurityUtils.hash(password);
    }
}
