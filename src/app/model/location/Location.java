package app.model.location;

import java.util.ArrayList;

import app.dao.ReviewDao;
import app.model.Campus;
import app.model.Reviewable;
import app.model.userContent.Review;

public abstract class Location implements Reviewable, Comparable<Location>
{
    private int locationId;
    private ArrayList<Review> reviews;
    private String imagePath;
    private String name;
    private String description;
    private double rating;
    private Campus campus;

    public Location (int id, ArrayList<Review> revs, String imagePath,
                     String name, String desc, double rate, Campus campus){
        setLocationId(id);
        setReviews(revs);
        setImagePath(imagePath);
        setName(name);
        setDescription(desc);
        setRating(rate);
        setCampus(campus);
    }

    public int compareTo(Location loc)
    {
        int result = (int) Math.ceil(this.getRating() - loc.getRating());
        return result;
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

    /**
     * @return average rate given of all reviews as rating
     */
    public double getRating() {
        ArrayList<Review> reviews = ReviewDao.getReviewsOf(getLocationId());

        double sum = 0;
        int count = 0;

        for (Review r : reviews)
        {
            sum += r.getRateGiven();
            count++;
        }

        if (count == 0)
        {
            return -1;
        }

        return (sum/count);
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
