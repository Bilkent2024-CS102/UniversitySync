package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SocialAllMessagesController {
    private FXMLLoader fxmlLoader;
    @FXML
    private BorderPane borderTextBox_ID;

    public void displayTextScreen(MouseEvent event) throws IOException {

           fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/messageBox.fxml").toURI().toURL());
           Pane newScene = fxmlLoader.load();
           borderTextBox_ID.setCenter(newScene);

    }

}
