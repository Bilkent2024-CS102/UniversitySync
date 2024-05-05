package app.model.userContent;

import app.model.User;

public class Message extends UserContentItem {
    
    private String text;
    private User receiver;
/**
 * Getter methods
 */
    public String getText()
    {
        return text;
    }

    public User getReceiver()
    {
        return receiver;
    }
/**
 * Setter method
 */
    public void setReceiver(User u)
    {
        receiver = u;
    }
}
