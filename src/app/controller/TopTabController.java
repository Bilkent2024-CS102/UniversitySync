package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class TopTabController implements Initializable  {
        private Stage stage;
        private Scene scene;
        private FXMLLoader fxmlLoader;

// ******************************************************************* TEST ***************************************
        @FXML
        private VBox postVBoxID = new VBox();
        private List<Post> posts;

    public void initialize(URL location, ResourceBundle resources) {
        posts = new ArrayList<>(data());   //each time test controller class is called, we make a new arraylist and display the data

        try {
        for(int i = 0; i < posts.size(); i++) {

            fxmlLoader = new FXMLLoader(new File("src/app/view/Post.fxml").toURI().toURL());

            //fxmlLoader.setLocation(getClass().getResource());

            Pane postPane = null;  //the pane that contains posts in the post fxml
            postPane = fxmlLoader.load();

            PostController postController = fxmlLoader.getController();
            postController.setData(posts.get(i));

            //npw adding post to the vbox
            postVBoxID.getChildren().add(postPane);
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Post> data() {
        List<Post> ls = new ArrayList<>();
        Post post = new Post();
        post.setUsername("Musa");
        post.setPostText("hello i am musa and");
        ls.add(post);

        Post post2 = new Post();
        post2.setUsername("Atilla");
        post2.setPostText("hello i am atilla and");
        ls.add(post2);
        return ls;
    }

//    ******************************************************************* TEST ***************************************
    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        if (event.getSource() instanceof Node) {                                    //FOR Buttons
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        else if (event.getSource() instanceof MenuItem menuItem)  {                 //FOR MenuButtons
            stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }
    public void switchToSocial(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialMyFriends.fxml", event);
    }
    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Homepage.fxml", event);
    }
    public void switchToProfilePage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileMyPost.fxml", event);
    }
    public void switchToCafeteriaPage(ActionEvent event) throws IOException {
        /*switchToFXML("CafeteriaPage/FoodPage.fxml", event);*/
        switchToFXML("src/app/view/CafeteriaPage/FoodPage.fxml", event);
    }
    public void switchToDormitoryPosting(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Dormitory/dormitoryPosting.fxml", event);
    }
    public void switchToDormitoryTransfer(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Dormitory/dormitoryTransfer.fxml", event);
    }
    public void switchToForumPage(ActionEvent event) throws IOException {
        switchToFXML("", event);
    }
    public void switchToLikedPage(ActionEvent event) throws IOException {
        switchToFXML("", event);
    }
    public void switchToContactUsPage(ActionEvent event) throws IOException {
        switchToFXML("", event);
    }
    public void logout(ActionEvent event) throws IOException {
        switchToFXML("", event);
        // SessionManager.logoutUser();
    }

}