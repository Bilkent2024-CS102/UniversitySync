package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import java.io.File;
import java.io.IOException;

public class SocialMyFriendsController {

//*******************************************************************************************************************
    @FXML
    private ImageView friendProfileImageID;
    @FXML
    private Label friendUsernameID;

    public void setData(FriendsMock friend) {
//      Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
//      friendProfileImageID.setImage(image);
        friendUsernameID.setText(friend.getFriendUsername());
    }

//*******************************************************************************************************************

    public void messageFriendButton (ActionEvent event) throws IOException {
       // 2 Steps:
                // Takes you to the all messages social page
                // and opens the message pane of this particular user

    }
    public void unFriendButton (ActionEvent event) throws IOException {

    }
}

class FriendsMock {
    private String friendUsername;
    private String profileImageSrc;

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

   /* public String getProfileImageSrc() {    // this is similar in other controllers as well
        return profileImageSrc;
    }
    public void setProfileImageSrc() {    // this is similar in other controllers as well
       this.profileImageSrc = ....;
    }*/
}