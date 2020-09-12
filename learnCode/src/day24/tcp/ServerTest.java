package day24.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6666);
             Socket socket = serverSocket.accept();
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream();) {
            System.out.println("客户端已经上线");
            outputStream.write("hello".getBytes());

            socket.shutdownOutput();
            byte[]bytes=new byte[1024];
            int len=0;
            while ((len=inputStream.read(bytes))!=-1){
                System.out.println(new String(bytes,0,len));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
