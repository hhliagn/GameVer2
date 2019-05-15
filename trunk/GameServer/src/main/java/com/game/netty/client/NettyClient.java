package com.game.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

public class NettyClient {

    public static String host = "127.0.0.1";
    public static int port = 8888;

    private static EventLoopGroup group = new NioEventLoopGroup();
    private static Bootstrap b = new Bootstrap();
    private static Channel ch;

    public NettyClient() throws InterruptedException, IOException {
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new NettyClientFilter());
        ChannelFuture f = b.connect(host, port).sync();
        ch = f.channel();
        System.out.println("客户端启动");
    }

    public void send(String str) throws IOException{
        ch.writeAndFlush(str+ "\r\n");
    }
}
