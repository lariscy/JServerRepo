package com.github.lariscy.jserverrepo.server.service;

import com.github.lariscy.jserverrepo.shared.User;
import org.springframework.stereotype.Service;

/**
 * @author Steven
 */
@Service
public class MockLoginService implements LoginService {

    @Override
    public boolean login(User user) {
        //@TODO real login logic
        return true;
    }

    @Override
    public boolean logout(User user) {
        //@TODO real logout logic
        return false;
    }
    
}
