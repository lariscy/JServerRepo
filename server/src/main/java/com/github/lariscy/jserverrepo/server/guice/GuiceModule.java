package com.github.lariscy.jserverrepo.server.guice;

import com.github.lariscy.jserverrepo.server.service.LoginService;
import com.github.lariscy.jserverrepo.server.service.MockLoginService;
import com.google.inject.AbstractModule;

/**
 * @author Steven
 */
public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LoginService.class).to(MockLoginService.class);
    }

}
