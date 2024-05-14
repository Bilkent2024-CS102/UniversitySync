package app.controller;

import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class SocialFriendReqUserController {
    @FXML
    private ImageView userImage_ID;
    @FXML
    private Label username_ID;

    public void setData(User friendsMock) {
//      Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
//        userImage_ID.setImage(image);
        username_ID.setText( friendsMock.getName());
    }

    public void acceptRequest(ActionEvent e)
    {
//        User other = (User)e.getSource();
//        User current = SessionManager.getCurrentUser();
//        other.addFriend(current);
//        current.addFriend(other);
//
//        UserDao.updateUser(other);
//        UserDao.updateUser(current); // update method is used to update the friends list, and friend request are deleted
    }

    public void rejectRequest(ActionEvent e)
    {
//        User other = (User)e.getSource();
//        User current = SessionManager.getCurrentUser();
//        current.rejectRequest(other);
//        other.removeOutgoingFriendRequest(current);
//        current.removeIncomingFriendRequest(other);
//        UserDao.update(current);
//        UserDao.update(other); // update method is used to update the friend request sent and received list
    }
}
