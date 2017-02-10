package com.github.lariscy.jserverrepo.server;

import com.github.lariscy.jserverrepo.server.dao.NodeDaoMysql;
import com.github.lariscy.jserverrepo.server.model.Node;
import com.github.lariscy.jserverrepo.server.net.NettyServer;
import com.google.gson.Gson;
import java.util.List;
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
        
        // @TODO remove below test
        NodeDaoMysql nodeDao = context.getBean(NodeDaoMysql.class);
        List<Node> nodes = nodeDao.getNodes();
        Gson gson = new Gson();
        System.out.println(gson.toJson(nodes));
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
