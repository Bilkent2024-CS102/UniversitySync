package app.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CafeteriaNameController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private Button food_cafeteriaName_ID;
    @FXML
    private Label foodPage_CafeRating_ID;
    @FXML
    private Label foodPage_CafePriceRange_ID;

    // ************  Particular Cafe Associated with this page  ****************
    private CafeMock cafeAssociatedWithThis;

    public void setData(CafeMock cafe) {

        cafeAssociatedWithThis = cafe;        //connection bw different pages

        food_cafeteriaName_ID.setText( cafe.getCafeName());
        foodPage_CafeRating_ID.setText( cafe.getCafeRating());
        foodPage_CafePriceRange_ID.setText( cafe.getCafePriceRange());


    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        CafeteriaDetailController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData(cafeAssociatedWithThis);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }


    public void switchToCafeDetail(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/CafeteriaPage/CafeDetail.fxml", event);
    }

}

class CafeMock {

    private String cafeName;
    private String cafeRating;
    private String cafePriceRange;
    private String cafeDescription;
    private String cafeMenu;
    private ImageView cafeImage;
    private List<ReviewMock> cafeReviewList;

    public String getCafeName() {
        return cafeName;
    }

    public String getCafePriceRange() {
        return cafePriceRange;
    }

    public String getCafeRating() {
        return cafeRating;
    }
    public ImageView getCafeImage() {
        return cafeImage;
    }

    public void setCafeImage(ImageView cafeImage) {
        this.cafeImage = cafeImage;
    }

    public String getCafeMenu() {
        return cafeMenu;
    }
    public String getCafeDescription() {
        return cafeDescription;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public void setCafeRating(String cafeRating) {
        this.cafeRating = cafeRating;
    }

    public void setCafePriceRange(String cafePriceRange) {
        this.cafePriceRange = cafePriceRange;
    }

    public void setCafeMenu(String cafeMenu) {
        this.cafeMenu = cafeMenu;
    }

    public void setCafeDescription(String cafeDescription) {
        this.cafeDescription = cafeDescription;
    }

    public List<ReviewMock> getCafeReviewList() {
        return cafeReviewList;
    }

    public void setCafeReviewList(List<ReviewMock> cafeReviewList) {
        this.cafeReviewList = cafeReviewList;
    }
}