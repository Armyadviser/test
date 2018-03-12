package storm.falcon.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpServer {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();

        // TCP使用ServerBootstrap
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
                        .addLast(new DatagramPacketToDatagramPacketEncoder())
                        .addLast(new DatagramPacketToStringDecoder())
                        .addLast(new EchoServerHandler());
            }
        });

        bootstrap.bind(8080).sync();//.channel().closeFuture().await();

        Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
    }

}
