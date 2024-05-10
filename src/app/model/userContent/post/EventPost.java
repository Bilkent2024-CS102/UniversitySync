package app.model.userContent.post;

import java.util.ArrayList;
import java.util.Date;

import app.dao.EventPostDao;
import app.model.User;

public class EventPost extends Post{
    String location;
    Date eventDate;
    String importantInfo;
    ArrayList<User> followers;
    int eventPostId;

    /**
     * the constructor that is used when event post is just created. this constructor inserts the event post to database and assigns
     * its database id
     *
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     * @param location
     * @param eventDate
     */
    public EventPost(int ownerId, String text, Date creation, Date lastEdit, String heading, String location, Date eventDate) {
        super(ownerId, text, creation, lastEdit, heading);
        setLocation(location);
        setEventDate(eventDate);
        followers = new ArrayList<User>();
        int id = EventPostDao.addEvent(this);
        setID(id);
    }

    /**
     * this constructor is used when existing event post is to be pulled from database
     *
     * @param id
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     * @param location
     * @param eventDate
     */
    public EventPost(int id, int ownerId, String text, Date creation, Date lastEdit, String heading, String location, Date eventDate) {
        super(ownerId, text, creation, lastEdit, heading);
        setLocation(location);
        setEventDate(eventDate);
        followers = new ArrayList<>();
        setID(id);
    }

    /*
    * Getters
    */
    public String getLocation()
    {
        return location;
    }
    public Date getEventDate()
    {
        return eventDate;
    }
    public String getImportantInfo()
    {
        return importantInfo;
    }
    public ArrayList<User> getFollowers()
    {
        return followers;
    }

    /*
    * Setters
    */
    public void setLocation(String location)
    {
        this.location = location;
    }
    public void setEventDate(Date eventDate)
    {
        this.eventDate = eventDate;
    }
    public void setImportantInfo(String importantInfo)
    {
        this.importantInfo = importantInfo;
    }
    public void setFollowers(ArrayList<User> followers)
    {
        this.followers = followers;
    }

}
