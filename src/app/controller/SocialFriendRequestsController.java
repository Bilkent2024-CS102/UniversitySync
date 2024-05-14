package app.controller;

import java.util.ArrayList;

import app.controller.SessionManager;
import app.model.User;
import app.dao.UserDao;

import javafx.event.ActionEvent;


public class SocialFriendRequestsController {
//    public void acceptRequest(ActionEvent e)
//    {
//        User other = (User)e.getSource();
//        User current = SessionManager.getCurrentUser();
//        other.addFriend(current);
//        current.addFriend(other);
//
//        UserDao.updateUser(other);
//        UserDao.updateUser(current); // update method is used to update the friends list, and friend request are deleted
//    }
//
//    public void rejectRequest(ActionEvent e)
//    {
//        User other = (User)e.getSource();
//        User current = SessionManager.getCurrentUser();
//        current.rejectRequest(other);
//        other.removeOutgoingFriendRequest(current);
//        current.removeIncomingFriendRequest(other);
//        UserDao.update(current);
//        UserDao.update(other); // update method is used to update the friend request sent and received list
//    }
}
