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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ForumController implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox forumVBoxID = new VBox();
    private ArrayList<ForumPost> posts;

    @FXML
    private TextField titleField;
    @FXML
    private TextArea postTextArea;

    @FXML
    private Button switchPostsButton;
    @FXML
    private Button makePostButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        posts = ForumPostDao.getForumPostsByRecency();
        try {
            for(int i = 0; i < posts.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Post.fxml").toURI().toURL());
                Pane postPane = fxmlLoader.load();              //the pane that contains posts in the post fxml
                PostController postController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                postController.setData(posts.get(i));
                forumVBoxID.getChildren().add(postPane);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchPosts(ActionEvent event) throws IOException {
        if (switchPostsButton.getText().equals("All posts"))
        {
            switchPostsButton.setText("Friends' posts");
            posts = ForumPostDao.getForumPostsByRecency();
            forumVBoxID.getChildren().clear();
            try {
                for(int i = 0; i < posts.size(); i++) {
                    fxmlLoader = new FXMLLoader(new File("src/app/view/Post.fxml").toURI().toURL());
                    Pane postPane = fxmlLoader.load();              //the pane that contains posts in the post fxml
                    PostController postController = fxmlLoader.getController();
                    //now setting data (username, text ...) for each post
                    postController.setData(posts.get(i));
                    forumVBoxID.getChildren().add(postPane);  //now adding post (pane) to the vbox
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            switchPostsButton.setText("All posts");
            posts = ForumPostDao.getForumPostsOfFriends(SessionManager.getCurrentUser().getUserId());
            forumVBoxID.getChildren().clear();
            try {
                for(int i = 0; i < posts.size(); i++) {
                    fxmlLoader = new FXMLLoader(new File("src/app/view/Post.fxml").toURI().toURL());
                    Pane postPane = fxmlLoader.load();              //the pane that contains posts in the post fxml
                    PostController postController = fxmlLoader.getController();
                    //now setting data (username, text ...) for each post
                    postController.setData(posts.get(i));
                    forumVBoxID.getChildren().add(postPane);  //now adding post (pane) to the vbox
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void displayMakePostPopup(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/ForumPage/MakePostPopup.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL);
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        filterStage.showAndWait();
    }

    public void submitPost(ActionEvent event) throws IOException {
        ForumPost post = new ForumPost(
                SessionManager.getCurrentUser().getUserId(),
                postTextArea.getText(),
                new Date(),
                new Date(),
                titleField.getText()
                );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Post created succesfully!");
        alert.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
        alert.showAndWait();

        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        refresh(event);
    }

    public void refresh(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ForumPage/Forum.fxml", event);
    }
}
