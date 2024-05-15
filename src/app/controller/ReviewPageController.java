package app.controller;

import app.dao.ReviewDao;
import app.model.location.Dormitory;
import app.model.location.Location;
import app.model.location.cafeteria.Cafeteria;
import app.model.userContent.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
//import com.mysql.cj.Session;


public class ReviewPageController {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox reviewVBox_ID = new VBox();
    private List<Review> reviews;

    @FXML
    private TextArea reviewText;
    @FXML
    private TextField reviewRating;
    @FXML
    private Button postReviewButton;

    @FXML
    private Label locationNameLabel;
    @FXML
    private Label locationTypeLabel;
    @FXML
    private Label reviewCountLabel;
    @FXML
    private Label ratingLabel;
    @FXML
    private ImageView pictureInReviews;


    private Location location;


    public void setData(Location loc) {
        this.location = loc;
        reviewVBox_ID.getChildren().clear();
        reviewVBox_ID.getChildren().add(reviewRating);
        reviewVBox_ID.getChildren().add(reviewText);
        reviewVBox_ID.getChildren().add(postReviewButton);
        locationNameLabel.setText(loc.getName());
        if (loc instanceof Dormitory)
        {
            locationTypeLabel.setText("Dormitory");
        }
        else
        {
            locationTypeLabel.setText("Cafeteria");
        }

        reviews = ReviewDao.getReviewsOf(loc.getLocationId());

        reviewCountLabel.setText(reviews.size() + " Reviews");
        if (loc.getRating() < 0)
        {
            ratingLabel.setText("No reviews");
        }
        else
        {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            ratingLabel.setText("" + decimalFormat.format(loc.getRating()));
        }

        try {
            for(int i = reviews.size() - 1; i >= 0; i--) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/reviewPost.fxml").toURI().toURL());
                VBox vbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                ReviewPostController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(reviews.get(i), location);
                reviewVBox_ID.getChildren().add(vbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String path = "";
        if (loc instanceof Dormitory){
            path = "src/app/images/dormitoryPictures/dormitoryPicture" + loc.getLocationId() + ".png";
        }
        else if (loc instanceof Cafeteria){
            path = "src/app/images/cafeteriaPictures/cafeteriaPicture" + loc.getLocationId() + ".png";
        }
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        pictureInReviews.setImage(image);
    }

    public void addReview(ActionEvent event) {
        try {
            String reviewMainText = reviewText.getText();
            double rating = Double.parseDouble(reviewRating.getText());
            if (rating < 0 || rating > 5)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please enter a valid rating");
                alert.showAndWait();
            }
            else
            {
                Review review = new Review(SessionManager.getCurrentUser().getUserId(), reviewMainText, new Date(), new Date(), location.getLocationId(), rating);
                reviewText.clear();
                reviewRating.clear();
                setData(location);
            }
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Add a number rating to it");
            alert.showAndWait();
        }
    }
}