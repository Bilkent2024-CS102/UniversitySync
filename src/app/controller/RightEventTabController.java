package app.controller;

import app.dao.DBConnectionManager;
import app.dao.EventPostDao;
import app.dao.UserDao;
import app.model.User;
import app.model.userContent.post.EventPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
//import com.mysql.cj.Session;
import javafx.stage.StageStyle;

public class RightEventTabController implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    //*************************************************************************************
    @FXML
    private ToggleButton eventFollowButton_ID;
    @FXML
    private VBox Event_VBox_ID = new VBox();
    @FXML
    private TextField searchUserField;
    //right Event VBox where we put our events
    private List<EventPost> eventMock;

    @FXML
    private TextField eventNameTextField;
    @FXML
    private TextField eventLocationTextField;
    @FXML
    private DatePicker eventDatePicker;
    @FXML
    private TextField eventTitleTextField;
    @FXML
    private TextArea eventDescriptionTextField;

    private User searchedUser;

    public void initialize(URL location, ResourceBundle resources) {
        SessionManager.setCurrentUser(UserDao.getUserById(1));
        eventMock = EventPostDao.getAllEventPosts();

        try {
            for( int i = 0; i < eventMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Event_Tab/Events.fxml").toURI().toURL());
                VBox vbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                EventController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(eventMock.get(i));
//              eventController.setRightEventTabController(this);
                Event_VBox_ID.getChildren().add(vbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchUser(ActionEvent event) throws IOException {
        ArrayList<User> users = UserDao.getUsers();
        String entered = searchUserField.getText();
        searchedUser = null;

        for (User user : users)
        {
            if (user.getName().toLowerCase().equals(entered.toLowerCase()))
            {
                searchedUser = user;
            }
        }

        if (searchedUser != null)
        {
            switchToFriendProfile(event);
        }
        // Just show its profile.
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        otherUserProfileDisplayController newController = fxmlLoader.getController();
        newController.setData(searchedUser);

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


    public void switchToFriendProfile(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/FriendProfile.fxml", event);
    }


    public void displayAddEventPopup (ActionEvent event) throws IOException {

        // Load the FXML file for the filter screen
        fxmlLoader = new FXMLLoader(new File("src/app/view/Event_Tab/Add_New_Event.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        // Show the filter screen
        filterStage.showAndWait(); // This will block interaction with the main window
    }

    public void addEvent(ActionEvent e) throws IOException {
        String eventName = eventNameTextField.getText();
        String location = eventLocationTextField.getText();
        LocalDate date = eventDatePicker.getValue();
        String title = eventTitleTextField.getText();
        String description = eventDescriptionTextField.getText();
        EventPost event = new EventPost(SessionManager.getCurrentUser().getUserId(), description,
                new java.sql.Timestamp(new Date().getTime()), new java.sql.Timestamp(new Date().getTime()), title,
                location, java.sql.Date.valueOf(date));
        stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void cancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
