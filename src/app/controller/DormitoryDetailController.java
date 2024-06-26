package app.controller;

import app.model.location.Dormitory;
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
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class DormitoryDetailController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Dormitory dorm;

    @FXML
    private Label dormName_ID;
    @FXML
    private ImageView dormImage_ID;
    @FXML
    private TextArea dormDescription_ID;
    @FXML
    private TextField dormRateTextField_ID;
    @FXML
    private Label dormCampus_ID;

    public void setData(Dormitory dorm) {

        this.dorm = dorm;
        dormName_ID.setText(dorm.getName());
        dormDescription_ID.setText(dorm.getDescription());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String text = (dorm.getRating() < 0) ? ("No Reviews") : ("" + decimalFormat.format(dorm.getRating()));
        dormRateTextField_ID.setText(text);
        dormCampus_ID.setText(dorm.getCampus().getName());

        dormDescription_ID.setEditable(false);
        dormRateTextField_ID.setEditable(false);
        File file = new File("src/app/images/dormitoryPictures/dormitoryPicture" + dorm.getLocationId() + ".png");
        Image image = new Image(file.toURI().toString());
        dormImage_ID.setImage(image);
    }

    public void switchToFXML(String fxmlFileName, MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        ReviewPageController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData(dorm);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToReviews(MouseEvent event) throws IOException {
        switchToFXML("src/app/view/ReviewPage.fxml", event);
    }
}
