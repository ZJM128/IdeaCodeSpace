package day19;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamTest {
    @Test
    public void test01(){
        File file=new File("D:\\test\\mysql.txt");
        // 创建流对象
        InputStream inputStream=null;
        try {
            //inputStream =new FileInputStream("D:\\test\\mysql.txt");
            inputStream =new FileInputStream(file);
            // 数据处理
            byte[]bytes=new byte[1024];
            int len=0;
            while((len=inputStream.read(bytes))!=-1){
                System.out.println(new String(bytes,0,len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭流
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
