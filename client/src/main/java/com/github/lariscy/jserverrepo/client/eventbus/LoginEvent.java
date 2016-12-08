package com.github.lariscy.jserverrepo.client.eventbus;

import com.github.lariscy.jserverrepo.shared.User;

/**
 * @author Steven Lariscy
 */
public class LoginEvent extends SimpleEvent {
    
    private User user;
    
    public LoginEvent(boolean success) {
        super(success);
    }

    public LoginEvent(boolean success, String message) {
        super(success, message);
    }

    public LoginEvent(boolean success, String message, User user) {
        super(success, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("LoginEvent{success=%b, message=%s, user=%s}", isSuccess(), getMessage(), user);
    }
    
}
