package storm.falcon.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import io.netty.channel.socket.DatagramPacket;
import java.nio.charset.Charset;
import java.util.List;

public class UdpStringDecoder extends MessageToMessageDecoder<DatagramPacket> {
    private final Charset charset;

    public UdpStringDecoder() {
        this(Charset.defaultCharset());
    }

    public UdpStringDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) {
        out.add(msg.content().toString(charset));
    }
}
