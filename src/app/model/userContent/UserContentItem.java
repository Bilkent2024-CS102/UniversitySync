package app.model.userContent;

import java.util.Date;

import app.model.User;

public abstract class UserContentItem {
    private static int numberOfInstances;

    private Date creationDate;
    private User owner;

    private int userContentItemId;
    private String mainText;
    private Date lastEditDate;

    /*
    * Getters
    * TODO add validation
    */
    public static int getNumberOfInstances()
    {
        return numberOfInstances;
    }
    public Date getCreationDate()
    {
        return creationDate;
    }
    public User getOwner()
    {
        return owner;
    }
    public int getUserContentItemId()
    {
        return userContentItemId;
    }
    public String getMainText()
    {
        return mainText;
    }
    public Date getLastEditDate()
    {
        return lastEditDate;
    }

    /*
    * Setters
    * TODO add validation
    */
    public void setCreationDate(Date lastEditDate)
    {
        this.lastEditDate = lastEditDate;
    }
    public void setLastEditDate(Date lastEditDate)
    {
        this.lastEditDate = lastEditDate;
    }
    public void setOwner(Date lastEditDate)
    {
        this.lastEditDate = lastEditDate;
    }
    public void setID(Date lastEditDate) //TODO MIGHT BE REMOVED - not useful (?)
    { 
        this.lastEditDate = lastEditDate;
    }
    public void setMainText(String mainText)
    {
        this.mainText = mainText;
    }
}
