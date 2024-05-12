package app.model.userContent;

import app.dao.ForumPostDao;

import java.util.Date;

public class Reply extends UserContentItem{
    private int postId;
    private int replyId;

    /**
     * when adding a new reply to database (i.e. posting a reply) add reply without id and then pull the auto assigned
     * id from database.
     * @param ownerId
     * @param commentContent
     * @param creation
     * @param lastEdit
     * @param postId
     */
    public Reply(int ownerId, String commentContent, Date creation, Date lastEdit, int postId) {
        super(ownerId, commentContent, creation, lastEdit);
        setPostId(postId);
        int id = ForumPostDao.addComment(this);
        setID(id);
    }

    public Reply(int ownerId, String commentContent, int toPost)
    {
        this(
                ownerId,
                commentContent,
                new Date(),
                new Date(),
                toPost
        );

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
    public Reply(int id, int own, String commentContent, Date creation, Date lastEdit, int toPost) {
        super(id, own, commentContent, creation, lastEdit);
        setPostId(toPost);
    }

    /*
     * Getters
     */
    public int getPostId()
    {
        return postId;
    }

    public int getReplyId() {
        return replyId;
    }

    /*
     * Setters
     */
    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }
}
