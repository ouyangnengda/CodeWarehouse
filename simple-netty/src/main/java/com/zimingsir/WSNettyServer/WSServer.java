package com.zimingsir.WSNettyServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description: 一个和网页通信的简易服务器
 * @Author: 欧阳能达
 * @Created: 2019年04月23日 14:05:00
 **/
public class WSServer {
    public static void main(String[] args) throws InterruptedException {


        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(mainGroup, subGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new WSChannel());

            ChannelFuture channel = bootstrap.bind(8088).sync();

            channel.channel().closeFuture().sync();
        } finally {
            mainGroup.shutdownGracefully().sync();
            subGroup.shutdownGracefully().sync();
        }

    }
}
