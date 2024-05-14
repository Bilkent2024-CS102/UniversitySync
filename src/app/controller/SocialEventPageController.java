package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private Stage stage;
    private FXMLLoader fxmlLoader;

    @FXML
    private HBox socialEventsHBox_ID;   //h box where we put our events
    private List<EventMock> eventMock;

    public void initialize(URL location, ResourceBundle resources) {
        eventMock = new ArrayList<>(followedEventData());

        try {
            for( int i = 0; i < eventMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/SocialFollowedEvents.fxml").toURI().toURL());
                BorderPane borderPane = fxmlLoader.load();
                SocialEventController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each event
                eventController.setData(eventMock.get(i));
                socialEventsHBox_ID.getChildren().add(borderPane);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private List<EventMock> followedEventData() {
        List<EventMock> ls = new ArrayList<>();
        EventMock event1 = new EventMock();
        event1.setEventTitle("Mars Event 0A-3X");
        event1.setEventText("A new event where all the famous musicians around the world are gonna dance and go to Mars together \n date: 2-2-3033");
        ls.add(event1);

        EventMock event2 = new EventMock();
        event2.setEventTitle("Nep-Earth p4");
        event2.setEventText("Location: planet Neptune \n Time: who cares \n Please Planet Earth do come to the event" );
        ls.add(event2);
        return ls;
        // return ForumPostDao.getPostsByRecency();
    }
}
