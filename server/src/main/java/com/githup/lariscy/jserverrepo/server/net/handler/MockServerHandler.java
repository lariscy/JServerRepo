package com.githup.lariscy.jserverrepo.server.net.handler;

import com.githup.lariscy.jserverrepo.server.net.NettyServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class MockServerHandler extends SimpleChannelInboundHandler<Object> {
    
    private static final Logger LOG = LoggerFactory.getLogger(MockServerHandler.class);

    private final NettyServer nettyServer;

    public MockServerHandler(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOG.info("received message: "+ msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        nettyServer.removeChannel(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        nettyServer.addChannel(ctx.channel());
    }

}
