package day24.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Send {
    public static void main(String[] args) {
        DatagramSocket socket=null;
        DatagramPacket packet=null;
        try {
            socket=new DatagramSocket();
            String str ="你好";
            packet=new DatagramPacket(str.getBytes(),str.getBytes().length, InetAddress.getLocalHost(),8888);
            socket.send(packet);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(socket!=null){
                socket.close();
            }
        }
    }
}
