package app.controller;
import app.model.userContent.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import app.model.location.cafeteria.Cafeteria;

public class CafeteriaNameController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private List<Review> cafeReviewList;

//********************************************************************************************
    @FXML
    private Button food_cafeteriaName_ID;
    @FXML
    private Label foodPage_CafeRating_ID;
    @FXML
    private Label foodPage_CafePriceRange_ID;

    // ************  Particular Cafe Associated with this page  ****************
    private Cafeteria thisCafe;

    public void setData(Cafeteria cafe) {

        thisCafe = cafe;        //connection bw different pages

        food_cafeteriaName_ID.setText( cafe.getName());
        String text = (cafe.getRating() < 0) ? ("No Reviews") : ("" + cafe.getRating());
        foodPage_CafeRating_ID.setText(text);
        foodPage_CafePriceRange_ID.setText( cafe.getMinPrice() + "-" + cafe.getMaxPrice() + " TL");


    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        CafeteriaDetailController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData(thisCafe);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }


    public void switchToCafeDetail(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/CafeteriaPage/CafeDetail.fxml", event);
    }

//    public List<ReviewMock> getCafeReviewList() {
//        return cafeReviewList;
//    }
//
//    public void setCafeReviewList(List<ReviewMock> cafeReviewList) {
//        this.cafeReviewList = cafeReviewList;
//    }

}

//
//class CafeMock {
//
//    private String cafeName;
//    private String cafeRating;
//    private String cafePriceRange;
//    private String cafeDescription;
//    private String cafeMenu;
//    private ImageView cafeImage;
//
//    public String getCafeName() {
//        return cafeName;
//    }
//
//    public String getCafePriceRange() {
//        return cafePriceRange;
//    }
//
//    public String getCafeRating() {
//        return cafeRating;
//    }
//    public ImageView getCafeImage() {
//        return cafeImage;
//    }
//
//    public void setCafeImage(ImageView cafeImage) {
//        this.cafeImage = cafeImage;
//    }
//
//    public String getCafeMenu() {
//        return cafeMenu;
//    }
//    public String getCafeDescription() {
//        return cafeDescription;
//    }
//
//    public void setCafeName(String cafeName) {
//        this.cafeName = cafeName;
//    }
//
//    public void setCafeRating(String cafeRating) {
//        this.cafeRating = cafeRating;
//    }
//
//    public void setCafePriceRange(String cafePriceRange) {
//        this.cafePriceRange = cafePriceRange;
//    }
//
//    public void setCafeMenu(String cafeMenu) {
//        this.cafeMenu = cafeMenu;
//    }
//
//    public void setCafeDescription(String cafeDescription) {
//        this.cafeDescription = cafeDescription;
//    }
//}