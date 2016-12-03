package com.github.lariscy.jserverrepo.client.eventbus;

/**
 * @author Steven Lariscy
 */
public class LoginEvent extends SimpleEvent {
    
    public LoginEvent(boolean success) {
        super(success);
    }

    public LoginEvent(boolean success, String message) {
        super(success, message);
    }
    
}
