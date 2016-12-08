package com.github.lariscy.jserverrepo.shared;

import java.io.Serializable;

/**
 * @author Steven
 */
public class User implements Serializable {
    
    private String username;
    private String password;
    private UserRole userRole;

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

}
