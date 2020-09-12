package day24.tcp;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CopyFileClient {
    public static void main(String[] args) {
        try(Socket socket=new Socket("127.0.0.1",8888);
            InputStream inputStream = socket.getInputStream();
            InputStreamReader reader=new InputStreamReader(inputStream);
            OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("D:\\io\\12.txt"))
        ){
            char[]chars=new char[1024];
            int len=0;
            while((len=reader.read(chars))!=-1){
                writer.write(chars,0,len);
            }
            System.out.println("客户端文件下载完毕");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
