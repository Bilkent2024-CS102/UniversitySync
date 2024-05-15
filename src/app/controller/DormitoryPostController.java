package app.controller;
import app.dao.CampusDao;
import app.dao.DormitoryDao;
import app.dao.UserDao;
import app.model.userContent.post.DormTransferPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
//import com.mysql.cj.Session;


public class DormitoryPostController {

    private DormTransferPost dtp;

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
    @FXML
    private Label dormPostHeading;
    private FXMLLoader fxmlLoader;

    public void setData(DormTransferPost dtp) {

        this.dtp = dtp;
        dormPostUsernameID.setText(UserDao.getUserById(dtp.getOwnerId()).getName());
        dormPostTextID.setText( dtp.getMainText());
        dormPostCampusID.setText
                (
                        DormitoryDao.getDormitoryById(
                                DormitoryDao.getRoomTypeById(
                                        dtp.getRoomId()).getDormId()
                        ).getCampus().getName()
                );
        dormPostFloorID.setText( "" + dtp.getOwnerId()%5);
        dormPostTypeID.setText( DormitoryDao.getRoomTypeById(dtp.getRoomId()).getDescription());
        dormPostHeading.setText(dtp.getHeading());
    }


    public void messageUser(ActionEvent event) throws IOException {
        //showMessageScreen(thisPost.getOwnerId());
        fxmlLoader = new FXMLLoader(new File("src/app/view/MessagePopup.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        // Show the filter screen
        filterStage.showAndWait();
    }

    public void transferDorm(ActionEvent event) {
    }
}



//class DormPostMock {
//    private ImageView postImage;
//    private String username;
//    private String postText;
//    private String campus;
//    private String floor;
//    private String dorm;
//    private String type;
//
//    public void setPostImage(ImageView postImage) {
//        this.postImage = postImage;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPostText(String postText) {
//        this.postText = postText;
//    }
//
//    public void setCampus(String campus) {
//        this.campus = campus;
//    }
//
//    public void setFloor(String floor) {
//        this.floor = floor;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public void setDorm(String dorm) {
//        this.dorm = dorm;
//    }
//
//    public ImageView getPostImage() {
//        return postImage;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getDorm() {
//        return dorm;
//    }
//
//    public String getPostText() {
//        return postText;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getFloor() {
//        return floor;
//    }
//
//    public String getCampus() {
//        return campus;
//    }
//}