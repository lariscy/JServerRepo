package com.github.lariscy.jserverrepo.shared;

import java.io.Serializable;

/**
 * @author Steven
 */
public class LoginRequest implements Serializable {
    
    private LoginRequestType requestType;
    private User user;

    public LoginRequest(LoginRequestType requestType, User user) {
        this.requestType = requestType;
        this.user = user;
    }

    public LoginRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(LoginRequestType requestType) {
        this.requestType = requestType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
