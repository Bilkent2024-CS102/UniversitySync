package app.controller;

import app.dao.ForumPostDao;
import app.model.userContent.post.ForumPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileLikedPostsController implements Initializable
{
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox postVBoxID = new VBox();

    private List<ForumPost> posts;

    public void initialize(URL location, ResourceBundle resources) {
        posts = ForumPostDao.getLikedPosts(SessionManager.getCurrentUser().getUserId());

        try {
            for(int i = 0; i < posts.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Post.fxml").toURI().toURL());
                Pane postPane = fxmlLoader.load();
                PostController postController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                postController.setData(posts.get(i));
                postVBoxID.getChildren().add(postPane);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}