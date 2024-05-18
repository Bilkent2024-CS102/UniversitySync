package app.model.userContent;

import app.dao.ReviewDao;

import java.util.Date;

public class Review extends UserContentItem {
    private int reviewableId;
    private double rateGiven;

    /**
     * when adding a new review to database (i.e. posting a review) add review without id and then pull the auto assigned
     * id from database
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param rId
     * @param rateGiven
     */
    public Review(int ownerId, String text, Date creation, Date lastEdit, int rId, double rateGiven) {
        super(ownerId, text, creation, lastEdit);
        setReviewableId(rId);
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
     * @param rId
     * @param rateGiven
     * @param id
     */
    public Review(int id, int own, String text, Date creation, Date lastEdit, int rId, double rateGiven) {
        super(id, own, text, creation, lastEdit);
        setReviewableId(rId);
        setRateGiven(rateGiven);
    }

    public int getReviewableId() {
        return reviewableId;
    }
    public void setReviewableId(int rId) {
        this.reviewableId = rId;
    }
    public double getRateGiven() {
        return rateGiven;
    }
    public void setRateGiven(double rateGiven) {
        this.rateGiven = rateGiven;
    }

    
}
