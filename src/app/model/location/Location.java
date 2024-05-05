package app.model.location;

import java.awt.*;
import java.util.ArrayList;

import app.model.Campus;
import app.model.Reviewable;
import app.model.userContent.Review;

public abstract class Location implements Reviewable {
    private static int numberOfInstances;
    private int locationId;
    private ArrayList<Review> reviews;
    private String imagePath;
    private String name;
    private String description;
    private double rating;
    private Campus campus;
    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        Location.numberOfInstances = numberOfInstances;
    }
    public int getLocationId() {
        return locationId;
    }
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public ArrayList<Review> getReviews() {
        return reviews;
    }
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public Campus getCampus() {
        return campus;
    }
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }
}
