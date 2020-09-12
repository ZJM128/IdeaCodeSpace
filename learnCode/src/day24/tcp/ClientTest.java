package day24.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTest {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 6666);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()
             ) {
            byte[]bytes=new byte[1024];
            int len=0;
            System.out.println("接收到来自服务器的信息");
            while ((len=inputStream.read(bytes))!=-1){
                System.out.println(new String(bytes,0,len));
            }
            System.out.println("回复一条信息给服务器");
            outputStream.write("我是客户端为,我已经收到了你的信息".getBytes());
            socket.shutdownOutput();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
