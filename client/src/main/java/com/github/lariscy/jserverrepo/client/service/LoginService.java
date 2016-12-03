package com.github.lariscy.jserverrepo.client.service;

import com.github.lariscy.jserverrepo.client.model.User;

/**
 * @author Steven Lariscy
 */
public interface LoginService {
    
    void login(User user);
    void loginGuest(User user);
    void logout(User user);
    
}
