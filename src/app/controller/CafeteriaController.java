package app.controller;

import app.dao.CafeteriaDao;
import app.model.location.cafeteria.Cafeteria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private List<Cafeteria> cafeterias= CafeteriaDao.getAllCafeterias();

    @FXML
    private Button normalButton;
    @FXML
    private Button orderByRatingButton;
    @FXML
    private Button orderByPriceButton;


    public void initialize(URL location, ResourceBundle resources) {

//        cafeVBoxID = new VBox();

        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
//              eventController.setRightEventTabController(this);
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }
//
//    public void normalClicked(ActionEvent event) {
//        cafeterias = CafeteriaDao.getAllCafeterias();
////        cafeVBoxID = new VBox();
//        try {
//            for(int i = 0; i < cafeterias.size(); i++) {
//                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
//                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
//                CafeteriaNameController eventController = fxmlLoader.getController();
//                //now setting data (username, text ...) for each post
//                eventController.setData(cafeterias.get(i));
////              eventController.setRightEventTabController(this);
//                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void orderByRatingClicked(ActionEvent event) throws IOException {
//        cafeterias = CafeteriaDao.getAllCafeteriasByRating(); // need method
//        switchToFXML("src/app/view/CafeteriaPage/CafeInfo.fxml", event);
//    }
//
//    public void orderByPriceClicked(ActionEvent event) {
//        cafeterias = CafeteriaDao.getAllCafeteriasByPrice(); // need method
//        cafeVBoxID = new VBox();
//        try {
//            for(int i = 0; i < cafeterias.size(); i++) {
//                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
//                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
//                CafeteriaNameController eventController = fxmlLoader.getController();
//                //now setting data (username, text ...) for each post
//                eventController.setData(cafeterias.get(i));
////              eventController.setRightEventTabController(this);
//                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void orderCafeByNormal(ActionEvent event) throws IOException {
        cafeterias = CafeteriaDao.getAllCafeterias();
        cafeVBoxID.getChildren().clear();
        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
//              eventController.setRightEventTabController(this);
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderCafeByRating(ActionEvent event) throws IOException {
        cafeterias = CafeteriaDao.getAllCafeteriasByRating();
        cafeVBoxID.getChildren().clear();
        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
//              eventController.setRightEventTabController(this);
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderCafePrice(ActionEvent event) throws IOException {
        cafeterias = CafeteriaDao.getAllCafeteriasByPrice();
        cafeVBoxID.getChildren().clear();
        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
//              eventController.setRightEventTabController(this);
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



//**********************************************************************************************

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


//    //after user puts filter, use this code: (right now both addFilter and close are same)
//    public void applyFilter(ActionEvent event) throws IOException {
//        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        // Close the filter screen
//        stage.close();
//    }
//
//    public void cancelFilter(ActionEvent event) throws IOException {
//        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        stage.close();
//    }

}
