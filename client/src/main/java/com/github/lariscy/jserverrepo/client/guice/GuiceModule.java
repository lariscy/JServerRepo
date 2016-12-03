package com.github.lariscy.jserverrepo.client.guice;

import com.github.lariscy.jserverrepo.client.service.LoginService;
import com.github.lariscy.jserverrepo.client.service.MockLoginService;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class GuiceModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(GuiceModule.class);
    
    @Override
    public void configure() {
        LOG.debug("configuring GuiceModule");
        //bind(AppGUI.class).asEagerSingleton();
        //bind(GuiceFXMLLoader.class).asEagerSingleton();
        bind(EventBus.class).asEagerSingleton();
        bind(LoginService.class).to(MockLoginService.class);
    }

}
