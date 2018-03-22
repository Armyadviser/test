package com.ge.io.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;

/**
 * 用于接收数据后返回给客户端
 */
public abstract class UdpServerReplyHandler
        extends ChannelInboundHandlerAdapter
        implements ServerService{

    private boolean autoRelease = false;

    public UdpServerReplyHandler() {
        this(false);
    }

    public UdpServerReplyHandler(boolean autoRelease) {
        this.autoRelease = autoRelease;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof DatagramPacket) {
            DatagramPacket message = (DatagramPacket) msg;
            String request = UdpPacketCoder.decode(message);
            String response = service(request);

            if (response != null) {
                ctx.write(new DatagramPacket(
                        UdpPacketCoder.encode(response), message.sender()));
            }
        }

        if (autoRelease) {
            ReferenceCountUtil.release(msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
