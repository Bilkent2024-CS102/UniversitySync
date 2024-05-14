package app.controller;

import java.sql.Date;
import java.time.LocalDate;

import app.controller.SessionManager;
import app.dao.ReviewDao;
import app.model.location.cafeteria.Cafeteria;
import app.model.userContent.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CafeReviewController {

    @FXML
    private TextField review;
    @FXML
    private TextField rating;

    public void submitReview(ActionEvent e)
    {
        String reviewText = review.getText();
        String ratingText = rating.getText();
        double rating = Double.parseDouble(ratingText);
        Cafeteria cafe = (Cafeteria) e.getSource();
        Date date = Date.valueOf(LocalDate.now());

        Review review = new Review(SessionManager.getCurrentUser().getUserId(), reviewText, date, date, cafe.getLocationId(), rating);

        ReviewDao.addReview(review);
    }

    public void deleteReview(ActionEvent e)
    {
        Review review = (Review) e.getSource();
        if (review.getOwnerId() == SessionManager.getCurrentUser().getUserId())
        {
            ReviewDao.removeReview(review.getUserContentItemId());
        }
    }
}
