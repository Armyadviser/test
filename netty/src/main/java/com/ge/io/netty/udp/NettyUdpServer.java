package com.ge.io.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.internal.SocketUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class NettyUdpServer extends Thread {

    private final InetSocketAddress address;

    private final List<ChannelHandler> handlers = new ArrayList<>();

    public NettyUdpServer(int port) {
        address = new InetSocketAddress(port);
    }

    public NettyUdpServer(String ip, int port) {
        address = SocketUtils.socketAddress(ip, port);
    }

    public void addHandler(ChannelHandler handler) {
        this.handlers.add(handler);
    }

    public void run() {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {
            @Override
            protected void initChannel(NioDatagramChannel ch) {
                ChannelPipeline entries = ch.pipeline();
                handlers.forEach(entries::addLast);
            }
        });
        bootstrap.bind(address);

        Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
    }
}
