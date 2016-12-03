package com.github.lariscy.jserverrepo.client.eventbus;

/**
 * @author Steven Lariscy
 */
public class LogoutEvent extends SimpleEvent {
    
    public LogoutEvent(boolean success) {
        super(success);
    }

    public LogoutEvent(boolean success, String message) {
        super(success, message);
    }
    
}
