package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CafeteriaDetailController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private Label cafeName_ID;
    @FXML
    private ImageView cafeImage_ID;
    @FXML
    private TextArea cafeDescription_ID;
    @FXML
    private TextArea cafeMenuTextArea_ID;
    @FXML
    private TextField cafeRateTextField_ID;
    @FXML
    private TextField cafePriceRange_ID;

    private CafeMock cafeAssociatedWithThis;

    public void setData(CafeMock cafe) {

        cafeAssociatedWithThis = cafe;

        cafeName_ID.setText( cafe.getCafeName());
        //cafeImage_ID.setImage(event.getCafeImage().getImage());
        cafeDescription_ID.setText(cafe.getCafeDescription());
        cafeMenuTextArea_ID.setText( cafe.getCafeMenu());
        cafeRateTextField_ID.setText( cafe.getCafeRating());
        cafePriceRange_ID.setText( cafe.getCafePriceRange());
    }

    public void switchToFXML(String fxmlFileName, MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        ReviewPageController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page

        ///////UNCOMMENT kARLO Neechai
        //detailController.setData( cafeAssociatedWithThis);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToReviewPage(MouseEvent event) throws IOException {
        switchToFXML("src/app/view/ReviewPage.fxml", event);
    }
}