package app.controller;

import app.dao.DormitoryDao;
import app.model.location.Dormitory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DormitoryController implements Initializable {

    private FXMLLoader fxmlLoader;

    @FXML
    private VBox dormVBoxID = new VBox();

    private List<Dormitory> dormitories;


    public void initialize(URL location, ResourceBundle resources) {
        dormitories = DormitoryDao.getAllDormitories();

        try {
            for (int i = 0; i < dormitories.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/DormNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();
                DormitoryNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(dormitories.get(i));
                dormVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderByDefault(ActionEvent event) {
        dormitories = DormitoryDao.getAllDormitories();
        dormVBoxID.getChildren().clear();

        try {
            for (int i = 0; i < dormitories.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/DormNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();
                DormitoryNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(dormitories.get(i));
                dormVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderByRating(ActionEvent event) {
        dormitories = DormitoryDao.getAllDormitoriesByRating();
        dormVBoxID.getChildren().clear();
        try {
            for (int i = 0; i < dormitories.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/DormNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                DormitoryNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(dormitories.get(i));
                dormVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}