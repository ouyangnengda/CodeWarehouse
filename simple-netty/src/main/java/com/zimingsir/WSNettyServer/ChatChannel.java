package com.zimingsir.WSNettyServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: 欧阳能达
 * @Created: 2019年04月23日 14:15:00
 **/
public class ChatChannel extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        System.out.println(frame.text());

        clients.writeAndFlush(
                new TextWebSocketFrame(
                        "服务器收到消息[" + LocalDateTime.now() + "]: " + frame.text()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    }
}
