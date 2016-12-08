package com.github.lariscy.jserverrepo.server;

import com.github.lariscy.jserverrepo.server.guice.GuiceModule;
import com.github.lariscy.jserverrepo.server.net.NettyServer;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class App {
    
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static final Injector INJECTOR = Guice.createInjector(new GuiceModule());
    
    @Inject
    private NettyServer nettyServer;
    
    public static void main(String[] args) {
        INJECTOR.getInstance(App.class).run();
    }
    
    public void run(){
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        
        nettyServer.setPort(8585);
        nettyServer.bind();
    }
    
    private void stop(){
        nettyServer.shutdown();
    }

}
