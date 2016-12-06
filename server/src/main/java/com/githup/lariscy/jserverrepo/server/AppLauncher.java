package com.githup.lariscy.jserverrepo.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Steven
 */
public class AppLauncher {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppLauncher.class);
    
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
    
    public static void main(String[] args) {
        new AppLauncher().start();
    }
    
    public void start(){
        App app = (App) context.getBean("app");
        app.startServer();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.stopServer();
        }));
    }

}
