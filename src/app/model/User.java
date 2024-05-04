package app.model;

import java.awt.*;
import java.util.ArrayList;

import app.model.location.Room;
import app.model.userContent.post.EventPost;
import app.model.userContent.post.ForumPost;
import app.model.userContent.post.Post;

public class User {

    private static int numberOfInstances;
    private int userId;

    private String name;
    private Room room;
    private String email;
    private String password;
    private String profilePicturePath; //What is Image class?
    //TODO Add enum Major here (how?)

    private ArrayList<User> friends;
    private ArrayList<ForumPost> liked;
    private ArrayList<EventPost> followedEvents;
    private ArrayList<FriendRequest> pendingIncomingRequests;
    private ArrayList<FriendRequest> pendingRequests;
    private ArrayList<Post> postsOwned;

    public User(int id, String name, String email, String password)
    {
        setId(id);
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    /*
     * Getters
     */
    public static int getNumberOfInstances()
    {
        return numberOfInstances;
    }
    public int getUserId() {
        return userId;
    }
    public String getName()
    {
        return name;
    }
    public Room getRoom()
    {
        return room;
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
    public void setRoom(Room room)
    {
        this.room = room;
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
    public void setPendingIncomingRequests(ArrayList<FriendRequest> pendingIncomingRequests)
    {
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
}
