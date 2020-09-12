package day24.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress address=InetAddress.getLocalHost();
            System.out.println(address);
        //    byte[]bytes="192.168.244.1".getBytes();
         /*   InetAddress byAddress = InetAddress.getByAddress(bytes);
            System.out.println(byAddress);*/
            InetAddress byName = InetAddress.getByName("192.168.244.1");
            System.out.println(byName.getAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
