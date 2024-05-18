package app.model.userContent.post;

public class Tag {
    private int tagId;
    private String tag;

    public Tag(String tag){
        setTag(tag);
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
