package com.github.lariscy.jserverrepo.server;

import com.github.lariscy.jserverrepo.server.net.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Steven
 */
@Component
public class App {
    
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    
    @Autowired
    private NettyServer nettyServer;
    
    public static void main(String[] args) {
        LOG.info("App starting");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        App me = context.getBean(App.class);
        me.run();
    }
    
    public void run(){
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        
        nettyServer.setPort(8585);
        if (!nettyServer.bind()){
            LOG.error("server failed to start");
            System.exit(-1);
        }
    }
    
    private void stop(){
        nettyServer.shutdown();
    }

}
