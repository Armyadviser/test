package com.ge.io.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;

/**
 * 仅用于接收，不返回
 */
public abstract class UdpServerReceiveHandler
        extends ChannelInboundHandlerAdapter
        implements ServerService {

    private boolean autoRelease = false;

    public UdpServerReceiveHandler() {}

    public UdpServerReceiveHandler(boolean autoRelease) {
        this.autoRelease = autoRelease;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof DatagramPacket) {
            DatagramPacket request = (DatagramPacket) msg;
            service(UdpPacketCoder.decode(request));
        }

        if (autoRelease) {
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public String service(String request) {
        receive(request);
        return null;
    }

    protected abstract void receive(String request);
}
