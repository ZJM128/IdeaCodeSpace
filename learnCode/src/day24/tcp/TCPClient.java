package day24.tcp;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        Scanner scanner = new Scanner(System.in);;
        OutputStream outputStream = null;
        try {

            System.out.println("已经连接上服务器");
            socket = new Socket("127.0.0.1", 9999);
            while (true) {
                inputStream = socket.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                byte[] bytes = new byte[1024];
                int len = 0;

               len=inputStream.read(bytes);
                String string = new String(bytes, 0, len);
                System.out.println("收到服务器端的回话");
                System.out.println(string);
                System.out.println("请输入对服务器说的话");
                outputStream = socket.getOutputStream();
                byte[] bytes1 = scanner.next().getBytes();
                outputStream.write(bytes1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
