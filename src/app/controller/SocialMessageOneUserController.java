package app.controller;

import app.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import java.io.File;
import java.io.File;
import java.io.IOException;

public class SocialMessageOneUserController {
    private FXMLLoader fxmlLoader;
    private SocialAllMessagesController largeController;

    @FXML
    private ImageView friendToMessageImage_ID;
    @FXML
    private Label friendToMessageName_ID;//place where we display message box
    private User associatedFriend;



    public void setData( User friend, SocialAllMessagesController largeController) {
        this.largeController = largeController;
        associatedFriend = friend;
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);
        friendToMessageName_ID.setText(friend.getName());
    }

    public void displayTextScreen(MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/messageBox.fxml").toURI().toURL());
        Pane newScene = fxmlLoader.load();   //the pane on which texts would be displayes

        SocialMessageBoxController eventController = fxmlLoader.getController();
        eventController.setData(associatedFriend);

        largeController.getBorderTextBox_ID().setCenter(newScene);
    }

}

//class User {
//    private String friendUsername;
//    private String profileImageSrc;
//    private message etc;
//
//    public String getFriendUsername() {
//        return friendUsername;
//    }
//
//    public void setFriendUsername(String friendUsername) {
//        this.friendUsername = friendUsername;
//    }
//
//    public String getProfileImageSrc() {
//        return profileImageSrc;
//    }
//
//    public void setProfileImageSrc(String profileImageSrc) {
//        this.profileImageSrc = profileImageSrc;
//    }
//}