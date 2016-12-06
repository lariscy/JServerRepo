package com.githup.lariscy.jserverrepo.server.service;

import com.githup.lariscy.jserverrepo.shared.User;

/**
 * @author Steven
 */
public interface LoginService {
    
    boolean login(User user);
    boolean logout(User user);

}
