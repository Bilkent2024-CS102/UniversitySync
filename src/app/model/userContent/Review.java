package app.model.userContent;

import app.model.Reviewable;

public class Review extends UserContentItem {
    private Reviewable r;
    private double rateGiven;
    
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
