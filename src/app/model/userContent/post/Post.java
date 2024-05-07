package app.model.userContent.post;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import app.model.User;
import app.model.userContent.UserContentItem;

public abstract class Post extends UserContentItem {
    
    private String heading;
    private ArrayList<File> attachments;

    public Post(User own, String text, Date creation, Date lastEdit, String heading){
        super(own, text, creation, lastEdit);
        setHeading(heading);
        attachments = new ArrayList<>();
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
