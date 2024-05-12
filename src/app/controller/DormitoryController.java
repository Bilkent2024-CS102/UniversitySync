package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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

public class DormitoryController implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    // private Label dormNameLabel;
    // private Label dormRatingLabel;
    // private Label dormCampusLabel;
    // private Label dormRoomTypeLabel; // Need to decide if we are implementing this

    // public void setData(Dormitory dorm)
    // {
    //     String dormNameLabelText = dorm.getName();
    //     dormNameLabel.setText(dormNameLabelText);
    //     String dormRatingLabelText = "Rated " + dorm.getRating() + "/5";
    //     dormRatingLabel.setText(dormRatingLabelText);
    //     dormCampusLabel.setText(dorm.getCampus());
    //     // Need to implement dormRoomTypeLabel if we decide to include it
    // }

//*******************************************************************************************************************
    @FXML
    private VBox dormVBoxID = new VBox();
    private List<DormMock> dormMock;

    public void initialize(URL location, ResourceBundle resources) {
        dormMock = new ArrayList<>(data());

        try {
            for( int i = 0; i < dormMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/DormNameHBox.fxml").toURI().toURL());
                HBox hbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                DormitoryNameController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(dormMock.get(i));
//              eventController.setRightEventTabController(this);
                dormVBoxID.getChildren().add(hbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<DormMock> data() {
        List<DormMock> ls = new ArrayList<>();

        DormMock dorm1 = new DormMock();
        dorm1.setDormName("76");
        dorm1.setDormRating("Rating 2.2/4");
        dorm1.setDormCampus("Center");
        dorm1.setDormRatedField(dorm1.getDormRating());
        dorm1.setDormDescription("This dorm is located near the big ground where beautiful view of the city is visible. This dorm also has one of the best cafeterias in bilkent ie Bilka. Best Dorm");
        ls.add(dorm1);

        DormMock dorm2 = new DormMock();
        dorm2.setDormName("55");
        dorm2.setDormRating("Rating 3.2/4");
        dorm2.setDormCampus("East");
        dorm2.setDormDescription("This dorm is located near the ee building where biggest building is visible. Aliens visit there");
        dorm2.setDormRatedField(dorm2.getDormRating());
        ls.add(dorm2);
        return ls;
        // return ForumPostDao.getPostsByRecency();
    }

    public List<DormMock> getData() {
        return data();
    }

    //*********************************( FOR DORM FILTER *************************************



//    @FXML
//    private ChoiceBox dormChoiceBoxID = new ChoiceBox();
//
//    public void addDormNames() {
//        dormChoiceBoxID.getItems().addAll("61", "62", "63", "70", "71", "74");
//    }

}
