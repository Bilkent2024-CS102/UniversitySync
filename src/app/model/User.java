package app.model;

import java.util.ArrayList;

import app.model.userContent.post.EventPost;
import app.model.userContent.post.ForumPost;
import app.model.userContent.post.Post;

import app.dao.UserDao;

public class User {

    private int userId;

    private String name;
    private int roomId;
    private String email;
    private String password;
    private String profilePicturePath;
    private String major;
    private String biography;

    private ArrayList<User> friends;
    private ArrayList<ForumPost> liked;
    private ArrayList<EventPost> followedEvents;
    private ArrayList<FriendRequest> pendingIncomingRequests;
    private ArrayList<FriendRequest> pendingRequests;
    private ArrayList<Post> postsOwned;

    /**
     * when adding a new user to database (i.e. registration) add user without id and then pull the auto assigned
     * id from database.
     * @param name
     * @param email
     * @param password
     */
    public User(String name, String email, String password, String major, String biography, int roomId)
    {
        setName(name);
        setEmail(email);
        setPassword(password);
        setMajor(major);
        setBiography(biography);
        setRoomId(roomId);
        int id = UserDao.addUser(this);
        setId(id);

    }

    /**
     * for constructing a user that already exists in database
     * @param id
     * @param name
     * @param email
     * @param password
     */
    public User(int id, String name, String email, String password, String major, String biography, int roomId)
    {
        setName(name);
        setEmail(email);
        setPassword(password);
        setId(id);
        setMajor(major);
        setBiography(biography);
        setRoomId(roomId);
    }


    /*
     * Getters
     */
    public int getUserId() {
        return userId;
    }
    public String getName()
    {
        return name;
    }
    public int getRoomId()
    {
        return roomId;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }
    public String getProfilePicturePath()
    {
        return profilePicturePath;
    }
    public ArrayList<User> getFriends()
    {
        return friends;
    }
    public ArrayList<ForumPost> getLiked()
    {
        return liked;
    }
    public ArrayList<EventPost> getFollowedEvents()
    {
        return followedEvents;
    }
    public ArrayList<FriendRequest> getPendingIncomingRequests()
    {
        return pendingIncomingRequests;
    }
    public ArrayList<FriendRequest> getPendingRequests()
    {
        return pendingRequests;
    }
    public ArrayList<Post> getPostsOwned()
    {
        return postsOwned;
    }
    public String getMajor(){
        return major;
    }
    public String getBiography(){
        return biography;
    }

    /*
    * Setters
    * TODO add validation
    */
    public void setId(int id)
    {
        this.userId = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setProfilePicturePath(String profilePicturePath)
    {
        this.profilePicturePath = profilePicturePath;
    }
    public void setFriends(ArrayList<User> friends)
    {
        this.friends = friends;
    }
    public void setLiked(ArrayList<ForumPost> liked)
    {
        this.liked = liked;
    }
    public void setFollowedEvents(ArrayList<EventPost> followedEvents)
    {
        this.followedEvents = followedEvents;
    }
    public void setPendingIncomingRequests(ArrayList<FriendRequest> pendingIncomingRequests) {
        this.pendingIncomingRequests = pendingIncomingRequests;
    }
    public void setPendingRequests(ArrayList<FriendRequest> pendingRequests)
    {
        this.pendingRequests = pendingRequests;
    }
    public void setPostsOwned(ArrayList<Post> postsOwned)
    {
        this.postsOwned = postsOwned;
    }
    public void setMajor(String major){
        this.major = major;
    }
    public void setBiography(String biography){
        this.biography = biography;
    }

    public void addFriend(User friend)
    {
        friends.add(friend);
        if (pendingIncomingRequests.contains(friend))
        {
            pendingIncomingRequests.remove(friend);
        }
        else
        {
            pendingRequests.remove(friend);
        }
    }
    public void removeFriend(User friend)
    {
        friends.remove(friend);
    }

    public boolean removeIncomingFriendRequest(User other)
    {
        for (int i = 0; i < pendingIncomingRequests.size(); i++)
        {
            if (pendingIncomingRequests.get(i).getSenderId() == other.getUserId())
            {
                pendingIncomingRequests.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeOutgoingFriendRequest(User other)
    {
        for (int i = 0; i < pendingRequests.size(); i++)
        {
            if (pendingRequests.get(i).getReceiverId() == other.getUserId())
            {
                pendingRequests.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean equals(User other)
    {
        if (this.getUserId() == other.getUserId())
        {
            return true;
        }
        return false;
    }
}
