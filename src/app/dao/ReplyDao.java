package app.dao;

import java.sql.*;
import java.util.ArrayList;
import app.model.*;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;

/**
 * manages database access operations for replies to forum posts.
 */
public class ReplyDao {

    //TODO: this method will return the database id of the newly inserted reply
    public static int addReply(Reply r){
        return 0;
    }

    //TODO: this method will return whether the operation was successful
    public static boolean removeReply(Reply r){
        return false;
    }

    //TODO: this method will update content of the reply using newReply.
    //access to old reply will be via id, which is the same in newReply
    //return whether the operation is successful
    public static boolean editReply(Reply newReply){
        return false;
    }

    //TODO: this method will return the replies given to a particular forum post
    public static ArrayList<Reply> getRepliesOfPost(ForumPost forumPost){
        return null;
    }
}