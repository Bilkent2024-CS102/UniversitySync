package app.model.location.cafeteria;

import java.awt.*;

public class MenuItem {
    private static int numberOfInstances;

    private int menuItemId;
    private String name;
    private double price;
    private String imagePath;

    /*
    * Getters
    */
    public static int getNumberOfInstances()
    {
        return numberOfInstances;
    }
    public int getMenuItemId()
    {
        return menuItemId;
    }
    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    public String getImagePath()
    {
        return imagePath;
    }

    /*
    * Setters
    * TODO validation
    */
    public void setMenuItemId(int id)
    {
        this.menuItemId = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
}