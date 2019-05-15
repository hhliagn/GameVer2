package com.game.netty.server;

import com.game.MessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    private static MessageHandler messageHandler = new MessageHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        String revMsg = messageHandler.handle(msg);

        // 返回客户端消息
        if (revMsg != null){
            ctx.writeAndFlush(revMsg +"\n");
        }
    }
}
