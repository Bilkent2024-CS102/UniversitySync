package app.model.userContent;

import app.model.User;
import app.model.userContent.post.ForumPost;
import app.model.userContent.post.Post;

import app.dao.ReplyDao;

import java.sql.Date;

public class Reply extends UserContentItem{
    Post previous;
    private int replyID;

    /**
     * when adding a new reply to database (i.e. posting a reply) add reply without id and then pull the auto assigned
     * id from database.
     * @param own
     * @param commentContent
     * @param creation
     * @param lastEdit
     * @param toPost
     */
    public Reply(User own, String commentContent, Date creation, Date lastEdit, ForumPost toPost) {
        super(own, commentContent, creation, lastEdit);
        setPrevious(toPost);
        int id = ReplyDao.addReply(this);
        setReplyID(id);
    }

    /**
     * constructor for when pulling the data from the database and id is known
     * @param own
     * @param commentContent
     * @param creation
     * @param lastEdit
     * @param toPost
     * @param id
     */
    public Reply(User own, String commentContent, Date creation, Date lastEdit, ForumPost toPost, int id) {
        super(own, commentContent, creation, lastEdit);
        setPrevious(toPost);
        setReplyID(id);
    }

    /*
     * Getters
     */
    public Post getPrevious()
    {
        return previous;
    }

    public int getReplyID() {
        return replyID;
    }

    /*
     * Setters
     */
    public void setPrevious(Post previous)
    {
        this.previous = previous;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }
}
