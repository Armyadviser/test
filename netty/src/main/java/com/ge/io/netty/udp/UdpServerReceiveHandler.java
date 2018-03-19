package com.ge.io.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * 仅用于接收，不返回
 */
public abstract class UdpServerReceiveHandler extends SimpleChannelInboundHandler<DatagramPacket> implements ServerService {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        service(UdpPacketCoder.decode(msg));
    }
}
