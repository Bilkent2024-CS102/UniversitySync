package app.controller;

import app.dao.MessageDao;
import app.dao.UserDao;
import app.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SocialAllMessagesController implements Initializable {
    private FXMLLoader fxmlLoader;

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public BorderPane getBorderTextBox_ID() {
        return borderTextBox_ID;
    }

    @FXML
    private VBox messageLeftDisplayVBox_ID = new VBox();
    @FXML
    private BorderPane borderTextBox_ID;

    private List <User> messageFriends;

    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Integer> userIds = MessageDao.getUserIdsMessagedWith(SessionManager.getCurrentUser().getUserId());
        messageFriends = new ArrayList<>();
        for (int i : userIds)
        {
            messageFriends.add(UserDao.getUserById(i));
        }

        try {
            for(int i = 0; i < messageFriends.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/friendToMessageHBox.fxml").toURI().toURL());
                HBox friendHBox = fxmlLoader.load();
                SocialMessageOneUserController eventController = fxmlLoader.getController();
                eventController.setData(messageFriends.get(i), this);
                messageLeftDisplayVBox_ID.getChildren().add(friendHBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}