package app.controller;

import java.sql.Date;
import java.time.LocalDate;

import app.dao.UserDao;
import app.model.User;
import app.model.userContent.post.DormTransferPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DormitoryTransferController {
    
    @FXML
    private TextField dormDescription;
    @FXML
    private TextField heading;

    public void addPost(ActionEvent e)
    {
        String postDetails = dormDescription.getText();
        String postHeading = heading.getText();
        Date date = Date.valueOf(LocalDate.now());
        DormTransferPost post = new DormTransferPost(SessionManager.getCurrentUser().getUserId(), postDetails, date, date, postHeading, SessionManager.getCurrentUser().getRoomId());
    }

    public void openPost(ActionEvent e)
    {
        DormTransferPost post = (DormTransferPost) e.getSource();
        // need to make UI element for post here
    }

    public void messageDormOwner(ActionEvent e)
    {
        DormTransferPost post = (DormTransferPost) e.getSource();
        int dormPostOwnerId = post.getOwnerId();
        // UI method to open messages
    }

    public void transferDorm(ActionEvent e)
    {
        User whoPosted = UserDao.getUserById(((DormTransferPost)e.getSource()).getOwnerId());
        int room1Id = whoPosted.getRoomId();
        User current = SessionManager.getCurrentUser();
        int room2Id = current.getRoomId();

        whoPosted.setRoomId(room2Id);
        current.setRoomId(room1Id);
        UserDao.updateUser(whoPosted);
        UserDao.updateUser(current);
    }
}
