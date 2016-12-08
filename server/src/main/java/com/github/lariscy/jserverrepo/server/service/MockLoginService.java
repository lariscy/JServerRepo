package com.github.lariscy.jserverrepo.server.service;

import com.github.lariscy.jserverrepo.shared.User;

/**
 * @author Steven
 */
public class MockLoginService implements LoginService {

    @Override
    public boolean login(User user) {
        return true;
    }

    @Override
    public boolean logout(User user) {
        return false;
    }
    
}
