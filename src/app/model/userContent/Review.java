package app.model.userContent;

import app.dao.ReviewDao;
import app.model.Reviewable;
import app.model.User;

import java.util.Date;

public class Review extends UserContentItem {
    private Reviewable r;
    private double rateGiven;
    private int reviewId;

    /**
     * when adding a new review to database (i.e. posting a review) add review without id and then pull the auto assigned
     * id from database
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param r
     * @param rateGiven
     */
    public Review(int ownerId, String text, Date creation, Date lastEdit, Reviewable r, double rateGiven) {
        super(ownerId, text, creation, lastEdit);
        setR(r);
        setRateGiven(rateGiven);
        int id = ReviewDao.addReview(this);
        setID(id);
    }

    /**
     * constructor for when pulling the data from the database and id is known
     * @param own
     * @param text
     * @param creation
     * @param lastEdit
     * @param r
     * @param rateGiven
     * @param id
     */
    public Review(int id, int own, String text, Date creation, Date lastEdit, Reviewable r, double rateGiven) {
        super(own, text, creation, lastEdit);
        setR(r);
        setRateGiven(rateGiven);
        setID(id);
    }

    public Reviewable getR() {
        return r;
    }
    public void setR(Reviewable r) {
        this.r = r;
    }
    public double getRateGiven() {
        return rateGiven;
    }
    public void setRateGiven(double rateGiven) {
        this.rateGiven = rateGiven;
    }

    
}
