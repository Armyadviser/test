package storm.falcon.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class DatagramPacketToStringDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        out.add(msg.content().toString(CharsetUtil.UTF_8));
    }
}
