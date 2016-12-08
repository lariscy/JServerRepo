package com.github.lariscy.jserverrepo.shared;

import java.io.Serializable;

/**
 * @author Steven
 */
public class LoginRequest implements Serializable {
    
    public enum Type implements Serializable {
        LOGIN, LOGOUT;
    }
    
    private Type requestType;
    private User user;

    public LoginRequest(Type requestType, User user) {
        this.requestType = requestType;
        this.user = user;
    }

    public Type getRequestType() {
        return requestType;
    }

    public void setRequestType(Type requestType) {
        this.requestType = requestType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
