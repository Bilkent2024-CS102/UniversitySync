import java.util.ArrayList;
import location.Dormitory;
import location.Room;
import userContent.Review;
import userContent.post.Post;
import userContent.post.ForumPost;
import userContent.post.EventPost;

public class User {

    private static int numberOfInstances;
    private int id;

    private String name;
    private Room room;
    private String email;
    private String password;
    private Image profilePicture; //What is Image class?
    //TODO Add enum Major here (how?)

    private ArrayList<User> friends;
    private ArrayList<ForumPost> liked;
    private ArrayList<EventPost> followedEvents;
    private ArrayList<FriendRequest> pendingIncomingRequests;
    private ArrayList<FriendRequest> pendingRequests;
    private ArrayList<Post> postsOwned;


    /*
     * Getters
     */
    public static int getNumberOfInstances()
    {
        return numberOfInstances;
    }
    public int getId()
    {
        return id;
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
    public Image getProfilePicture()
    {
        return profilePicture;
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
        this.id = id;
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
    public void setProfilePicture(Image profilePicture)
    {
        this.profilePicture = profilePicture;
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
    /*
    * Mutators
    * TODO add validation
    */
    public void addFriends(User friend)
    {
        friends.add(friend);
    }
    public void addLiked(ForumPost post)
    {
        liked.add(post);
    }
    public void addFollowedEvent(EventPost post)
    {
        followedEvents.add(post);
    }
    public void addPendingIncomingRequest(FriendRequest req)
    {
        pendingIncomingRequests.add(req);
    }
    public void addPendingRequests(FriendRequest req)
    {
        pendingRequests.add(req);
    }
    public void addPostsOwned(ForumPost post)
    {
        postsOwned.add(post);
    }
}
