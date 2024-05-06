package app.model.location.cafeteria;

import java.util.ArrayList;

import app.model.Campus;
import app.model.location.Location;
import app.model.location.Room;
import app.model.userContent.Review;

public class Cafeteria extends Location{
    private double minPrice;
    private double maxPrice;
    private ArrayList<MenuItem> items;

    public Cafeteria (int id, ArrayList<Review> revs, String imagePath, String n, String desc, double rate, Campus c,
                      ArrayList<MenuItem> items, double minPrice, double maxPrice){
        super(id, revs, imagePath, n, desc, rate, c);
        setItems(items);
        setMaxPrice(maxPrice);
        setMinPrice(minPrice);
    }
    
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
