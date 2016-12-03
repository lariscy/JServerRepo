package com.github.lariscy.jserverrepo.client.service;

import com.github.lariscy.jserverrepo.client.eventbus.LoginEvent;
import com.github.lariscy.jserverrepo.client.eventbus.LogoutEvent;
import com.github.lariscy.jserverrepo.client.model.User;
import com.github.lariscy.jserverrepo.client.model.UserRole;
import com.google.common.eventbus.EventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
@Singleton
public class MockLoginService implements LoginService {
    
    private static final Logger LOG = LoggerFactory.getLogger(MockLoginService.class);

    @Inject
    private EventBus eventBus;
    
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "user";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String READ_ONLY_USERNAME = "readonly";
    private static final String READ_ONLY_PASSWORD = "reaonly";
    
    private User user;
    
    @Override
    public void login(User user) {
        LOG.debug("login()");
        if (user.getUsername().equals(USER_USERNAME) && user.getPassword().equals(USER_PASSWORD)){
            user.setUserRole(UserRole.USER);
            user.setLoggedIn(true);
        } else if (user.getUsername().equals(ADMIN_USERNAME) && user.getPassword().equals(ADMIN_PASSWORD)){
            user.setUserRole(UserRole.ADMIN);
            user.setLoggedIn(true);
        } else if (user.getUsername().equals(READ_ONLY_USERNAME) && user.getPassword().equals(READ_ONLY_PASSWORD)){
            user.setUserRole(UserRole.READ_ONLY);
            user.setLoggedIn(true);
        } else {
            user.setLoggedIn(false);
        }
        user.setPassword(null); // clear user password so its not saved in memory
        this.user = user;
        eventBus.post(new LoginEvent(user.isLoggedIn(), 
                user.isLoggedIn() ? "login success" : "Error: Login Failed!"));
    }

    @Override
    public void loginGuest(User user) {
        LOG.debug("loginGuest()");
        user.setUserRole(UserRole.READ_ONLY);
        user.setLoggedIn(true);
        this.user = user;
        eventBus.post(new LoginEvent(true, "guest login successful"));
    }

    @Override
    public void logout(User user) {
        user = null;
        this.user = user;
        eventBus.post(new LogoutEvent(true, "logout success"));
    }

    public User getUser() {
        return user;
    }
    
}
