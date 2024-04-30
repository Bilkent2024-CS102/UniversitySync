package app.model.userContent.post;

import java.util.ArrayList;

import app.model.User;
import app.model.userContent.Reply;

public class ForumPost extends Post{
    private int likeCount;
    private ArrayList<Tag> tags;
    private ArrayList<Reply> replies;
    private ArrayList<User> likedBy;

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
