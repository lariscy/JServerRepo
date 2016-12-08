package com.github.lariscy.jserverrepo.client.controller;

import com.github.lariscy.jserverrepo.client.AppGUI;
import com.github.lariscy.jserverrepo.client.eventbus.LoginEvent;
import com.github.lariscy.jserverrepo.client.service.LoginService;
import com.github.lariscy.jserverrepo.shared.User;
import com.google.common.eventbus.Subscribe;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class LoginFormController implements Initializable {

    private static final Logger LOG = LoggerFactory.getLogger(LoginFormController.class);
    
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink btnGuestLogin;
    @FXML
    private Label lblError;
    
    @Inject
    private AppGUI appGUI;
    @Inject
    private LoginService loginService;
    @Inject
    private ExecutorService backgroundExecutor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LOG.debug("initialize");
        usernameFocus();
    }
    
    private void usernameFocus(){
        Platform.runLater(txtUsername::requestFocus);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        backgroundExecutor.execute(() -> 
            loginService.login(new User(txtUsername.getText(), txtPassword.getText())));
    }

    @FXML
    private void handleGuestLogin(ActionEvent event) {
        backgroundExecutor.execute(() -> 
            loginService.loginGuest(new User("Guest", null)));
    }
    
    @Subscribe
    public void ebHandleLoginEvent(LoginEvent loginEvent){
        LOG.debug("LoginEvent received: {}", loginEvent);
        if (loginEvent.isSuccess()){
            appGUI.loadServerTree();
        } else {
            Platform.runLater(() -> 
                lblError.setText(loginEvent.getMessage()));
        }
    }
    
}
