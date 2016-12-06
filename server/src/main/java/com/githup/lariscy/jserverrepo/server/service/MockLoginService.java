package com.githup.lariscy.jserverrepo.server.service;

import com.githup.lariscy.jserverrepo.shared.User;

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
