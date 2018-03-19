package storm.falcon.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
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
                        .addLast(new EchoServerHandler())
                ;
            }
        });

        // IO操作都是由线程池异步执行的，返回一个Future之后继续执行下面的代码
        // 如果需要等待方法返回判断可以用sync(),await()等将方法变成同步
        // 或者（建议）使用监听器
        bootstrap.bind("", 1105);//.sync().channel().closeFuture().await();

        Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
    }

}
