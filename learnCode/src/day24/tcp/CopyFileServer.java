package day24.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CopyFileServer {
    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        OutputStream outputStream=null;
        InputStreamReader ireader=null;
        OutputStreamWriter writer=null;
        Socket socket=null;
        try {
            serverSocket =new ServerSocket(8888);// 服务端提供端口号
            socket = serverSocket.accept();
            ireader=new InputStreamReader(new FileInputStream("D:\\io\\11.txt"));
            outputStream =socket.getOutputStream();
            writer=new OutputStreamWriter(outputStream);
            char[]chars=new char[1024];
            int len=0;
            while ((len=ireader.read(chars))!=-1){
                writer.write(chars,0,len);
            }

            System.out.println("服务端文件传输完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            if(ireader!=null){
                try {
                    ireader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(writer!=null){
                try {
                   writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
