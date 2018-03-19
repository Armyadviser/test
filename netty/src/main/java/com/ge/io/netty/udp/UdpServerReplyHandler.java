package com.ge.io.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * 用于接收数据后返回给客户端
 */
public abstract class UdpServerReplyHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    protected abstract String service(String request);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String request = UdpPacketCoder.decode(msg);
        String response = service(request);
        if (response != null) {
            ctx.writeAndFlush(new DatagramPacket(
                    UdpPacketCoder.encode(response), msg.sender()));
        }
    }
}
