package com.github.lariscy.jserverrepo.server.net;

import com.github.lariscy.jserverrepo.server.net.handler.ChannelGroupHandler;
import com.github.lariscy.jserverrepo.server.net.handler.IPFilterHandler;
import com.github.lariscy.jserverrepo.server.net.handler.LoginHandler;
import com.github.lariscy.jserverrepo.server.net.handler.TestObjHandler;
import com.github.lariscy.jserverrepo.server.net.handler.TestStringHandler;
import com.github.lariscy.jserverrepo.server.service.LoginService;
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
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
@Singleton
public class NettyServer {
    
    private static final Logger LOG = LoggerFactory.getLogger(NettyServer.class);
    
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final ServerBootstrap b = new ServerBootstrap();;
    private final NettyServer instance = this;
    private final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    
    private int port;
    
    @Inject
    private LoginService loginService;

    public NettyServer() {
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
                    new IPFilterHandler(), //inbound
                    new ChannelGroupHandler(instance), //inbound
                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)), //inbound
                    new ObjectEncoder(), //outbound
                    new TestObjHandler(), //inbound
                    new LoginHandler(loginService), //inbound
                    new TestStringHandler() //inbound
                );
            }
        });
    }
    
    public boolean bind(){
        LOG.debug("attempting to bind server to port {}", port);
        Future bindFuture = b.bind(port);
        bindFuture.awaitUninterruptibly();
        if (bindFuture.isSuccess()){
            LOG.info("server now listening on port {} ", port);
            return true;
        } else {
            LOG.error("server failed to bind to port {}", port, bindFuture.cause());
            return false;
        }
    }
    
    public void shutdown(){
        LOG.info("server shutting down");
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
    
    public void addChannel(Channel channel){
        channelGroup.add(channel);
        LOG.debug("channel [{}] added - current size: {}", channel.remoteAddress(), channelGroup.size());
    }
    
    public void removeChannel(Channel channel){
        // no point to remove from ChannelGroup because it seems that it gets
        // removed automatically when the channel is closed
        //channelGroup.remove(channel);
        LOG.debug("channel [{}] removed - current size: {}", channel.remoteAddress(), channelGroup.size());
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
