package app.controller;

import app.model.location.Dormitory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.io.File;
import java.io.IOException;


public class DormitoryNameController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;


    @FXML
    private Button DormName_ID;
    @FXML
    private Label DormRating_ID;
    @FXML
    private Label dormCampus_ID;

    // ************  Particular Dorm Associated with this page  ****************
    private Dormitory thisDorm;

    public void setData(Dormitory dorm) {

        thisDorm = dorm;

        DormName_ID.setText(dorm.getName());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String text = (dorm.getRating() < 0) ? ("No Reviews") : ("" + decimalFormat.format(dorm.getRating()));
        DormRating_ID.setText(text);
        dormCampus_ID.setText("" + dorm.getCampus().getName());

    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        DormitoryDetailController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData(thisDorm);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToDormDetails(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Dormitory/dormitoryDetails.fxml", event);
    }
}