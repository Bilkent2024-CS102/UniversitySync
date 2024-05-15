package app.controller;
import app.dao.ReviewDao;
import app.dao.UserDao;
import app.model.location.Location;
import app.model.userContent.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ReviewPostController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private ImageView reviewPostImage_ID;

    @FXML
    private Label reviewPost_TopInfo_ID;
    @FXML
    private Label reviewPost_TopRating_ID;
    @FXML
    private Button deleteReviewButton;
    @FXML
    private TextArea reviewPost_textArea_ID;


    private Review thisReview;
    private Location thisLocation;

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        if (event.getSource() instanceof Node) {                                    //FOR Buttons
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        else if (event.getSource() instanceof MenuItem menuItem)  {                 //FOR MenuButtons
            stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/HomePage.fxml", event);
    }

    public void deleteMyReview(ActionEvent event) throws IOException {
        ReviewDao.removeReview(thisReview.getUserContentItemId());
        switchToHomePage(event);
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
        File file = new File("src/app/images/profilePictures/profilePicture" + review.getOwnerId() + ".png");
        Image image = new Image(file.toURI().toString());
        reviewPostImage_ID.setImage(image);
    }
}
