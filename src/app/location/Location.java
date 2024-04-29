package app.location;

import java.util.ArrayList;

import app.Campus;
import app.userContent.Review;

public abstract class Location {
    private static int numberOfInstances;
    private int locationId;
    private ArrayList<Review> reviews;
    private Image image;
    private String name;
    private String description;
    private double rating;
    private Campus campus;

    /*
    * Getters
    */
    public static int getNumberOfInstances()
    {
        return numberOfInstances;
    }
    public int getLocationId()
    {
        return locationId;
    }
    public ArrayList<Review> getReviews()
    {
        return reviews;
    }
    public Image getImage()
    {
        return image;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public double getRating()
    {
        return rating;
    }
    public Campus getCampus()
    {
        return campus;
    }

    /*
     * Setters
    */

    public void addReview(Review r)
    {

    }

    public void removeReview(Review r)
    {

    }
}
