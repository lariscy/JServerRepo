package com.github.lariscy.jserverrepo.client.guice;

import com.github.lariscy.jserverrepo.client.concurrent.util.ExecutorsUtil;
import com.github.lariscy.jserverrepo.client.service.LoginService;
import com.github.lariscy.jserverrepo.client.service.NetworkLoginService;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import java.util.concurrent.ExecutorService;
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
        bind(EventBus.class).toInstance(getAsynEventBus());
        bind(ExecutorService.class).toInstance(getBackgroundExecutor());
        bind(LoginService.class).to(NetworkLoginService.class);
    }
    
    private EventBus eventBus;
    private EventBus getAsynEventBus(){
        if (eventBus == null){
            eventBus = new AsyncEventBus(ExecutorsUtil.getSingleThreadExecutor("EventBus Executor Thread"));
        }
        return eventBus;
    }
    
    private ExecutorService backgroundExecutor;
    private ExecutorService getBackgroundExecutor(){
        if (backgroundExecutor == null){
            backgroundExecutor = ExecutorsUtil.getFixedThreadPoolExecutor("Background Thread", 2);
        }
        return backgroundExecutor;
    }

}
