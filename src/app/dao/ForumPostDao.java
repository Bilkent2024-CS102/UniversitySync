package app.dao;

import app.model.User;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;

import java.sql.*;
import java.util.ArrayList;

/**
 * The ForumPostDao class provides methods for accessing and managing forum post data in the database.
 */
public class ForumPostDao {

    /**
     * @TESTED
     * Retrieves a forum post by its ID from the database.
     *
     * @param id The ID of the forum post to retrieve.
     * @return The ForumPost object corresponding to the given ID, or null if no such post exists.
     */
    public static ForumPost getPostById(int id)
    {
        ForumPost post = null;
        try
        {
            String query = "SELECT * FROM university_sync.forum_post WHERE forum_post_id= ?";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            post = new ForumPost(
                    rs.getInt("forum_post_id"),
                    rs.getInt("owner_student_id"),
                    rs.getString("main_text"),
                    rs.getTimestamp("creation_date"),
                    rs.getTimestamp("last_edit_date"),
                    rs.getString("heading")
            );
            return post;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }


    /**
     * @TESTED
     * @return all  {@code EventPost} instances by order of recency.
     */
    public static ArrayList<ForumPost> getForumPostsByRecency()
    {
        ArrayList<ForumPost> posts = new ArrayList<ForumPost>();
        ForumPost post = null;
        try
        {
            String query = "SELECT * FROM university_sync.forum_post ORDER BY creation_date DESC";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                post = new ForumPost(
                        rs.getInt("forum_post_id"),
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_edit_date"),
                        rs.getString("heading")
                );
                posts.add(post);
            }
            return posts;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * @TESTED
     * Checks if a forum post is liked by a specific user.
     *
     * @param userId      The ID of the user.
     * @param forumPostId The ID of the forum post.
     * @return True if the user has liked the post, false otherwise.
     */
    public static boolean isLikedByUser(int userId, int forumPostId)
    {
        boolean isLiked = false;
        try{
            String query = "SELECT * FROM university_sync.like_forum_post WHERE liked_by_student_id = ? AND liked_forum_post_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, forumPostId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                isLiked = true;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return isLiked;
    }

    /**
     * @TESTED
     * Adds a like to a forum post in the database.
     *
     * @param forumPostId The id of the forum post to add a like to.
     * @param userId id of the user that liked the post
     * @return True if the operation was successful, false otherwise.
     */
    public static boolean addLike(int forumPostId, int userId)
    {
        try {
            String query = "INSERT INTO university_sync.like_forum_post (liked_by_student_id, liked_forum_post_id) VALUES (?,?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, forumPostId);
            pst.executeUpdate();
            return true;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * @TESTED
     * Deletes a forum post from the database.
     *
     * @param forumPostId The ID of the post to remove like from.
     * @param userId id of the user that unlikes the post
     */
    public static void removeLike(int forumPostId, int userId)
    {
        try
        {
            String query = "DELETE FROM university_sync.like_forum_post WHERE liked_forum_post_id= ? AND liked_by_student_id= ?;";
            PreparedStatement ps = DBConnectionManager.getConnection().prepareStatement(query);
            ps.setInt(1, forumPostId);
            ps.setInt(2, userId);
            ResultSet rs = ps.getResultSet();
            ps.executeUpdate();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return;
        }
    }

    /**
     * @TESTED
     * Adds a comment (reply) to a forum post in the database.
     *
     * @param comment The reply to be added.
     * @return The ID of the newly added comment, or -1 if an error occurred.
     */
    public static int addComment(Reply comment)
    {
        int postId = comment.getPostId();
        ForumPost post = ForumPostDao.getPostById(postId);
        try {
            String query = "INSERT INTO university_sync.reply " +
                    "(owner_student_id, creation_date, last_edit_date, main_text, replies_to_forum_post_id) " +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, comment.getOwnerId());
            pst.setTimestamp(2, new Timestamp(comment.getCreationDate().getTime()));
            pst.setTimestamp(3, new Timestamp(comment.getLastEditDate().getTime()));
            pst.setString(4, comment.getMainText());
            pst.setInt(5, comment.getPostId());
            pst.executeUpdate();
            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int idOfNewComment = rs.getInt(1);
            return idOfNewComment;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return -1;
        }
    }

    /**
     * @TESTED
     * Delete a comment from the database.
     * @param commentId The ID of the comment to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public static boolean deleteComment(int commentId) {
        try {
            String query = "DELETE FROM university_sync.reply WHERE reply_id=?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, commentId);
            pst.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * @TESTED
     * Retrieves replies to a forum post from the database.
     *
     * @param forumPostId The id of the forum post to retrieve replies for.
     * @return An ArrayList containing the replies to the specified post.
     */
    public static ArrayList<Reply> getReplies(int forumPostId)
    {
        ArrayList<Reply> result = new ArrayList<Reply>();
        try
        {
            String query = "SELECT * FROM university_sync.reply WHERE replies_to_forum_post_id= ?";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            st.setInt(1, forumPostId);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                Reply r = new Reply(
                        rs.getInt("reply_id"),
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_edit_date"),
                        rs.getInt("replies_to_forum_post_id")
                );
                result.add(r);
            }
            return result;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * @TESTED
     * Deletes a forum post from the database.
     *
     * @param postId The ID of the post to delete.
     */
    public static void delete(int postId)
    {
        try
        {
            //delete all likes of the post
            String query = "DELETE FROM university_sync.like_forum_post WHERE liked_forum_post_id=?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, postId);
            pst.executeUpdate();

            //delete all replies of the post
            query = "DELETE FROM university_sync.reply WHERE replies_to_forum_post_id=?";
            pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, postId);
            pst.executeUpdate();

            //delete all tags (?) of the forum post
            query = "DELETE FROM university_sync.tag_forum_post WHERE tagged_forum_post_id=?";
            pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, postId);
            pst.executeUpdate();

            query = "DELETE FROM university_sync.forum_post WHERE forum_post_id = ?;";
            PreparedStatement ps = DBConnectionManager.getConnection().prepareStatement(query);
            ps.setInt(1, postId);
            ps.executeUpdate();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return;
        }
    }

    /**
     * @TESTED
     * Edits a forum post in the database.
     *
     * @param postId     The ID of the post to edit.
     * @param editedText The edited text to replace the original post content.
     * @return True if the edit was successful, false otherwise.
     */
    public static boolean edit(int postId, String editedText)
    {
        String query = "UPDATE university_sync.forum_post SET main_text= ? WHERE forum_post_id= ?";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, editedText);
            pst.setInt(2, postId);
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
     * Adds a forum post to the database.
     *
     * @param forumPost The forum post to add.
     * @return The ID of the newly added post, or -1 if an error occurred.
     */
    public static int addForumPost(ForumPost forumPost)
    {
        String query = "INSERT INTO university_sync.forum_post " +
                "(owner_student_id, creation_date, last_edit_date, heading, main_text, like_count) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, forumPost.getOwnerId());
            pst.setTimestamp(2, new java.sql.Timestamp(forumPost.getCreationDate().getTime()));
            pst.setTimestamp(3, new java.sql.Timestamp(forumPost.getLastEditDate().getTime()));
            pst.setString(4, forumPost.getHeading());
            pst.setString(5, forumPost.getMainText());
            pst.setInt(6,  forumPost.getLikeCount());

            pst.executeUpdate();

            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int id = rs.getInt(1);
            return id;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return -1;
        }
    }

    /**
     * @TESTED
     * Returns the posts that were liked by user
     *
     * @param userId ID of the user whose liked posts to get
     * @return ArrayList of all liked ForumPosts by user with ID userId
     */
    public static ArrayList<ForumPost> getLikedPosts(int userId)
    {
        try
        {
            ArrayList<ForumPost> likedPosts = new ArrayList<>();
            String query = "SELECT liked_forum_post_id FROM university_sync.like_forum_post WHERE liked_by_student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                likedPosts.add(getPostById(rs.getInt("liked_forum_post_id")));
            }
            return likedPosts;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @TESTED
     * Returns forum posts made by the user
     *
     * @param userId id of the user whose posts are to be pulled
     * @return ArrayList of forum posts made by user
     */
    public static ArrayList<ForumPost> getForumPostsOfUser(int userId)
    {
        ArrayList<ForumPost> posts = new ArrayList<ForumPost>();
        try
        {
            String query = "SELECT * FROM university_sync.forum_post WHERE owner_student_id = ?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                ForumPost post = new ForumPost(
                        rs.getInt("forum_post_id"),
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_edit_date"),
                        rs.getString("heading")
                );
                posts.add(post);
            }
            return posts;
        }
        catch (Exception sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * @TESTED
     * Returns forum posts made by the user's friends
     *
     * @param userId id of the user whose friends' posts are to be pulled
     * @return ArrayList of forum posts made by user's friends
     */
    public static ArrayList<ForumPost> getForumPostsOfFriends(int userId)
    {
        ArrayList<ForumPost> posts = new ArrayList<ForumPost>();
        try
        {
            ArrayList<User> friends = UserDao.getFriends(userId);
            String query = "SELECT * FROM university_sync.forum_post WHERE owner_student_id = ?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            for (User friend: friends) {
                pst.setInt(1, friend.getUserId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ForumPost post = new ForumPost(
                            rs.getInt("forum_post_id"),
                            rs.getInt("owner_student_id"),
                            rs.getString("main_text"),
                            rs.getTimestamp("creation_date"),
                            rs.getTimestamp("last_edit_date"),
                            rs.getString("heading")
                    );
                    posts.add(post);
                }
            }
            return posts;
        }
        catch (Exception sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }

    public static int getPostLikes(int postID) {
        ArrayList<User> users = UserDao.getUsers();
        int count = 0;
        for (int i = 0; i < users.size(); i++)
        {
            if (ForumPostDao.isLikedByUser(users.get(i).getUserId(), postID))
            {
                count++;
            }
        }
        return count;
    }
}
