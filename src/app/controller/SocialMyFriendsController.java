package app.controller;

import app.dao.UserDao;
import app.model.FriendRequest;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SocialMyFriendsController {

    public void displayFriendRequests(ActionEvent e)
    {
        User currentUser = SessionManager.getCurrentUser();
        ArrayList<FriendRequest> friendRequests = UserDao.getFriendRequests(currentUser);

        // Need to make UI elements for each friend request
    }

    public void unfriend(ActionEvent e)
    {
        User other = (User) e.getSource();
        UserDao.removeFriend(SessionManager.getCurrentUser().getUserId(), other.getUserId());
    }
}
