package app.controller;

import app.model.userContent.post.EventPost;
import app.dao.EventPostDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

public class EventController {
    EventPost post;

    @FXML
    private TextArea eventTexArea_ID;
    @FXML
    private ToggleButton eventFollowButton_ID;


    public void setData(EventPost event)
    {
        post = event;
        eventTexArea_ID.setText(event.getMainText());
        eventTexArea_ID.setEditable(false);

        boolean isFollowed = EventPostDao.doesUserFollow(event.getUserContentItemId(), SessionManager.getCurrentUser().getUserId());
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
        }
        else {
            eventFollowButton_ID.setText("Follow");
            EventPostDao.removeFollower(post.getUserContentItemId(), SessionManager.getCurrentUser().getUserId());
        }
    }
}
