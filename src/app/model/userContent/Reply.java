package app.model.userContent;

import app.model.User;
import app.model.userContent.post.ForumPost;
import app.model.userContent.post.Post;

public class Reply extends UserContentItem{
    Post previous;

    // TODO: Complete constructors.
    public Reply(User currentUser, String commentContent, ForumPost post) {
        super();
    }

    /*
     * Getters
     */
    public Post getPrevious()
    {
        return previous;
    }

    /*
     * Setters
     */
    public void setPrevious(Post previous)
    {
        this.previous = previous;
    }
}
