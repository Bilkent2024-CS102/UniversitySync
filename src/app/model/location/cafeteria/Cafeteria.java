package app.model.location.cafeteria;

import java.util.ArrayList;

import app.model.location.Location;

public class Cafeteria extends Location{
    private double minPrice;
    private double maxPrice;
    private ArrayList<MenuItem> items;
    
    public double getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
    public double getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
    public ArrayList<MenuItem> getItems() {
        return items;
    }
    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    
}
