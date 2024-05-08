import java.sql.Date;
import java.time.LocalDate;

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
        
        Review review = new Review(SessionManager.getCurrentUser(), reviewText, date, date, cafe, rating);
        
        ReviewDao.addReview(review);
    }

    public void deleteReview(ActionEvent e)
    {
        Review review = (Review) e.getSource();
        if (review.getOwner().getUserId == SessionManager.getCurrentUser().getUserId())
        {
            ReviewDao.removeReview(review);
        }
    }
}
