package storm.falcon.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class UdpClient {

    public static void main(String[] args) throws Exception {
        String data = "5678";
        byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
        InetSocketAddress targetHost = new InetSocketAddress("127.0.0.1", 8080);

        DatagramSocket socket = new DatagramSocket();
        socket.send(new java.net.DatagramPacket(bytes, 0, bytes.length, targetHost));
        socket.close();

//        System.out.println(Charset.defaultCharset());

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {
            @Override
            protected void initChannel(NioDatagramChannel ch) {
                ch.pipeline()

                        // TODO not decide
//                        .addLast(new StringDecoder())
//                        .addLast(new StringEncoder())
//                        .addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()))
//                        .addLast(new DatagramPacketToStringDecoder())
                        .addLast(new EchoClientHandler());
            }
        });

        // different from server is port.
        Channel channel = bootstrap.bind(0).sync().channel();

        channel.writeAndFlush(new DatagramPacket(
                Unpooled.copiedBuffer(
                        "123".getBytes(CharsetUtil.UTF_8)),
                new InetSocketAddress("127.0.0.1", 8080)))
        .sync();

        if (!channel.closeFuture().await(2000)) {
            System.out.println("error");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
    }
}
