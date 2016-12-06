package com.githup.lariscy.jserverrepo.server.net.handler;

import com.githup.lariscy.jserverrepo.server.net.NettyServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Steven
 */
public class ChannelGroupHandler extends SimpleChannelInboundHandler<Object> {
    
    private final NettyServer nettyServer;

    public ChannelGroupHandler(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chc, Object i) throws Exception {
        // not doing anything here
        // class only used for keeping up with ChannelGroup in NettyServer
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        nettyServer.addChannel(ctx.channel());
        super.channelActive(ctx);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        nettyServer.removeChannel(ctx.channel());
        super.channelInactive(ctx);
    }

}
