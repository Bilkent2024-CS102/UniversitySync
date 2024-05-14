package app.controller;

import app.dao.ReviewDao;
import app.model.location.Location;
import app.model.userContent.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    //USE THIS : private ...... dormOrCafeAssociatedWithThis;
    private Location location;
    ////// ^^^^^^ HERE REPLACE Location with parent class of both dorm and cafe DATABASE


    public void setData(Location loc) {
        //  this.dormAssociatedWithThis = dormAssociatedWithThis;
        this.location = loc;
//        reviewVBox_ID.getChildren().clear();

        reviews = ReviewDao.getReviewsOf(loc.getLocationId());

        try {
            for(int i = 0; i < reviews.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/reviewPost.fxml").toURI().toURL());
                VBox vbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                ReviewPostController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(reviews.get(i));
                reviewVBox_ID.getChildren().add(vbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReview(ActionEvent event) {
        try {
            String reviewMainText = reviewText.getText();
            double rating = Double.parseDouble(reviewRating.getText());
            Review review = new Review(SessionManager.getCurrentUser().getUserId(), reviewMainText, new Date(), new Date(), location.getLocationId(), rating);
            reviewText.clear();
            reviewRating.clear();
            setData(location);
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