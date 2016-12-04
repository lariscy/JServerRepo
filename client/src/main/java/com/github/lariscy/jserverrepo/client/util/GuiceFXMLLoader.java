package com.github.lariscy.jserverrepo.client.util;

import com.google.common.eventbus.EventBus;
import com.google.inject.Injector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
@Singleton
public class GuiceFXMLLoader {
    
    private static final Logger LOG = LoggerFactory.getLogger(GuiceFXMLLoader.class);
    
    private final Map<FXMLView, Object> controllerCache = new HashMap<>();
    
    @Inject
    private Provider<FXMLLoader> loaderProvider;
    @Inject
    private Injector injector;
    @Inject
    private EventBus eventBus;
    
    public Parent load(FXMLView viewName){
        LOG.debug("request to load FXML [{}]", viewName);
        FXMLLoader loader = loaderProvider.get();
        loader.setLocation(getClass().getResource(viewName.toString()));
        loader.setControllerFactory(controllerClass -> {
            if (controllerCache.containsKey(viewName)){
                // return cached controller instance
                return controllerCache.get(viewName);
            }
            
            Object controller = injector.getInstance(controllerClass);
            LOG.debug("registering class [{}] with EventBus", controllerClass.getName());
            eventBus.register(controller);
            controllerCache.put(viewName, controller);
            // return new controller instance
            return controller;
        });
        
        try {
            return loader.load();
        } catch (IOException ex) {
            throw new TypeNotPresentException(String.format("error loading view [%s]", viewName), ex);
        }
    }
    
    public enum FXMLView {
        CONTAINER("Container.fxml"),
        TOP_MENU("TopMenu.fxml"),
        SIDE_MENU("SideMenu.fxml"),
        LOGIN_FORM("LoginForm.fxml"),
        SERVER_TREE("ServerTree.fxml"),
        URL_TREE("UrlTree.fxml"),
        STATUS_BAR("StatusBar.fxml");
        
        private final String name;
        private final String FXML_BASE = "/fxml/%s";
        
        FXMLView(String name){
            this.name = String.format(FXML_BASE, name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
