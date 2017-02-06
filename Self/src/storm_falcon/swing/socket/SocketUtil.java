package storm_falcon.swing.socket;

import storm_falcon.util.StreamUtil;

import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by falcon on 17-1-1.
 *
 */
public class SocketUtil {

    public static <E> E receive(DatagramSocket socket, Function<byte[], E> mapper) {
        try {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);
            byte[] data = packet.getData();
            return mapper.apply(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Send UDP packet.
     * @param socket UDP socket.
     * @param ip desteny ip.
     * @param port desteny port.
     * @param e packet content.
     * @param serializer convert e to byte[].
     * @param <E> type of e.
     */
    public static <E> void send(DatagramSocket socket, String ip, int port, E e, Function<E, byte[]> serializer) {
        try {
            byte[] data = serializer.apply(e);
            DatagramPacket packet = new DatagramPacket(data, data.length);
            packet.setAddress(InetAddress.getByName(ip));
            packet.setData(data);
            packet.setPort(port);

            socket.send(packet);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static boolean isSocketAvailable(Socket socket) {
        return socket != null
                && socket.isConnected()
                && !socket.isInputShutdown()
                && !socket.isOutputShutdown()
                && !socket.isConnected();
    }

    public static String getLocalAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback()) {
                    continue;
                }
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    String ip = inetAddress.getHostAddress();
                    if (ip.length() > 15) {
                        continue;
                    }
                    return ip;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    private static String getBroadcast(NetworkInterface networkInterface) {
        Optional<String> broadcast = networkInterface.getInterfaceAddresses()
                .stream()
                .map(SocketUtil::getBroadcastIp)
                .findFirst();
        return broadcast.orElse("255.255.255.0");
    }

    /**
     * Get broadcast of network adapter's name.
     * @param name network card's name.
     * @return broadcast ip.
     */
    public static String getBroadcast(String name) {
        try {
            Optional<String> broadcast = StreamUtil.stream(NetworkInterface.getNetworkInterfaces())
                    .filter(networkInterface -> name.equals(networkInterface.getDisplayName()))
                    .map(SocketUtil::getBroadcast)
                    .findFirst();
            if (broadcast.isPresent()) {
                return broadcast.get();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "255.255.255.0";
    }

    public static List<String> getBroadcast() {
        try {
            return StreamUtil.stream(NetworkInterface.getNetworkInterfaces())
                    .map(SocketUtil::getBroadcast)
                    .distinct()
                    .collect(Collectors.toList());
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Get a ip's broadcast ip.
     * @param ipAddress
     * @return
     */
    public static String getBroadcastIp(InterfaceAddress ipAddress) {
        if (ipAddress == null) {
            return "255.255.255.0";
        }
        InetAddress broadcast = ipAddress.getBroadcast();
        if (broadcast == null) {
            return "255.255.255.0";
        }
        String ip = broadcast.toString();
        return ip.substring(1, ip.length());
    }
}
