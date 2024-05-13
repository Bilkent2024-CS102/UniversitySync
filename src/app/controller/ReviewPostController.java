package app.controller;
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

    public void deleteMyReview(ActionEvent event) throws IOException {
        //user should be able to delete its own reviews only
    }

    public void setData(ReviewMock review) {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);

        //reviewPostImage_ID  ... s
        reviewPost_TopInfo_ID.setText( review.getReviewTopInfo());
        reviewPost_TopRating_ID.setText( review.getReviewRating());
        reviewPost_textArea_ID.setText( review.getReviewText());

    }
}

//MOCK CLASS
class ReviewMock {
    private String reviewTopInfo;
    private String reviewRating;
    private String reviewText;
    //private Image >>>>


    public String getReviewTopInfo() {
        return reviewTopInfo;
    }

    public void setReviewTopInfo(String reviewTopInfo) {
        this.reviewTopInfo = reviewTopInfo;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    //private (For image)
}