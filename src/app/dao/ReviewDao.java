package app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import app.model.*;
import app.model.location.Location;
import app.model.userContent.Review;
import app.model.location.Dormitory;
import app.model.location.cafeteria.Cafeteria;


public class ReviewDao {

    //this method returns the database id of the newly inserted review
    //does that according to type of the Reviewable instance of the Review
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
            pst.setInt(1, r.getOwnerId());
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
        String query = "DELETE FROM university_sync.review WHERE review_id=" +
                r.getUserContentItemId();
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.executeQuery();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * access to old review will be via id of currentReview
     * return whether the operation is successful
     * @param currentReview is the review to be edited.
     * @param nexText is the new review text to be set.
     */
    public static boolean editReview(Review currentReview, String nexText){
        String query = "UPDATE university_sync.review SET main_text='" +
                nexText +
                "' WHERE review_id=" +
                currentReview.getUserContentItemId();
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.executeQuery();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * @param r
     * @return the arraylist of reviews associated with the given reviewable object r.
     */
    public static ArrayList<Review> getReviewsOf(Reviewable r){
        ArrayList<Review> result = new ArrayList<Review>();
        String query = "";
        Location loc = (Location) r;
        int id = loc.getLocationId();
        String type = ((loc instanceof Cafeteria) ? "cafeteria" : "dormitory");
        query = "SELECT * FROM university_sync.review " +
                "WHERE review_to_" + type + "_id=" + id;

        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next())
            {
                int u = UserDao.getUserById(resultSet.getInt("owner_student_id"));
                Review review = new Review(
                        u,
                        resultSet.getString("main_text"),
                        new Date(resultSet.getDate("creation_date").getTime()),
                        new Date(resultSet.getDate("last_edit_date").getTime()),
                        loc,
                        resultSet.getDouble("rating_given")
                );

                result.add(review);
            }
            resultSet.close();
            pst.close();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }

        return result;
    }
}
