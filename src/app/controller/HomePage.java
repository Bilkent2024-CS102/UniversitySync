package app.controller;
import app.dao.DBConnectionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class HomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/app/view/LoginPage.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/university_sync";
        String username = "root";
        String password = "LkJhGfDs@10"; // TODO: type your own mysql server password here!
        DBConnectionManager.initializeConnection(url, username, password);
        launch();
    }
}
