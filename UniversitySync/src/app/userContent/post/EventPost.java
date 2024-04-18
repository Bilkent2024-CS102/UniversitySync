package userContent.post;

import java.util.ArrayList;
import java.util.Date;
import User;

public class EventPost extends Post{
    String location;
    Date eventDate;
    String importantInfo;
    ArrayList<User> followers;

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
    /*
    * Mutators
    */
    public void addFollower(User u)
    {
        followers.add(u);
    }
    public void removeFollower(User u)
    {
        followers.remove(u);
    }

    public void notifyFollowers()
    {

    }

}
