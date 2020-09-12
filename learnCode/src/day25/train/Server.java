package day25.train;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/*
*@Description:模拟客户端输入要查询的中文，服务器返回对应的英文单词
*@author:zhijm
*@Date:2020/6/25 15:26
*/
public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        Socket socket=null;
        InputStream inputStream;
        OutputStream outputStream;
        InputStreamReader reader=null;
        Map<String,String>map=new HashMap<>();
        map.put("星期一","Monday");
        map.put("星期二","Tuesday");
        map.put("星期三","Wednesday");
        map.put("星期四","Thursday");
        map.put("星期五","Friday");
        try {
            serverSocket=new ServerSocket(9999);
            socket= serverSocket.accept();
            while(true) {
                inputStream = socket.getInputStream();
                reader = new InputStreamReader(inputStream);
                outputStream = socket.getOutputStream();
                // 获取客户端发过来的信息
                char[] chars = new char[1024];
                int lem = 0;

                lem = reader.read(chars);
                String key = new String(chars, 0, lem);
                String value = map.get(key);
                if (value == null) {
                    outputStream.write("查无此词语".getBytes());
                } else {
                    outputStream.write(value.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(socket!=null){
                try {
                    reader.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverSocket!=null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
