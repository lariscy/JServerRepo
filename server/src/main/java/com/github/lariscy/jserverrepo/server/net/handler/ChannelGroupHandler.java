package com.github.lariscy.jserverrepo.server.net.handler;

import com.github.lariscy.jserverrepo.server.net.NettyServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Steven
 */
public class ChannelGroupHandler extends ChannelInboundHandlerAdapter {
    
    private final NettyServer nettyServer;

    public ChannelGroupHandler(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
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
