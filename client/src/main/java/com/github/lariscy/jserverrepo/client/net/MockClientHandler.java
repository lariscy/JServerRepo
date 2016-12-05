package com.github.lariscy.jserverrepo.client.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class MockClientHandler extends SimpleChannelInboundHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(MockClientHandler.class);

    private final NetworkClient networkClient;

    public MockClientHandler(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext chx, Object msg) throws Exception {
        LOG.debug("received message: {}", msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        networkClient.setChannelHandlerContext(ctx);
    }

}
