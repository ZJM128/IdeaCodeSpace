package day25.train;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
*@Description:使用UDP
*@author:zhijm
*@Date:2020/6/25 16:18
*/
public class StudentClient {
    public static void main(String[] args) {
        DatagramSocket socket=null;
        Scanner scanner=new Scanner(System.in);
        try {
            socket=new DatagramSocket(9999);
            while(true) {
                // 向服务器发信息
                String info = scanner.next();
                DatagramPacket packet = new DatagramPacket(info.getBytes(), info.getBytes().length, InetAddress.getLocalHost(), 8888);
                socket.send(packet);

                // 接收服务发过来的信息
                byte[] bytes = new byte[1024];
                packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                System.out.println(new String(bytes, 0, packet.getLength()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(socket!=null)
                socket.close();
        }

    }
}
