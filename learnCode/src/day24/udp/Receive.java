package day24.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receive {
    public static void main(String[] args) {
        DatagramSocket socket=null;
        DatagramPacket packet=null;
        try {
            socket=new DatagramSocket(8888);
            byte[]bytes=new byte[1024];
            packet=new DatagramPacket(bytes,bytes.length);
            socket.receive(packet);
            System.out.println(new String(bytes,0,packet.getLength()));
           // packet
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(socket!=null)
              socket.close();
        }

    }
}

