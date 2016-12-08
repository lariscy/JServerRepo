package com.github.lariscy.jserverrepo.client.service;

import com.github.lariscy.jserverrepo.client.net.NetworkClient;
import com.github.lariscy.jserverrepo.shared.LoginRequest;
import com.github.lariscy.jserverrepo.shared.TestObj;
import com.github.lariscy.jserverrepo.shared.User;
import io.netty.util.concurrent.Future;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class NetworkLoginService implements LoginService {

    private static final Logger LOG = LoggerFactory.getLogger(NetworkLoginService.class);
    
    @Inject
    private NetworkClient networkClient;
    
    @Override
    public void login(User user) {
        LOG.debug("requesting to login user: "+user.getUsername());
        networkClient.getChannelHandlerContext().writeAndFlush(new LoginRequest(LoginRequest.Type.LOGIN, user));
    }

    @Override
    public void loginGuest(User user) {
        LOG.debug("requesting to login user: "+user.getUsername());
        Future f = networkClient.getChannelHandlerContext().writeAndFlush(new TestObj("hello world"));
        f.addListener((Future f1) -> {
            LOG.debug("success: {}", f1.isSuccess());
            if (!f1.isSuccess()) {
                LOG.debug("cause", f1.cause());
            }
        });
    }

    @Override
    public void logout(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
