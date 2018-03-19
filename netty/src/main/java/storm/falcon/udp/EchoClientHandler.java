package storm.falcon.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
        System.out.println(msg.content().toString(CharsetUtil.UTF_8));

//        ctx.close();
    }

}
