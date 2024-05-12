package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
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


public class DormitoryPostingPageController implements Initializable{
    private Stage stage;
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox dormitoryPostingPageVbox_ID = new VBox();
    private List<DormPostMock> dormPostMockss;

    public void initialize(URL location, ResourceBundle resources) {
        dormPostMockss = new ArrayList<>(data());

        try {
            for( int i = 0; i < dormPostMockss.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/dormPosts.fxml").toURI().toURL());
                Pane postPane = fxmlLoader.load();              //the pane that contains posts in the post fxml
                DormitoryPostController dormController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                dormController.setData(dormPostMockss.get(i));
//              eventController.setRightEventTabController(this);
                dormitoryPostingPageVbox_ID.getChildren().add(postPane);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<DormPostMock> data() {
        List<DormPostMock> ls = new ArrayList<>();

        DormPostMock dormPost1 = new DormPostMock();
        dormPost1.setUsername("Saqib Sheikh");
        dormPost1.setDorm("76");
        dormPost1.setCampus("Center");
        dormPost1.setPostText("Room is well cleaned and has a fridge and 2 bunk beds. Roommate should be a hygenic and desciplined guy who doesn't smoke inside the room. Also roommate should ensure that he doesn't bring his friends inside the room when I am somewhere else. Thank You for reading this post.");
        dormPost1.setFloor("4");
        dormPost1.setType("Double (Bunk Bed), 2 Roommates");
        ls.add(dormPost1);

        DormPostMock dormPost2 = new DormPostMock();
        dormPost2.setUsername("Mutaib");
        dormPost2.setDorm("61");
        dormPost2.setCampus("Main");
        dormPost2.setPostText("I want a roommate who studys 24x7 a day");
        dormPost2.setFloor("1");
        dormPost2.setType("Triple (Bunk Bed), 3 Roommates");
        ls.add(dormPost2);
        return ls;
        // return ForumPostDao.getPostsByRecency();
    }

    public void displayFilterPopup(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/dormFilterPopup.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL);
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        filterStage.showAndWait();
    }
    public void addFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void cancelFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
