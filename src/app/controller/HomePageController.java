package app.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import app.dao.UserDao;
//import app.model.userContent.Reply;
//import app.model.userContent.post.ForumPost;
//import app.dao.ForumPostDao;
import app.dao.DBConnectionManager;
import app.dao.ForumPostDao;
import app.dao.UserDao;
import app.model.userContent.post.ForumPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageController implements Initializable
{
    private Stage stage;
    private static Stage stage2;
    private Scene scene;
    private static Scene scene2;
    private FXMLLoader fxmlLoader;
    private static FXMLLoader fxmlLoader2;

    // ******************************************************************* TEST ***************************************
    @FXML
    private VBox postVBoxID = new VBox();
    private List<ForumPost> posts;

    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            DBConnectionManager.initializeConnection("jdbc:mysql://127.0.0.1:3306/?user=root", "root", "ben123**AA");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        //SessionManager.setCurrentUser(UserDao.getUserById(1));
        posts = ForumPostDao.getForumPostsByRecency();

        try {
            for(int i = 0; i < posts.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Post.fxml").toURI().toURL());
                Pane postPane = fxmlLoader.load();              //the pane that contains posts in the post fxml
                PostController postController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                postController.setData(posts.get(i));
                postVBoxID.getChildren().add(postPane);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Creating a list of mock data for now (deleting later)
//    private List<PostMock> data() {
//        List<PostMock> ls = new ArrayList<>();
//        PostMock post = new PostMock();
//        post.setUsername("Musa");
//        post.setPostText("hello i am musa and");
//        ls.add(post);
//
//        PostMock post2 = new PostMock();
//        post2.setUsername("Atilla");
//        post2.setPostText("hello i am atilla and");
//        ls.add(post2);
//        return ls;
//
//        // return ForumPostDao.getPostsByRecency();
//    }
// ******************************************************************* TEST ***************************************

    public void refreshAndShowPosts(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(new File("src/app/view/Homepage.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

//        ArrayList<ForumPost> posts = ForumPostDao.getPostsByRecency();
//        displayRecentPosts(posts);
    }

    public static void refresh(ActionEvent event) throws IOException {

        fxmlLoader2 = new FXMLLoader(new File("src/app/view/Homepage.fxml").toURI().toURL());
        Parent root = fxmlLoader2.load();
        stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.setFullScreen(true);
        stage2.show();

//        ArrayList<ForumPost> posts = ForumPostDao.getPostsByRecency();
//        displayRecentPosts(posts);
    }


}
