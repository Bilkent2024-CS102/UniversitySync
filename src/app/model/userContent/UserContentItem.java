package app.model.userContent;

import java.util.Date;

public abstract class UserContentItem {
    private static int numberOfInstances;
    private int userContentItemId;

    private int ownerId;
    private String mainText;
    private Date lastEditDate;
    private Date creationDate;

    public UserContentItem(int ownerId, String text, Date creation, Date lastEdit) {
        setOwnerId(ownerId);
        setMainText(text);
        setCreationDate(creation);
        setLastEditDate(lastEdit);
    }

    public UserContentItem(int ownerId, String text) {
        setOwnerId(ownerId);
        setMainText(text);
        setCreationDate();
        setLastEditDate();
    }

    private void setLastEditDate() {
        setCreationDate(new Date());
    }

    private void setCreationDate() {
        setLastEditDate(new Date());
    }

    /*
     * Getters
     * TODO add validation
     */
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getUserContentItemId() {
        return userContentItemId;
    }

    public String getMainText() {
        return mainText;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    /*
     * Setters
     * TODO add validation
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setID(int id) //TODO MIGHT BE REMOVED - not useful (?)
    {
        this.userContentItemId = id;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
}
