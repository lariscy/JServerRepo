package com.githup.lariscy.jserverrepo.server;

import com.githup.lariscy.jserverrepo.server.net.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class App {
    
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    
    private final NettyServer nettyServer;
    
    public App(NettyServer nettyServer){
        this.nettyServer = nettyServer;
    }
    
    public void startServer(){
        nettyServer.bind();
    }
    
    public void stopServer(){
        nettyServer.shutdown();
    }

}
