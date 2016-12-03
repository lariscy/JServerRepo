package com.github.lariscy.jserverrepo.client.model;

/**
 * @author Steven Lariscy
 */
public class User {
    
    private String username;
    private String password;
    private UserRole userRole;
    private boolean loggedIn;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", userRole=" + userRole + ", loggedIn=" + loggedIn + '}';
    }
    
}
