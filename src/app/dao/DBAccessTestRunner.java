package app.dao;

import app.model.User;
import app.model.location.Dormitory;
import app.model.location.cafeteria.Cafeteria;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


/**
 * this is a class for testing database access functionality
 * this probably will not exist in actual project at the final
 */
public class DBAccessTestRunner {
    public static void main(String[] args) throws Exception{

        String url = "jdbc:mysql://localhost:3306/university_sync";
        String username = "root";
        String password = "ben123**AA"; // TODO: type your own mysql server password here!
        DBConnectionManager.initializeConnection(url, username, password);

        //User u = new User("hahahaha", "hahahahaha@gmail.com", "pass12345");
        //UserDao.addUser(u);
        ArrayList<User> users = UserDao.getUsers();
        //System.out.println(UserDao.concludeFriendRequest(users.get(0), users.get(3), false));
        //Dormitory d = DormitoryDao.getDormitoryById(1);

        //System.out.println(d.getLocationId() + " " + d.getName() + " " + d.getDescription() + " " + d.getRating());

        //ArrayList<Room> rooms = DormitoryDao.getRoomTypesIn(d);
        /*for (Room r : rooms) {
            System.out.println(r.getRoomId() + " " + r.getCapacity() + " " + r.isBunked() + " " + r.isPrivateBathroom() + " " + r.getDescription() + " " + r.getDorm().getName());
        }*/
        //Review r = new Review(users.get(3), "this is a very good dormitory", new java.util.Date(), new java.util.Date(), dorms.get(2), 5);
        //ReviewDao.addReview(r);

        //Cafeteria c = CafeteriaDao.getCafeteriaById(1);
        //Review r = new Review(users.get(3), "nice cafeteria however somewhat expensive", new java.util.Date(), new java.util.Date(), c, 4);
        try {
            setProfileImage(users.get(3));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("lalalaal");
    }

    public static void setProfileImage(User u) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));
        chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        String newPath = "src/app/images/profilePictures/profilePicture" + u.getUserId() + ".jpg";
        Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
        u.setProfilePicturePath(newPath);
        UserDao.updateUser(u);
    }
}