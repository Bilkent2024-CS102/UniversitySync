package app.controller;

import app.dao.UserDao;
import app.model.userContent.Reply;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class PostReplyController {
    @FXML
    private TextArea postReplyTextArea_ID;

    @FXML
    private ImageView postReplyUserImage_ID;

    @FXML
    private Label postReplyUsername_ID;

    public void deletePostReply(ActionEvent event) {
    }

    public void setData(Reply reply) {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);

        //reviewPostImage_ID  ... s
      //  postReplyUserImage_ID.setImage( reply.getReplyUserImage() );

        postReplyUsername_ID.setText(UserDao.getUserById(reply.getOwnerId()).getName());
        postReplyTextArea_ID.setText(reply.getMainText());
        File file = new File("src/app/images/profilePictures/profilePicture" + reply.getOwnerId() + ".png");
        Image image = new Image(file.toURI().toString());
        postReplyUserImage_ID.setImage(image);
    }
}