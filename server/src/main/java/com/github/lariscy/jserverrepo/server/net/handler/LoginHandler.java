package com.github.lariscy.jserverrepo.server.net.handler;

import com.github.lariscy.jserverrepo.server.service.LoginService;
import com.github.lariscy.jserverrepo.shared.LoginRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginRequest> {
    
    private static final Logger LOG = LoggerFactory.getLogger(LoginHandler.class);
    
    private final LoginService loginService;

    public LoginHandler(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {
        LOG.debug("received LoginRequest from {} [{}]", msg.getUser().getUsername(), ctx.channel().remoteAddress());
        switch(msg.getRequestType()){
            case LOGIN:
                LOG.debug("login result: {}", loginService.login(msg.getUser()));
                break;
            case LOGOUT:
                LOG.debug("logout result: {}", loginService.logout(msg.getUser()));
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        LOG.error("exception caught in handler", cause);
    }

}
