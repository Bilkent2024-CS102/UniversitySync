package app.model.userContent.post;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import app.model.User;
import app.model.userContent.UserContentItem;

public abstract class Post extends UserContentItem {
    
    private String heading;
    private ArrayList<File> attachments;

    public Post(int ownerId, String text, Date creation, Date lastEdit, String heading){
        super(ownerId, text, creation, lastEdit);
        setHeading(heading);
        attachments = new ArrayList<>();
    }

    public Post(int ownerId, String text, String heading) {
        super(ownerId, text);
        setHeading(heading);
    }

    public Post(int id, int ownerId, String text, Date creation, Date lastEdit, String heading){
        super(id, ownerId, text, creation, lastEdit);
        setHeading(heading);
        attachments = new ArrayList<>();
    }

    public Post(int id, int ownerId, String text, String heading) {
        super(id, ownerId, text);
        setHeading(heading);
    }

    public String getHeading() {
        return heading;
    }
    public void setHeading(String heading) {
        this.heading = heading;
    }
    public ArrayList<File> getAttachments() {
        return attachments;
    }
    public void setAttachments(ArrayList<File> attachments) {
        this.attachments = attachments;
    }

}
