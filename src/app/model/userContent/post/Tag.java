package app.model.userContent.post;

public class Tag {
    private static int numberOfInstances;
    private int tagId;
    private String tag;
    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        Tag.numberOfInstances = numberOfInstances;
    }
    public int getTagId() {
        return tagId;
    }
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

}
