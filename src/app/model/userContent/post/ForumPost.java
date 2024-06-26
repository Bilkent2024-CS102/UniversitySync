package app.model.userContent.post;

import java.util.ArrayList;
import java.util.Date;

import app.dao.ForumPostDao;
import app.model.User;
import app.model.userContent.Reply;

public class ForumPost extends Post{
    private int likeCount;
    private ArrayList<Tag> tags;
    private ArrayList<Reply> replies;
    private ArrayList<User> likedBy;


    /**
     * the constructor that is used when forum post is just created. this constructor inserts the forum post to database and assigns
     * its database id
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     */
    public ForumPost(int ownerId, String text, Date creation, Date lastEdit, String heading) {
        super(ownerId, text, creation, lastEdit, heading);
        tags = new ArrayList<>();
        replies = new ArrayList<>();
        likedBy = new ArrayList<>();
        int id = ForumPostDao.addForumPost(this);
        setID(id);
    }

    /**
     * the constructor that is used when forum post is just created. this constructor inserts the forum post to database and assigns
     * its database id
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     */
    public ForumPost(int id, int ownerId, String text, Date creation, Date lastEdit, String heading) {
        super(id, ownerId, text, creation, lastEdit, heading);
        tags = new ArrayList<>();
        replies = new ArrayList<>();
        likedBy = new ArrayList<>();
    }

    /**
     * this constructor is used when existing forum post is to be pulled from database
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     */
    public ForumPost(int id, int ownerId, String text, Date creation, Date lastEdit, String heading, ArrayList<Tag> tags, ArrayList<Reply> replies, ArrayList<User> likedBy) {
        super(id, ownerId, text, creation, lastEdit, heading);
        setTags(tags);
        setReplies(replies);
        setLikedBy(likedBy);
    }

    /*
     * Getters
     */
    public int getLikeCount()
    {
        return likeCount;
    }
    public ArrayList<Tag> getTags()
    {
        return tags;
    }
    public ArrayList<Reply> getReplies()
    {
        return replies;
    }
    public ArrayList<User> getLikedBy()
    {
        return likedBy;
    }
    public int getCommentCount() {
        return ForumPostDao.getReplies(this.getUserContentItemId()).size();
    }

    /*
     * Setters
     */
    public void setLikeCount(int likeCount)
    {
        this.likeCount = likeCount;
    }
    public void setTags(ArrayList<Tag> tags)
    {
        this.tags = tags;
    }
    public void setReplies(ArrayList<Reply> replies)
    {
        this.replies = replies;
    }
    public void setLikedBy(ArrayList<User> likedBy)
    {
        this.likedBy = likedBy;
    }


}
