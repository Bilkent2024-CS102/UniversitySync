package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SocialEachTextController {
    //Right now don't think we need it
    @FXML
    private Label eachText_ID;

    public void setData(String text) {

        eachText_ID.setText( text);

    }

}
