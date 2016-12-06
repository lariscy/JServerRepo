package com.githup.lariscy.jserverrepo.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public final class NettyServer {
    
    private static final Logger LOG = LoggerFactory.getLogger(NettyServer.class);
    
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final ServerBootstrap b = new ServerBootstrap();;
    private final NettyServer instance = this;
    private final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    
    private int port;

    public NettyServer(int port) {
        this.port = port;
        setup();
    }
    
    private void setup(){
        LOG.info("setting up server");
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(
                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                    new ObjectEncoder(),
                    new MockServerHandler(instance)
                );
            }
        });
    }
    
    public void bind(){
        LOG.info("attempting to bind server to port {}", port);
        Future bindFuture = b.bind(port);
        bindFuture.awaitUninterruptibly();
        if (bindFuture.isSuccess()){
            LOG.debug("server now listening on port {} ", port);
        } else {
            LOG.error("server failed to bind to port {}", port, bindFuture.cause());
        }
    }
    
    public void shutdown(){
        System.out.println("server shutting down");
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
    
    public void addChannel(Channel channel){
        channelGroup.add(channel);
        System.out.println("channel ["+channel.remoteAddress()+"] added - current size: "+channelGroup.size());
    }
    
    public void removeChannel(Channel channel){
        channelGroup.remove(channel);
        System.out.println("channel ["+channel.remoteAddress()+"] removed - current size: "+channelGroup.size());
    }

}
