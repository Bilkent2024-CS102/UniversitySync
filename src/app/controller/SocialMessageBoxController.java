package app.controller;

import app.dao.MessageDao;
import app.model.User;
import app.model.userContent.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SocialMessageBoxController {

    @FXML
    private VBox MessageBoxVBox_ID;
    @FXML
    private TextField typeMessageTextfield_ID;

    private FXMLLoader fxmlLoader;

    private List<Message> textList;
    private User associatedFriend;


    public void sendText(ActionEvent event) throws IOException {
        String text = typeMessageTextfield_ID.getText();
        typeMessageTextfield_ID.clear();

        Message message = new Message(
                SessionManager.getCurrentUser().getUserId(),
                associatedFriend.getUserId(),
                text
        );

        fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/eachMessageText.fxml").toURI().toURL());
        HBox friendHBox = fxmlLoader.load();

        SocialEachTextController eventController = fxmlLoader.getController();
        eventController.setData(message);

        if(message.getSenderId() == SessionManager.getCurrentUser().getUserId()) {friendHBox.setTranslateX(350);}
        else {friendHBox.setTranslateX(30);}

        //adds each message to the vBox
        MessageBoxVBox_ID.getChildren().add(friendHBox);
    }

    public void setData(User associatedFriend) {
        this.associatedFriend = associatedFriend;

        textList = MessageDao.getMessagesBetween(
                SessionManager.getCurrentUser().getUserId(),
                associatedFriend.getUserId()
        );

        try {
            for(int i = 0; i < textList.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/eachMessageText.fxml").toURI().toURL());
                HBox friendHBox = fxmlLoader.load();

                SocialEachTextController eventController = fxmlLoader.getController();
                eventController.setData(textList.get(i) );

                if(textList.get(i).getSenderId() == SessionManager.getCurrentUser().getUserId()) {
                    friendHBox.setTranslateX(350);
                }
                else {friendHBox.setTranslateX(30);}
                //adds each message to the vBox
                MessageBoxVBox_ID.getChildren().add(friendHBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}