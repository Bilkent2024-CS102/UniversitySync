package app.dao;

import java.sql.*;
import java.util.ArrayList;
import app.model.*;
import app.model.location.Location;
import app.model.userContent.Reply;
import app.model.userContent.Review;
import app.model.location.Dormitory;
import app.model.location.cafeteria.Cafeteria;
import app.model.userContent.post.ForumPost;


public class ReviewDao {
    //TODO: this method will return the database id of the newly inserted review
    //will do that according to type of the Reviewable instance of the Review
    public static int addReview(Review r){
        try {
            String type = "";
            int id = 0;
            if (r.getR() instanceof Dormitory){
                type = "dormitory";
                id = ((Dormitory)r.getR()).getLocationId();
            }
            else if (r.getR() instanceof Cafeteria){
                type = "cafeteria";
                id = ((Cafeteria)r.getR()).getLocationId();
            }
            String query = "INSERT INTO university_sync.review " +
                    "(owner_student_id, creation_date, last_edit_date, main_text, rating_given, review_to_" + type + "_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, r.getOwner().getUserId());
            pst.setDate(2, new java.sql.Date(r.getCreationDate().getTime()));
            pst.setDate(3, new java.sql.Date(r.getLastEditDate().getTime()));
            pst.setString(4, r.getMainText());
            pst.setDouble(5, r.getRateGiven());
            pst.setInt(6, id);

            pst.executeUpdate();

            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();

            int idOfNewReview = rs.getInt(1);
            return idOfNewReview;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        }
    }

    //TODO: this method will return whether the operation was successful
    public static boolean removeReply(Review r){
        return false;
    }

    //TODO: this method will update content of the review using newText.
    //access to old review will be via id of currentReview
    //return whether the operation is successful
    public static boolean editReview(Review currentReview, String nexText){
        return false;
    }

    //TODO: this method will return the reviews to a particular Reviewable
    public static ArrayList<Review> getReviewsOf(Reviewable r){
        return null;
    }
}
