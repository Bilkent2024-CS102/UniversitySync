package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CafeteriaController implements Initializable {

    // {
    //     cafeNameLabel.setText(cafe.getName());
    //     cafeNameLabel.onMouseClickedProperty(openCafe(cafe));
    //     String ratingLabelText = "Rated " + cafe.getRating() + "/5";
    //     ratingLabel.setText(ratingLabelText);
    //     String priceRangeLabelText = "Price range: TL" + cafe.getMinPrice + "-TL" + cafe.getMaxPrice;
    //     priceRangeLabel.setText(priceRangeLabelText);
    // }
    // private void openCafe(Cafe cafe)
    // {
    //     // UI method to open page for this particular cafe
    // }
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    //****************************************************************************************************8
    @FXML
    private VBox cafeVBoxID = new VBox();
    private List<CafeMock> cafeMock;


    public void initialize(URL location, ResourceBundle resources) {
        cafeMock = new ArrayList<>(data());

        try {
            for( int i = 0; i < cafeMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeMock.get(i));
//              eventController.setRightEventTabController(this);
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    ////// For filter button
    public void displayAddFilterPopup (ActionEvent event) throws IOException {
//        switchToFXML("Add_New_Event.fxml", event);

        // Load the FXML file for the filter screen
        fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/AddFilter_popup.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();

        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));

        // Show the filter screen
        filterStage.showAndWait(); // This will block interaction with the main window
    }

    //after user puts filter, use this code: (right now both addFilter and close are same)
    public void applyFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        // Close the filter screen
        stage.close();
    }

    public void cancelFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void orderCafeByNormal(ActionEvent event) {

    }

    public void orderCafeByRating(ActionEvent event) {

    }

    public void orderCafePrice(ActionEvent event) {
    }




    private List<CafeMock> data() {
        List<CafeMock> ls = new ArrayList<>();
        CafeMock cafe1 = new CafeMock();
        cafe1.setCafeName("Marmara");
        cafe1.setCafeDescription("This is the best cafeteraia in the world");
        cafe1.setCafeMenu(" Chicken Fiesta  100$-140$ \n Mac Cheese 50$-100$");
        cafe1.setCafeRating("Rating 3/4");
        cafe1.setCafePriceRange("100$-200$");

        //mock review posts for dorm Marmara
        List<ReviewMock> cafe1Reviews = new ArrayList<ReviewMock>();
        ReviewMock m1 = new ReviewMock();
        m1.setReviewTopInfo("Reviewed by Arhaan on 7-11-99");
        m1.setReviewRating("Rated 3.1/44");
        m1.setReviewText("This marmara is one of the best cafe in the campus ");
        cafe1Reviews.add( m1 );
        ReviewMock m2 = new ReviewMock();
        m2.setReviewTopInfo("Reviewed by your on 3-1-19");
        m2.setReviewRating("Rated 3.9/44");
        m2.setReviewText("This 76 dorm is extremely big and modern ");
        cafe1Reviews.add( m1 );
        cafe1Reviews.add(m2);
        cafe1.setCafeReviewList(cafe1Reviews);
        ls.add(cafe1);



        CafeMock cafe2 = new CafeMock();
        cafe2.setCafeName("Bilka");
        cafe2.setCafeDescription("Bilka is the most famous cafe in Bilkent University and has one of the best location and is situated near dorm 76");
        cafe2.setCafeMenu(" Tavuk Doner  100$-140$ \n Kebab and Pilav 50$-100$");
        cafe2.setCafeRating("Rating 3.9/4");
        cafe2.setCafePriceRange("50$-150");

        //mock review posts for Bilka
        List<ReviewMock> cafe2Reviews = new ArrayList<ReviewMock>();
        ReviewMock n1 = new ReviewMock();
        n1.setReviewTopInfo("Reviewed by faizan on 5-5-2020");
        n1.setReviewRating("Rated 4/4");
        n1.setReviewText("I like Bilka dorm as is one of the smallest cafe in the campus ");
        cafe2Reviews.add( n1 );
        ReviewMock n2 = new ReviewMock();
        n2.setReviewTopInfo("Reviewed by AG Guru on 3-1-19");
        n2.setReviewRating("Rated 3.9/44");
        n2.setReviewText("This bilka is extremely big and modern ");
        cafe2.setCafeReviewList(cafe2Reviews);
        ls.add(cafe2);
        return ls;
        // return ForumPostDao.getPostsByRecency();
    }

}
