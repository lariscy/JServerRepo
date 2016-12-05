package com.github.lariscy.jserverrepo.client;

import com.github.lariscy.jserverrepo.client.guice.GuiceModule;
import com.github.lariscy.jserverrepo.client.net.NetworkClient;
import com.gluonhq.ignite.guice.GuiceContext;
import java.util.Arrays;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class App extends Application {
    
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private final GuiceContext context = new GuiceContext(this, 
        () -> Arrays.asList(new GuiceModule()));
    
    private Stage primaryStage;
    
    @Inject
    private AppGUI appGUI;
    @Inject
    private NetworkClient networkClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOG.info("Application.start()");
        this.primaryStage = primaryStage;
        context.init();
        appGUI.start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        LOG.info("Application.stop()");
        if (networkClient.isConnected()){ networkClient.disconnect(); }
        networkClient.closeThreads();
        super.stop();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
