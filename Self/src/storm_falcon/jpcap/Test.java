package storm_falcon.jpcap;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

/**
 * @author gewp
 *
 * require jpcap.jar
 * libpcap.so
 */
public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.library.path"));
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        for (NetworkInterface device : devices) {
            System.out.println(device.name);
        }

        JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[0], 65535, false, 50);
        for (int i = 0; i < 10; i++) {
            System.out.println("--------------------------");
            Packet packet = jpcap.getPacket();

            System.out.println(packet.getClass());
            System.out.println(packet.data.length);
            if (packet instanceof IPPacket) {
                IPPacket ipPacket = (IPPacket) packet;
                System.out.println("Protocol:" + ipPacket.protocol);
                System.out.println("Src ip:" + ipPacket.src_ip.getHostAddress());
                System.out.println("Dst ip:" + ipPacket.dst_ip.getHostAddress());
            }
        }
    }
}
