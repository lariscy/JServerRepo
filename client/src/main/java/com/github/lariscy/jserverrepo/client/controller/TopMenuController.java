package com.github.lariscy.jserverrepo.client.controller;

import com.github.lariscy.jserverrepo.client.AppGUI;
import com.github.lariscy.jserverrepo.client.eventbus.LogoutEvent;
import com.github.lariscy.jserverrepo.client.service.LoginService;
import com.github.lariscy.jserverrepo.shared.User;
import com.google.common.eventbus.Subscribe;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class TopMenuController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(TopMenuController.class);
    
    @FXML
    private MenuItem miLogout;
    
    @Inject
    private LoginService loginService;
    @Inject
    private ExecutorService backgroundExecutor;
    @Inject
    private AppGUI appGUI;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LOG.debug("initialize");
    }    

    @FXML
    private void handleLogout(ActionEvent event) {
        //@TODO dummy
        backgroundExecutor.execute(() -> loginService.logout(new User("dummy", null)));
    }
    
    @Subscribe
    public void ebHandleLogoutEvent(LogoutEvent logoutEvent){
        if (logoutEvent.isSuccess()){
            appGUI.loadLoginForm();
        }
    }
    
}
