package location;

import java.util.ArrayList;

import Campus;
import userContent.Review;

public abstract class Location {
    protected static int numberOfInstances;
    private int id;
    private ArrayList<Review> reviews;
    private Image image;
    private String name;
    private String description;
    private double rating;
    private Campus campus;

    public void addReview(Review r)
    {

    }

    public void removeReview(Review r)
    {

    }
}
