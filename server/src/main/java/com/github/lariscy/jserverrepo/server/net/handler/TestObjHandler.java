package com.github.lariscy.jserverrepo.server.net.handler;

import com.github.lariscy.jserverrepo.shared.TestObj;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven
 */
public class TestObjHandler extends SimpleChannelInboundHandler<TestObj> {

    private static final Logger LOG = LoggerFactory.getLogger(TestObjHandler.class);
    
    @Override
    protected void channelRead0(ChannelHandlerContext chc, TestObj i) throws Exception {
        LOG.debug("got a TestObj with message: "+i.getMessage());
    }

}
