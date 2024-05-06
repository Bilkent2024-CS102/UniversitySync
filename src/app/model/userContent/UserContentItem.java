package app.model.userContent;

import java.util.Date;

import app.model.User;

public abstract class UserContentItem {
    private static int numberOfInstances;
    private int userContentItemId;

    private User owner;
    private String mainText;
    private Date lastEditDate;
    private Date creationDate;

    public UserContentItem(User own, String text, Date creation, Date lastEdit){
        setOwner(own);
        setMainText(text);
        setCreationDate(creation);
        setLastEditDate(lastEdit);
    }

    public UserContentItem(){

    }
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
    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }
    public void setLastEditDate(Date lastEditDate)
    {
        this.lastEditDate = lastEditDate;
    }
    public void setOwner(User owner)
    {
        this.owner = owner;
    }
    public void setID(int id) //TODO MIGHT BE REMOVED - not useful (?)
    { 
        this.userContentItemId = id;
    }
    public void setMainText(String mainText)
    {
        this.mainText = mainText;
    }
}
