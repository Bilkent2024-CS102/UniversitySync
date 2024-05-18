package app.controller;

import app.model.location.cafeteria.Cafeteria;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

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

    private Cafeteria thisCafeteria;

    public void setData(Cafeteria cafe) {
        thisCafeteria = cafe;
        cafeName_ID.setText(cafe.getName());
        cafeDescription_ID.setText(cafe.getDescription());
        cafeMenuTextArea_ID.setText(cafe.getMenu());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String text = (cafe.getRating() < 0) ? ("No Reviews") : ("" + decimalFormat.format(cafe.getRating()));
        cafeRateTextField_ID.setText(text);
        cafePriceRange_ID.setText(cafe.getMinPrice() + "-" + cafe.getMaxPrice() + " TL");

        cafeDescription_ID.setEditable(false);
        cafeMenuTextArea_ID.setEditable(false);
        cafeRateTextField_ID.setEditable(false);
        cafePriceRange_ID.setEditable(false);

        File file = new File("src/app/images/cafeteriaPictures/cafeteriaPicture" + cafe.getLocationId() + ".png");
        Image image = new Image(file.toURI().toString());
        cafeImage_ID.setImage(image);
    }

    public void switchToFXML(String fxmlFileName, MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        ReviewPageController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData(thisCafeteria);

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
