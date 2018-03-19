package com.ge.io.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

class NettyUdpTest {

    @Test
    void clientTest() throws IOException {
        String data = "5678";
        byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
        InetSocketAddress targetHost = new InetSocketAddress("127.0.0.1", 1105);

        DatagramSocket socket = new DatagramSocket();
        socket.send(new java.net.DatagramPacket(bytes, 0, bytes.length, targetHost));
        socket.close();
    }

    @Test
    void serverTest() throws InterruptedException {
        NettyUdpServer server = new NettyUdpServer(1105);
        server.addHandler(new UdpServerReplyHandler() {
            @Override
            protected String service(String request) {
                System.out.println(request);
                return "xxx---" + request;
            }
        });
        server.start();
        Thread.sleep(60 * 1000);
    }
}