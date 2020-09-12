package day19.review;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamTest {
    @Test
    public void test01(){

        // 创建流对象
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:\\IOtest\\mysql.txt");
            // 对数据进行操作
            // 单个字节的处理
           /* int i=0;
            while ((i=inputStream.read())!=-1){
                System.out.print((char)i);
            }*/
           // 数组处理
            byte[]bytes=new byte[1024];
            int len=0; // 代表从文件中读取了多少个字符
            while((len=inputStream.read(bytes))!=-1){
                //System.out.println(Arrays.toString(bytes));
                System.out.println(new String(bytes,0,len));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
