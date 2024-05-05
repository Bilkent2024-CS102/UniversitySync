package app.controller;

import app.model.User;


public class SessionManager {

    private static User currentUser;

    public void loginUser(User newUser)
    {
        setCurrentUser(newUser);
    }

    public void logoutUser()
    {
        setCurrentUser(null);
    }

    public boolean isUserLoggedIn()
    {
        return currentUser != null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SessionManager.currentUser = currentUser;
    }
}
