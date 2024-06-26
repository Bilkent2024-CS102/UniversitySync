package app.controller;
import app.dao.DormitoryDao;
import app.dao.UserDao;
import app.model.userContent.post.DormTransferPost;
import com.sshtools.twoslices.Toast;
import com.sshtools.twoslices.ToastType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class DormitoryPostController {

    private DormTransferPost thisDormPost;

    @FXML
    private Label dormPostUsernameID;
    @FXML
    private Label dormPostTextID;
    @FXML
    private Label dormPostHeading;
    @FXML
    private Label dormPostCampusID;
    @FXML
    private Label dormPostFloorID;
    @FXML
    private Label dormPostTypeID;
    @FXML
    private Label dormPostDormID;
    @FXML
    private Button messageButton;
    @FXML
    private Button transferButton;
    @FXML
    private ImageView dormPostImageID;

    private FXMLLoader fxmlLoader;

    public void setData(DormTransferPost dtp) {

        this.thisDormPost = dtp;
        dormPostHeading.setText(dtp.getHeading());
        dormPostDormID.setText("" + DormitoryDao.getDormitoryById(DormitoryDao.getRoomTypeById(dtp.getRoomId()).getDormId()).getName());
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
        if (dtp.getOwnerId() == SessionManager.getCurrentUser().getUserId()) {
            messageButton.setDisable(true);
            transferButton.setDisable(true);
        }
        File file = new File("src/app/images/dormitoryPictures/dormitoryPicture" + DormitoryDao.getRoomTypeById(dtp.getRoomId()).getDormId() + ".png");
        javafx.scene.image.Image image = new Image(file.toURI().toString());
        dormPostImageID.setImage(image);
    }


    private void switchToFXML2(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        MessagePopupController newController = fxmlLoader.getController();
        newController.setData(UserDao.getUserById(thisDormPost.getOwnerId()));
        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        // Show the filter screen
        filterStage.showAndWait();
    }

    public void messageUser(ActionEvent event) throws IOException {
        switchToFXML2("src/app/view/MessagePopup.fxml", event);
    }

    public void transferDorm(ActionEvent event) throws AWTException {
        Toast.toast(ToastType.INFO, "Transfer Request", "Transfer request has been sent.");
    }
}