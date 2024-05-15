package app.controller;
import app.dao.ReviewDao;
import app.dao.UserDao;
import app.model.location.Location;
import app.model.userContent.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ReviewPostController {

    @FXML
    private ImageView reviewPostImage_ID;
    @FXML
    private Label reviewPost_TopInfo_ID;
    @FXML
    private Label reviewPost_TopRating_ID;
    @FXML
    private Button reviewPost_deleteButton_ID;
    @FXML
    private TextArea reviewPost_textArea_ID;

    @FXML
    private Button deleteReviewButton;

    private Review thisReview;
    private Location thisLocation;

    public void deleteMyReview(ActionEvent event) throws IOException {
        ReviewDao.removeReview(thisReview.getReviewableId());
//        ReviewPageController.refresh(thisLocation);
    }

    public void setData(Review review, Location location) {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);

        //reviewPostImage_ID  ... s
        thisReview = review;
        thisLocation = location;
        reviewPost_TopInfo_ID.setText( "Review by " + UserDao.getUserById(review.getOwnerId()).getName() + " on " + review.getCreationDate());
        reviewPost_TopRating_ID.setText( "" + review.getRateGiven());
        reviewPost_textArea_ID.setText( review.getMainText());
        reviewPost_textArea_ID.setEditable(false);
        if (review.getOwnerId() != SessionManager.getCurrentUser().getUserId())
        {
            deleteReviewButton.setDisable(true);
        }
    }
}
