package storm.falcon.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpServer {

    public void run() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        // TCP使用ServerBootstrap
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {
            @Override
            protected void initChannel(NioDatagramChannel ch) {
                ch.pipeline()
                        .addLast(new UdpStringDecoder())
                        .addLast(new EchoHandler());
            }
        });

        bootstrap.bind(8080).sync();//.channel().closeFuture().await();

        Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
    }

    public static void main(String[] args) throws Exception {
        new UdpServer().run();
    }
}
