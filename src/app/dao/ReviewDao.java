package app.dao;

import app.model.userContent.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * The ReviewDao class provides methods for interacting with reviews in the database.
 */
public class ReviewDao {

    /**
     * @TESTED
     * Adds a review to the database.
     *
     * @param r The review to be added.
     * @return The ID of the newly added review, or 0 if an error occurred.
     */
    public static int addReview(Review r){
        try {
            String query = "INSERT INTO university_sync.review " +
                    "(owner_student_id, creation_date, last_edit_date, main_text, rating_given, review_to_location_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, r.getOwnerId());
            pst.setTimestamp(2, new java.sql.Timestamp(r.getCreationDate().getTime()));
            pst.setTimestamp(3, new java.sql.Timestamp(r.getLastEditDate().getTime()));
            pst.setString(4, r.getMainText());
            pst.setDouble(5, r.getRateGiven());
            pst.setInt(6, r.getReviewableId());

            pst.executeUpdate();

            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();

            int idOfNewReview = rs.getInt(1);
            return idOfNewReview;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1;
        }
    }

    /**
     * @TESTED
     * Removes a review from the database.
     *
     * @param reviewId The ID of the review to be removed.
     * @return True if the removal was successful, false otherwise.
     */
    public static boolean removeReview(int reviewId){
        String query = "DELETE FROM university_sync.review WHERE review_id= ?;";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, reviewId);
            pst.executeUpdate();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * @TESTED
     * access to old review will be via id of currentReview
     * return whether the operation is successful
     * @param reviewId is the id of the review to be edited.
     * @param nexText is the new review text to be set.
     */
    public static boolean editReview(int reviewId, String nexText, double newRating){
        String query = "UPDATE university_sync.review SET main_text = ?, rating_given = ?, last_edit_date = ? WHERE review_id= ?;";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, nexText);
            pst.setFloat(2, (float) newRating);
            pst.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            pst.setInt(4, reviewId);
            pst.executeUpdate();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * @TESTED
     * @param reviewableId
     * @return the arraylist of reviews associated with the given reviewable object r.
     */
    public static ArrayList<Review> getReviewsOf(int reviewableId){
        ArrayList<Review> result = new ArrayList<Review>();
        String query = "";

        query = "SELECT * FROM university_sync.review WHERE review_to_location_id= ?";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, reviewableId);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next())
            {
                Review review = new Review(
                        resultSet.getInt("review_id"),
                        resultSet.getInt("owner_student_id"),
                        resultSet.getString("main_text"),
                        new Timestamp(resultSet.getTimestamp("creation_date").getTime()),
                        new Timestamp(resultSet.getTimestamp("last_edit_date").getTime()),
                        reviewableId,
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

    /**
     * @TESTED
     * used to ensure unique review
     * @param userId
     * @param reviewableId
     * @return whether a review exists by that particular user to that particular location
     */
    public static boolean doesReviewExist(int userId, int reviewableId){
        try
        {
            String query = "SELECT * FROM university_sync.review WHERE owner_student_id = ? AND review_to_location_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, reviewableId);
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                return true;
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
