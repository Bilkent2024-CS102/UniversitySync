package app.controller;

import app.dao.UserDao;
import app.model.userContent.post.EventPost;
import app.dao.EventPostDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class EventController {
    EventPost post;

    @FXML
    private TextArea eventTexArea_ID;
    @FXML
    private ToggleButton eventFollowButton_ID;


    public void setData(EventPost event)
    {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);
        post = event;
        eventTexArea_ID.setText(event.getMainText());
        eventTexArea_ID.setEditable(false);

        boolean isFollowed = EventPostDao.doesUserFollow(SessionManager.getCurrentUser().getUserId(), event.getUserContentItemId());
        if (isFollowed)
        {
            eventFollowButton_ID.setText("Unfollow");
        }
        else
        {
            eventFollowButton_ID.setText("Follow");
        }
    }

    public void followButton(ActionEvent e)
    {
        if (eventFollowButton_ID.getText().equals("Follow")) {
            eventFollowButton_ID.setText("Unfollow");
            EventPostDao.addFollower(post.getUserContentItemId(), SessionManager.getCurrentUser().getUserId());
            // eventTexArea_ID.setText( eventTexArea_ID.getText() + "\n You are now following.");
        }
        else {
            eventFollowButton_ID.setText("Follow");
            EventPostDao.removeFollower(post.getUserContentItemId(), SessionManager.getCurrentUser().getUserId());
        }
    }
}
