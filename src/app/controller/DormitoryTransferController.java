package app.controller;

import java.sql.Date;
import java.time.LocalDate;

import app.dao.UserDao;
import app.model.User;
import app.model.location.Room;
import app.model.userContent.post.DormTransferPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import app.dao.DormTransferPostDao;
import app.dao.DormitoryDao;

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
        DormTransferPost post = new DormTransferPost(SessionManager.getCurrentUser().getUserId(), postDetails, date, date, postHeading, SessionManager.getCurrentUser().getRoom().getRoomId());
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
        Room room1 = whoPosted.getRoom();
        User current = SessionManager.getCurrentUser();
        Room room2 = current.getRoom();

        whoPosted.setRoom(room2);
        current.setRoom(room1);
        UserDao.updateUser(whoPosted);
        UserDao.updateUser(current);
    }
}
