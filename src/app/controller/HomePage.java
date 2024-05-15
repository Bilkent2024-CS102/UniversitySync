package app.controller;
import app.dao.DBConnectionManager;
import app.dao.UserDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SessionManager.setCurrentUser(UserDao.getUserById(1));
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/app/view/HomePage.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/university_sync";
        String username = "root";
        String password = "12345678"; // TODO: type your own mysql server password here!
        DBConnectionManager.initializeConnection(url, username, password);
        launch();
    }
}
