package app.controller;

import app.dao.DormTransferPostDao;
import app.model.userContent.post.DormTransferPost;
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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
//import com.mysql.cj.Session;
import javafx.stage.StageStyle;

public class DormitoryPostingPageController implements Initializable{
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox dormitoryPostingPageVbox_ID = new VBox();
    @FXML
    private TextField headerField;
    @FXML
    private TextArea postMessageArea;

    private List<DormTransferPost> posts;

    public void initialize(URL location, ResourceBundle resources) {
        posts = DormTransferPostDao.getAllPosts();

        try {
            for(int i = 0; i < posts.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/dormPosts.fxml").toURI().toURL());
                Pane postPane = fxmlLoader.load();
                DormitoryPostController dormController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                dormController.setData(posts.get(i));
                dormitoryPostingPageVbox_ID.getChildren().add(postPane);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayMakePostPopup(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/DormPostingPopup.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL);
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        filterStage.showAndWait();
    }

    public void submitPost(ActionEvent event) throws IOException {

        DormTransferPost dtp = new DormTransferPost(
                SessionManager.getCurrentUser().getUserId(),
                postMessageArea.getText(),
                new Date(),
                new Date(),
                headerField.getText(),
                SessionManager.getCurrentUser().getRoomId()
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Post created successfully!");
        alert.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
        alert.showAndWait();

        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        refresh(event);
    }

    public void refresh(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/Dormitory/dormitoryPostingPage.fxml", event);
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
}