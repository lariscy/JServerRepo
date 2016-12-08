package com.github.lariscy.jserverrepo.client.eventbus;

import com.github.lariscy.jserverrepo.shared.User;

/**
 * @author Steven Lariscy
 */
public class LogoutEvent extends SimpleEvent {
    
    private User user;
    
    public LogoutEvent(boolean success) {
        super(success);
    }

    public LogoutEvent(boolean success, String message) {
        super(success, message);
    }

    public LogoutEvent(boolean success, String message, User user) {
        super(success, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
