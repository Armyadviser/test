package storm.falcon.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.net.InetSocketAddress;
import java.util.List;

public class DatagramPacketToDatagramPacketEncoder extends MessageToMessageEncoder<DatagramPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        ByteBuf content = msg.content();
        InetSocketAddress sender = msg.sender();
        InetSocketAddress recipient = msg.recipient();
        out.add(new DatagramPacket(content, sender));
    }
}
