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
    // private Label cafeNameLabel;
    // private Label ratingLabel;
    // private Label priceRangeLabel;
    // public void setData(Cafeteria cafe)
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




//****************************************************************************************************8
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
    private List<CafeMock> data() {
        List<CafeMock> ls = new ArrayList<>();
        CafeMock cafe1 = new CafeMock();
        cafe1.setCafeName("Marmara");
        cafe1.setCafeRating("Rating 3/4");
        cafe1.setCafePriceRange("100$-200$");
        ls.add(cafe1);

        CafeMock cafe2 = new CafeMock();
        cafe2.setCafeName("Bilka");
        cafe2.setCafeRating("Rating 3.9/4");
        cafe2.setCafePriceRange("50$-150");
        ls.add(cafe2);
        return ls;
        // return ForumPostDao.getPostsByRecency();
    }


//**********************************************************************************************

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
}
