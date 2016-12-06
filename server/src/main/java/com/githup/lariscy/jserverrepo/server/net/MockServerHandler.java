package com.githup.lariscy.jserverrepo.server.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Steven
 */
public class MockServerHandler extends SimpleChannelInboundHandler<Object> {

    private final NettyServer nettyServer;

    public MockServerHandler(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext chc, Object i) throws Exception {
        
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
