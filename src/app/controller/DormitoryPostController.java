package app.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import com.mysql.cj.Session;
import javafx.stage.StageStyle;

public class DormitoryPostController {

    @FXML
    private Label dormPostUsernameID;
    @FXML
    private Label dormPostTextID;
    @FXML
    private Label dormPostCampusID;
    @FXML
    private Label dormPostFloorID;
    @FXML
    private Label dormPostTypeID;

    public void setData(DormPostMock event) {
        dormPostUsernameID.setText( event.getUsername());
        dormPostTextID.setText( event.getPostText());
        dormPostCampusID.setText( event.getCampus());
        dormPostFloorID.setText( event.getFloor());
        dormPostTypeID.setText( event.getType());

    }
}

class DormPostMock {
    private ImageView postImage;
    private String username;
    private String postText;
    private String campus;
    private String floor;
    private String dorm;
    private String type;

    public void setPostImage(ImageView postImage) {
        this.postImage = postImage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public ImageView getPostImage() {
        return postImage;
    }

    public String getType() {
        return type;
    }

    public String getDorm() {
        return dorm;
    }

    public String getPostText() {
        return postText;
    }

    public String getUsername() {
        return username;
    }

    public String getFloor() {
        return floor;
    }

    public String getCampus() {
        return campus;
    }
}