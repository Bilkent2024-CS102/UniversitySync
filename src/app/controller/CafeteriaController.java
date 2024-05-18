package app.controller;

import app.dao.CafeteriaDao;
import app.model.location.cafeteria.Cafeteria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CafeteriaController implements Initializable {

    private FXMLLoader fxmlLoader;

    @FXML
    private VBox cafeVBoxID = new VBox();

    private List<Cafeteria> cafeterias= CafeteriaDao.getAllCafeterias();


    public void initialize(URL location, ResourceBundle resources) {
        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderCafeByNormal(ActionEvent event) throws IOException {
        cafeterias = CafeteriaDao.getAllCafeterias();
        cafeVBoxID.getChildren().clear();
        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderCafeByRating(ActionEvent event) throws IOException {
        cafeterias = CafeteriaDao.getAllCafeteriasByRating();
        cafeVBoxID.getChildren().clear();
        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderCafePrice(ActionEvent event) throws IOException {
        cafeterias = CafeteriaDao.getAllCafeteriasByPrice();
        cafeVBoxID.getChildren().clear();
        try {
            for(int i = 0; i < cafeterias.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/CafeNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                CafeteriaNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(cafeterias.get(i));
                cafeVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}