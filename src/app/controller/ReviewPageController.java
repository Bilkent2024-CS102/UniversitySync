package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import com.mysql.cj.Session;
import javafx.stage.StageStyle;

public class ReviewPageController implements Initializable{
    private Stage stage;
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox reviewVBox_ID = new VBox();    //right Event VBox where we put our events
    private List<ReviewMock> reviewMock;

    public void initialize(URL location, ResourceBundle resources) {
        reviewMock = new ArrayList<>(data());

        try {
            for( int i = 0; i < reviewMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/reviewPost.fxml").toURI().toURL());
                VBox vbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                ReviewPostController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(reviewMock.get(i));
//              eventController.setRightEventTabController(this);
                reviewVBox_ID.getChildren().add(vbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ReviewMock> data() {
        List<ReviewMock> ls = new ArrayList<>();

        ReviewMock reviewPost1 = new ReviewMock();
        reviewPost1.setReviewTopInfo("Reviewed By Zaeem on 03-11-2022");         //THis sets the top info of review including name and date (get a string of all of them combined)
        reviewPost1.setReviewText("i ate this food and was bad");
        reviewPost1.setReviewRating("Rated 2/5");
        ls.add(reviewPost1);

        ReviewMock reviewPost2 = new ReviewMock();
        reviewPost2.setReviewTopInfo("Reviewed By Berkay on 01-12-2019");         //THis sets the top info of review including name and date (get a string of all of them combined)
        reviewPost2.setReviewText("The food was so good that i regularly visit this now");
        reviewPost2.setReviewRating("Rated 4.8/5");
        ls.add(reviewPost2);

        return ls;
    }



    public void postReview(ActionEvent event) throws IOException {

    }
    public void switchToPreviousScreen(ActionEvent event) throws IOException {

    }

}
