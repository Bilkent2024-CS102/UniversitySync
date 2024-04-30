package app.model.userContent;

import app.model.userContent.post.Post;

public class Reply extends UserContentItem{
    Post previous;

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
