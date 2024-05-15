package app.controller;

import app.dao.UserDao;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import java.io.File;
import java.io.File;
import java.io.IOException;

public class SocialMyFriendsController {

    //*******************************************************************************************************************
    @FXML
    private ImageView friendProfileImageID;
    @FXML
    private Label friendUsernameID;
    @FXML
    private Button messageButton;
    @FXML
    private Button unfriendButton;

    private User thisFriend;

    private FXMLLoader fxmlLoader;
    private Stage stage;
    private Scene scene;

    public void setData(User friend) {
//      Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
//      friendProfileImageID.setImage(image);
        thisFriend = friend;
        friendUsernameID.setText(friend.getName());
    }

//*******************************************************************************************************************

    public void messageFriendButton (ActionEvent event) throws IOException {
       switchToMessages(event);
        // 2 Steps:
                // Takes you to the all messages social page
                // and opens the message pane of this particular user

    }
    public void unFriendButton (ActionEvent event) throws IOException {
        UserDao.removeFriend(SessionManager.getCurrentUser().getUserId(), thisFriend.getUserId());
        refresh(event);
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToMessages(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialAllMessages.fxml", event);
    }

    public void refresh(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialMyFriends.fxml", event);
    }
}
//
//class FriendsMock {
//    private String friendUsername;
//    private String profileImageSrc;
//
//    public String getFriendUsername() {
//        return friendUsername;
//    }
//
//    public void setFriendUsername(String friendUsername) {
//        this.friendUsername = friendUsername;
//    }
//
//   /* public String getProfileImageSrc() {    // this is similar in other controllers as well
//        return profileImageSrc;
//    }
//    public void setProfileImageSrc() {    // this is similar in other controllers as well
//       this.profileImageSrc = ....;
//    }*/
//}