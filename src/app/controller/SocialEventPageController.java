package app.controller;

import app.dao.EventPostDao;
import app.model.userContent.post.EventPost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SocialEventPageController implements Initializable {
    private FXMLLoader fxmlLoader;

    @FXML
    private HBox socialEventsHBox_ID;   //H box where we put our events

    private List<EventPost> eventMock;

    public void initialize(URL location, ResourceBundle resources) {
        eventMock = EventPostDao.getEventsOfUser(SessionManager.getCurrentUser().getUserId());

        try {
            if (eventMock != null)
            {
                for( int i = 0; i < eventMock.size(); i++) {
                    fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/SocialFollowedEvents.fxml").toURI().toURL());
                    BorderPane borderPane = fxmlLoader.load();
                    SocialEventController eventController = fxmlLoader.getController();
                    //now setting data (username, text ...) for each event
                    eventController.setData(eventMock.get(i));
                    socialEventsHBox_ID.getChildren().add(borderPane);  //now adding post (pane) to the vbox
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You are currently not following any events");
                alert.showAndWait();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}