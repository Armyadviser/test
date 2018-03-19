package storm.falcon.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class EchoServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
        String content = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println(content);

        ctx.writeAndFlush(new DatagramPacket(
                Unpooled.copiedBuffer(
                        ("server receive your msg:" + content).getBytes(CharsetUtil.UTF_8)),
                msg.sender()));
    }
}
