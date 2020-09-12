package day24.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ReceiveOrSend {
    public static void main(String[] args) {
        DatagramSocket socket=null;
        DatagramPacket packet=null;
        Scanner scanner=new Scanner(System.in);
        try {
            socket=new DatagramSocket(9999);
           while (true) {
               byte[] bytes = new byte[1024];
               packet = new DatagramPacket(bytes, bytes.length);
               socket.receive(packet);
               System.out.println("收到的信息");
               System.out.println(new String(bytes, 0, packet.getLength()));
               System.out.println("==============================================");
               System.out.println("请输入回复的信息");
               String s = scanner.next();
               byte[] bytes1 = s.getBytes();
               packet = new DatagramPacket(bytes1, bytes1.length, InetAddress.getLocalHost(), 8888);
               socket.send(packet);
               System.out.println("回复成功");
               System.out.println("==============================================");
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(socket!=null)
                socket.close();
        }

    }
}
