package day24.tcp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("等待客户端的连接~~~~");
            socket = serverSocket.accept();
            System.out.println("客户端已连接!!");
            while (true) {
                outputStream = socket.getOutputStream();
                System.out.println("请输入要对客户说的话");
                outputStream.write(scanner.next().getBytes());

                inputStream = socket.getInputStream();
                System.out.println("收到客户端的回复");
                byte[] bytes = new byte[1024];
                int len = 0;
                len=inputStream.read(bytes);
                System.out.println(new String(bytes,0,len));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (scanner != null) scanner.close();
                if (socket != null) socket.close();
                if (serverSocket != null) serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
