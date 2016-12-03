package com.github.lariscy.jserverrepo.client;

import com.github.lariscy.jserverrepo.client.util.GuiceFXMLLoader;
import com.github.lariscy.jserverrepo.client.util.GuiceFXMLLoader.FXMLView;
import com.google.common.eventbus.EventBus;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class AppGUI {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppGUI.class);
    
    private Stage stage;
    private BorderPane root;
    
    @Inject
    private GuiceFXMLLoader loader;
    @Inject
    private EventBus eventBus;

    public void start(Stage stage){
        this.stage = stage;
        initLayout();
        stage.show();
    }

    private void initLayout() {
        root = loader.load(FXMLView.CONTAINER);
        
        root.setTop(loader.load(FXMLView.TOP_MENU));
        root.setLeft(loader.load(FXMLView.SIDE_MENU));
        root.setCenter(loader.load(FXMLView.LOGIN_FORM));
        root.setBottom(loader.load(FXMLView.STATUS_BAR));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
    public void loadLoginForm(){
        root.setCenter(loader.load(FXMLView.LOGIN_FORM));
    }
    
    public void loadServerTree(){
        root.setCenter(loader.load(FXMLView.SERVER_TREE));
    }
    
    public void loadUrlTree(){
        root.setCenter(loader.load(FXMLView.URL_TREE));
    }

}
