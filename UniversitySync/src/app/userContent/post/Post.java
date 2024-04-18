package userContent.post;

import java.io.File;
import java.util.ArrayList;
import userContent.UserContentItem;

public abstract class Post extends UserContentItem {
    
    private String heading;
    private ArrayList<File> attachments;

    /*
     * Getters
     */
    public int getId()
    {
        return id;
    }
    public String getHeading()
    {
        return heading;
    }
    public ArrayList<File> getAttachments()
    {
        return attachments;
    }

    /*
     * Setters
     * TODO add validation
     */
    public void setId(int id)
    {
        this.id = id;
    }
    public void setHeading(String heading)
    {
        this.heading = heading;
    }
    public void setAttachments(ArrayList<File> attachments)
    {
        this.attachments = attachments;
    }
    /*
    * Mutators
    */
    public void addAttachment(File attachment)
    {
        attachments.add(attachment);
    }
}
