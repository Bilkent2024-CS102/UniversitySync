package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TopTabController implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private ImageView topImageIcon_ID;
    @FXML
    private MenuButton profileID;

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
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

    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/app/images/profilePictures/profilePicture" + SessionManager.getCurrentUser().getUserId() + ".png");
        Image image = new Image(file.toURI().toString());
        topImageIcon_ID.setImage(image);

        profileID.setText(SessionManager.getCurrentUser().getName());
    }

    public void switchToSocial(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialMyFriends.fxml", event);
    }
    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Homepage.fxml", event);
    }
    public void switchToProfilePage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileMyPost.fxml", event);
    }
    public void switchToCafeteriaPage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/CafeteriaPage/CafeInfo.fxml", event);
    }
    public void switchToDormitoryPosting(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Dormitory/dormitoryPostingPage.fxml", event);
    }
    public void switchToDormitoryInfo(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Dormitory/dormitoryInfo.fxml", event);
    }
    public void switchToForumPage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ForumPage/Forum.fxml", event);
    }
    public void switchToLikedPage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileLikedPost.fxml", event);
    }
    public void switchToContactUsPage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileContactUs.fxml", event);
    }
    public void logout(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/LoginPage.fxml", event);
        SessionManager.setCurrentUser(null);
    }
}