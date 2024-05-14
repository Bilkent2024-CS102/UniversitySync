package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SocialMessageBoxController {
    private FXMLLoader fxmlLoader;
    @FXML
    private VBox MessageBoxVBox_ID;
    @FXML
    private TextField typeMessageTextfield_ID

    private List< String > textList;


    //when you type your new message and press enter this method acts
    public void sendText(ActionEvent event) {
        String text = typeMessageTextfield_ID.getText();

        fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/eachMessageText.fxml").toURI().toURL());
        HBox friendHBox = fxmlLoader.load();

        SocialEachTextController eventController = fxmlLoader.getController();
        eventController.setData( text ) );

        if( i send message ) {
            friendHBox.setTranslateX(200);
        }
        //adds each message to the vBox
        MessageBoxVBox_ID.getChildren().add(friendHBox);

    }

    public void setData(MessageFriendsMock associatedFriend) {

        textList = associatedFriend.getTextList ....

        try {
            for(int i = 0; i < textList.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/eachMessageText.fxml").toURI().toURL());
                HBox friendHBox = fxmlLoader.load();

                SocialEachTextController eventController = fxmlLoader.getController();
                eventController.setData(  textList.get(i) );

                if( i send message ) {
                    friendHBox.setTranslateX(200);
                }
                else {
                    friendHBox.setTranslateX(30);
                }
                //adds each message to the vBox
                MessageBoxVBox_ID.getChildren().add(friendHBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
