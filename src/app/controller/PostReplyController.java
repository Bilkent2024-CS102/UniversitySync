package app.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

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

    public void setData(PostReplyMock reply) {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);

        //reviewPostImage_ID  ... s
        postReplyUsername_ID.setText( reply.getReplyTopUsername());
      //  postReplyUserImage_ID.setImage( reply.getReplyUserImage() );
        postReplyTextArea_ID.setText( reply.getReplyText() );

    }
}
class PostReplyMock {
    private String replyTopUsername;
    private String replyText;
    private ImageView replyUserImage ;


    public String getReplyTopUsername() {
        return replyTopUsername;
    }

    public void setReplyTopUsername(String replyTopUsername) {
        this.replyTopUsername = replyTopUsername;
    }

    public ImageView getReplyUserImage() {
        return replyUserImage;
    }

    public void setReplyUserImage(ImageView replyUserImage) {
        this.replyUserImage = replyUserImage;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
}