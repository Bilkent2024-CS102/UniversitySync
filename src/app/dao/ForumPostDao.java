package app.dao;

import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;

import java.util.ArrayList;

public class ForumPostDao {

    public static ArrayList<ForumPost> getPostsByRecency() {
        return null;
    }

    public static void addLike(ForumPost post)
    {

    }

    /**
     * TODO
     * add a reply object for given post.
     *
     * @param post           ;
     * @param commentContent
     */
    public static void addComment(ForumPost post, Reply commentContent)
    {

    }

    /**
     * TODO
     * return replies of the given ForumPost instance
     * from database.
     * @param post
     * @return
     */
    public static ArrayList<Reply> getReplies(ForumPost post)
    {
        return null;
    }

    public static void delete(ForumPost post)
    {

    }

    public static void edit(ForumPost post, String editedText)
    {

    }
}
