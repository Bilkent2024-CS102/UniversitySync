package app.controller;


import app.dao.EventPostDao;
import app.model.User;
import app.model.userContent.post.EventPost;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class SocialEventController {

    @FXML
    private TextArea eventTextArea_ID;

    @FXML
    private Label eventTitle_ID;

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    private EventPost thisEvent;

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
    public void refresh(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialEventsPage.fxml", event);
    }

    @FXML
    public void unfollowAndRemoveEvent(ActionEvent event) throws IOException
    {
        User u = SessionManager.getCurrentUser();
        EventPostDao.removeFollower(thisEvent.getUserContentItemId(), u.getUserId());
        refresh(event);
    }

    public void setData(EventPost event) {
        thisEvent = event;
        eventTitle_ID.setText(event.getHeading());
        eventTextArea_ID.setText(event.getMainText());
    }
}
