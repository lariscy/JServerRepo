package com.github.lariscy.jserverrepo.shared;

import java.io.Serializable;

/**
 * @author Steven
 */
public class TestObj implements Serializable {
    
    private String message;

    public TestObj(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
