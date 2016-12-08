package com.github.lariscy.jserverrepo.server.service;

import com.github.lariscy.jserverrepo.shared.User;

/**
 * @author Steven
 */
public interface LoginService {
    
    boolean login(User user);
    boolean logout(User user);

}
