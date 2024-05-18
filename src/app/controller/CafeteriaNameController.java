package app.controller;
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
import java.text.DecimalFormat;

import app.model.location.cafeteria.Cafeteria;

public class CafeteriaNameController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private Button food_cafeteriaName_ID;
    @FXML
    private Label foodPage_CafeRating_ID;
    @FXML
    private Label foodPage_CafePriceRange_ID;

    // ************  Particular Cafe Associated with this page  ****************
    private Cafeteria thisCafe;

    public void setData(Cafeteria cafe) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        thisCafe = cafe;

        food_cafeteriaName_ID.setText(cafe.getName());
        String text = (cafe.getRating() < 0) ? ("No Reviews") : ("" + decimalFormat.format(cafe.getRating()));
        foodPage_CafeRating_ID.setText(text);
        foodPage_CafePriceRange_ID.setText(cafe.getMinPrice() + "-" + cafe.getMaxPrice() + " TL");
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
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToCafeDetail(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/CafeteriaPage/CafeDetail.fxml", event);
    }
}