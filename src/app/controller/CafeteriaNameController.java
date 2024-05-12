package app.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class CafeteriaNameController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

//********************************************************************************************
    @FXML
    private Button food_cafeteriaName_ID;
    @FXML
    private Label foodPage_CafeRating_ID;
    @FXML
    private Label foodPage_CafePriceRange_ID;

    public void setData(CafeMock event) {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);
        food_cafeteriaName_ID.setText( event.getCafeName());
        foodPage_CafeRating_ID.setText( event.getCafeRating());
        foodPage_CafePriceRange_ID.setText( event.getCafePriceRange());

    }




    public void switchToCafeDetail(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/CafeteriaPage/CafeDetail.fxml", event);
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}

class CafeMock {
    private String cafeName;
    private String cafeRating;
    private String cafePriceRange;


    public String getCafeName() {
        return cafeName;
    }

    public String getCafePriceRange() {
        return cafePriceRange;
    }

    public String getCafeRating() {
        return cafeRating;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public void setCafeRating(String cafeRating) {
        this.cafeRating = cafeRating;
    }

    public void setCafePriceRange(String cafePriceRange) {
        this.cafePriceRange = cafePriceRange;
    }
}