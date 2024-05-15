package app.controller;

import app.dao.ForumPostDao;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import com.mysql.cj.Session;
import javafx.stage.StageStyle;

public class PostReplyPageController {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox postReplyVBox_ID = new VBox();
    private List<Reply> postReviewMock;
    private ForumPost thisPost;


    public void setData(ForumPost post)
    {
        thisPost = post;
        postReviewMock = ForumPostDao.getReplies(thisPost.getUserContentItemId());

        try {
            for (int i = 0; i < postReviewMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/PostReplyEach.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                PostReplyController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(postReviewMock.get(i));
                postReplyVBox_ID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToLastPage_ID(ActionEvent event) {
    }

    public void seeAttachedFiles(ActionEvent event) {
    }

}
