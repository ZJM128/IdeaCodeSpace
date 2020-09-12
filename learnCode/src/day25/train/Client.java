package day25.train;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket=null;
        Scanner scanner=new Scanner(System.in);
        OutputStream outputStream;
        InputStream inputStream;
        InputStreamReader reader=null;
        try {
            socket=new Socket("127.0.0.1",9999);
            while (true) {
                outputStream = socket.getOutputStream();
                // 向服务器发信息的时候不能用其他流来封装,只能用字节流
                System.out.print("请输入要查询的词语:");
                String key = scanner.next();
                outputStream.write(key.getBytes());

                inputStream = socket.getInputStream();
                reader = new InputStreamReader(inputStream);
                char[] bytes = new char[1024];
                int len = reader.read(bytes);
                String value = new String(bytes, 0, len);
                System.out.println("查询的结果:" + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(socket!=null) {
                try {
                    reader.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
