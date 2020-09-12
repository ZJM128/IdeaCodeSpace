package day24.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendAndReceive {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket packet=null;
        Scanner scanner=new Scanner(System.in);
        try {
            socket=new DatagramSocket(8888);
            // 先发送
            while (true) {
                System.out.println("请输入需要发送的文字");
                String string = scanner.next();
                byte[] bytes = string.getBytes();
                packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 9999);
                socket.send(packet);
                System.out.println("发送成功");
                System.out.println("===========================================");
                byte[] bytes1 = new byte[1024];
                packet = new DatagramPacket(bytes1, bytes.length);
                socket.receive(packet);
                System.out.println("收到的回复");
                System.out.println(new String(bytes1, 0, packet.getLength()));
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
