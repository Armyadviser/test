package com.ge.io.netty.udp;

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
        server.addHandler(new UdpServerReceiveHandler(false) {
            @Override
            protected void receive(String request) {
                System.out.println(request);
            }
        });
        server.addHandler(new UdpServerReplyHandler() {
            @Override
            public String service(String request) {
                return "xxx---" + request;
            }
        });
        server.start();
        Thread.sleep(60 * 1000);
    }
}