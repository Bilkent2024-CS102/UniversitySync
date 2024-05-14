package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SocialAllMessagesController implements Initializable {
    private FXMLLoader fxmlLoader;
          //to fill the message box area

    private List <MessageFriendsMock> messageFriendMock;
    @FXML
    private VBox messageLeftDisplayVBox_ID = new VBox();

    public void initialize(URL location, ResourceBundle resources) {
        messageFriendMock = new ArrayList<>(data());

        try {
            for(int i = 0; i < messageFriendMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/friendToMessageHBox.fxml").toURI().toURL());
                HBox friendHBox = fxmlLoader.load();
                SocialMessageOneUserController eventController = fxmlLoader.getController();
                eventController.setData(messageFriendMock.get(i));
                messageLeftDisplayVBox_ID.getChildren().add(friendHBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<MessageFriendsMock> data() {

//        List<MessageFriendsMock> tss = new ArrayList<>();
        return new ArrayList<>();

        // MOCK MESSAGE DATA WOULD BE PRESENT HERE
    }


}
