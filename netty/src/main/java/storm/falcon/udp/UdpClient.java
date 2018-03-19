package storm.falcon.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

public class UdpClient {

    public static void main(String[] args) throws Exception {
//        String data = "5678";
//        byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
//        InetSocketAddress targetHost = new InetSocketAddress("127.0.0.1", 8080);
//
//        DatagramSocket socket = new DatagramSocket();
//        socket.send(new java.net.DatagramPacket(bytes, 0, bytes.length, targetHost));
//        socket.close();

//        System.out.println(Charset.defaultCharset());

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {
            @Override
            protected void initChannel(NioDatagramChannel ch) {
                ch.pipeline()
                        .addLast(new EchoClientHandler());
            }
        });

        // different from server is port.
        Channel channel = bootstrap.bind(0).sync().channel();
        channel.closeFuture().addListener(future -> {
            System.out.println(future.getNow());
            System.out.println("close");
        });

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                try {
                    channel.writeAndFlush(new DatagramPacket(
                            Unpooled.copiedBuffer(
                                    line.getBytes(CharsetUtil.UTF_8)),
                            new InetSocketAddress("192.168.2.104", 1105))).sync()
                    ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (line.equals("bye")) {
                    break;
                }
            }

            scanner.close();
        }).start();


        Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
    }
}
