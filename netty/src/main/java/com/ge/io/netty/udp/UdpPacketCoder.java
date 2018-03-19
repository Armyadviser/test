package com.ge.io.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UdpPacketCoder {

    public static String decode(DatagramPacket packet) {
        return packet.content().toString(CharsetUtil.UTF_8);
    }

    public static ByteBuf encode(String data) {
        return Unpooled.copiedBuffer(data.getBytes(CharsetUtil.UTF_8));
    }
}
