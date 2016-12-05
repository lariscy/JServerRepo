package com.github.lariscy.jserverrepo.client.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.Future;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
@Singleton
public final class NetworkClient {
    
    private static final Logger LOG = LoggerFactory.getLogger(NetworkClient.class);
    
    private ChannelHandlerContext ctx;
    private final Bootstrap b = new Bootstrap();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    private final NetworkClient instance;
    
    public NetworkClient() {
        instance = this;
        setup();
    }
    
    public void setup(){
        LOG.info("setting up network client");
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        //b.option(ChannelOption.SO_KEEPALIVE, true); //@TODO may need this??
        b.handler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(
                    new ObjectEncoder(),
                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                    new MockClientHandler(instance)
                );
            }
        });
    }
    
    public boolean connect(String hostname, int port){
        LOG.info("connecting network client");
        if (ctx==null || (ctx!=null && !isConnected())){
            ChannelFuture connectFuture = b.connect(hostname, port);
            connectFuture.awaitUninterruptibly();
            if (connectFuture.isSuccess()){
               return true; 
            } else {
                LOG.error("failed to connect to server", connectFuture.cause());
            }
        } else {
            LOG.info("NetworkClient already connected, ignoring connect request");
        }
        return false;
    }
    
    public boolean disconnect(){
        LOG.info("disconnecting network client");
        if (ctx!=null && isConnected()){
            ChannelFuture disConnectFuture = ctx.channel().disconnect();
            disConnectFuture.awaitUninterruptibly();
            if (disConnectFuture.isSuccess()){
                return true;
            } else {
                LOG.error("failed to disconnect from server", disConnectFuture.cause());
            }
        } else {
            LOG.info("NetworkClient is not connected, ignoring disconnect request");
        }
        return false;
    }
    
    public boolean closeThreads(){
        LOG.info("closing network client threads");
        Future closeThreadsFuture = workerGroup.shutdownGracefully();
        closeThreadsFuture.awaitUninterruptibly();
        if (closeThreadsFuture.isSuccess()){
            return true;
        } else {
            LOG.error("failed to shutdown NetworkClient threads", closeThreadsFuture.cause());
        }
        return false;
    }
    
    public ChannelHandlerContext getChannelHandlerContext() {
        return ctx;
    }
    
    public void setChannelHandlerContext(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }
    
    public boolean isConnected(){
        return ctx == null ? false : ctx.channel().isActive();
    }
    
}
