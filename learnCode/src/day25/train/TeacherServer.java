package day25.train;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
*@Description:客户端模拟学生咨询，服务器端模拟咨询老师，进行交互。
客户端收到信息：
欢迎咨询尚硅谷
报满了,请报下一期吧
服务器端收到信息：
你好，我想报名就业班
好吧，再见！
*@author:zhijm
*@Date:2020/6/25 16:17
*/
public class TeacherServer {
    public static void main(String[] args) {
        DatagramSocket socket=null;
        DatagramPacket packet;
        Scanner scanner=new Scanner(System.in);
        try {
            // 建立数据传输的通道
            socket=new DatagramSocket(8888);
            while (true) {
                //收取客户端发过来的信息
                byte[] bytes = new byte[1024];
                // 建立接收和发送的数据包
                packet = new DatagramPacket(bytes, bytes.length);
                // 获得数据
                socket.receive(packet);
                System.out.println(new String(bytes, 0, packet.getLength()));


                // 发送消息给客户端
                String info = scanner.next();
                byte[] bytes1 = info.getBytes();
                packet = new DatagramPacket(bytes1, bytes1.length, InetAddress.getLocalHost(), 9999);
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(socket!=null)
                socket.close();
        }
    }
}
