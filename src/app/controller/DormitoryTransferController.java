package app.controller;

import java.sql.Date;
import java.time.LocalDate;

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
        DormTransferPost post = new DormTransferPost(SessionManager.getCurrentUser(), postDetails, date, date, postHeading, SessionManager.getCurrentUser().getRoom());
        DormitoryPostDao.addPost(post);
    }

    public void openPost(ActionEvent e)
    {
        DormitoryTransferPost post = e.getSource();
        // need to make UI element for post here
    }

    public void messageDormOwner(ActionEvent e)
    {
        DormitoryTransferPost post = e.getSource();
        User dormOwner = post.getOwner();
        // UI method to open messages
    }

    public void transferDorm(ActionEvent e)
    {
        User user1 = e.getSource().getOwner();
        Room room1 = whoPosted.getRoom();
        User current = SessionManager.getCurrentUser();
        Room room2 = current.getRoom();

        whoPosted.setRoom(room2);
        current.setRoom(room1);
        DormitoryDao.update(whoPosted);
        DormitoryDao.update(current);
    }
}
