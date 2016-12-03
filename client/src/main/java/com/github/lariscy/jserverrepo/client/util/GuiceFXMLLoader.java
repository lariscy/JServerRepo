package com.github.lariscy.jserverrepo.client.util;

import com.google.common.eventbus.EventBus;
import com.google.inject.Injector;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
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
    
    @Inject
    private Provider<FXMLLoader> loaderProvider;
    @Inject
    private Injector injector;
    @Inject
    private EventBus eventBus;
    
    public <T> T load(FXMLView viewName){
        LOG.debug("request to load FXML [{}]", viewName);
        FXMLLoader loader = loaderProvider.get();
        loader.setLocation(getClass().getResource(viewName.toString()));
        loader.setControllerFactory(controllerClass -> {
            Object controller = injector.getInstance(controllerClass);
            LOG.debug("registering class [{}] with EventBus", controllerClass.getName());
            eventBus.register(controller);
            return controller;
        });
        try {
            return loader.load();
        } catch (IOException ex) {
            throw new TypeNotPresentException(String.format("error loading view with name: %s", viewName), ex);
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