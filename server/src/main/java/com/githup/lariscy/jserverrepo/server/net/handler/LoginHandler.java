package com.githup.lariscy.jserverrepo.server.net.handler;

import com.githup.lariscy.jserverrepo.server.service.LoginService;
import com.githup.lariscy.jserverrepo.shared.LoginRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginRequest> {
    
    private static final Logger LOG = LoggerFactory.getLogger(LoginHandler.class);
    
    @Inject
    private LoginService loginService;

    public LoginHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {
        LOG.debug("received LoginRequest from {} [{}]", msg.getUser().getUsername(), ctx.channel().remoteAddress());
        switch(msg.getRequestType()){
            case LOGIN:
                loginService.login(msg.getUser());
                break;
            case LOGOUT:
                loginService.logout(msg.getUser());
                break;
        }
    }

}
