package com.githup.lariscy.jserverrepo.server.net.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ipfilter.AbstractRemoteAddressFilter;
import java.net.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class IPFilterHandler extends AbstractRemoteAddressFilter<InetSocketAddress> {
    
    private static final Logger LOG = LoggerFactory.getLogger(IPFilterHandler.class);

    @Override
    protected boolean accept(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) throws Exception {
        LOG.debug("connection attempt is being made by {}:{}", remoteAddress.getHostName(), remoteAddress.getPort());
        //@TODO configure IPs allowed to access client
        return true;
    }

    @Override
    protected ChannelFuture channelRejected(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) {
        LOG.debug("connection rejected {}:{}", remoteAddress.getHostName(), remoteAddress.getPort());
        return super.channelRejected(ctx, remoteAddress);
    }

    @Override
    protected void channelAccepted(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) {
        LOG.debug("connection accepted {}:{}", remoteAddress.getHostName(), remoteAddress.getPort());
        super.channelAccepted(ctx, remoteAddress);
    }

}
