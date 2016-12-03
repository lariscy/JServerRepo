package com.github.lariscy.jserverrepo.client.eventbus;

/**
 * @author Steven Lariscy
 */
public class SimpleEvent {
    
    private boolean success;
    private String message;

    public SimpleEvent(boolean success) {
        this.success = success;
    }

    public SimpleEvent(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
