package app.controller;

import app.model.User;

public class SessionManager {

    private User currentUser;

    public void loginUser(User newUser)
    {
        currentUser = newUser;
    }

    public void logoutUser()
    {
        currentUser = null;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public boolean isUserLoggedIn()
    {
        return currentUser != null;
    }

}
