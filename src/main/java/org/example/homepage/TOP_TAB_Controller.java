package org.example.homepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;


public class TOP_TAB_Controller {
        private Stage stage;
        private Scene scene;
        private FXMLLoader fxmlLoader;

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = fxmlLoader.load();

        if (event.getSource() instanceof Node) {                                    //FOR Buttons
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        else if (event.getSource() instanceof MenuItem menuItem)  {                 //FOR MenuButtons
            stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }
    public void switchToMyFriends(ActionEvent event) throws IOException {
        switchToFXML("SocialPage/socialMyFriends.fxml", event);
    }
    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToFXML("Homepage.fxml", event);
    }
    public void switchToProfilePage(ActionEvent event) throws IOException {
        switchToFXML("ProfilePage/profileMyPost.fxml", event);
    }
    public void switchToCafeteriaPage(ActionEvent event) throws IOException {
        /*switchToFXML("CafeteriaPage/FoodPage.fxml", event);*/
        switchToFXML("FoodPage.fxml", event);

    }
    public void switchToForumPage(ActionEvent event) throws IOException {
        switchToFXML("", event);
    }
    public void switchToLikedPage(ActionEvent event) throws IOException {
        switchToFXML("", event);
    }
    public void switchToContactUsPage(ActionEvent event) throws IOException {
        switchToFXML("", event);
    }
    public void logout(ActionEvent event) throws IOException {
        switchToFXML("", event);
    }

}