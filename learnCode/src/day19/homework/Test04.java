package day19.homework;

import java.io.*;

public class Test04 {
    public static void main(String[] args) {

        GBKToUFT8("D:\\IOtest\\mysql2.txt","D:\\testoi\\12.txt");
    }
    public static void GBKToUFT8(String fromPath,String toPath){
        InputStreamReader reader=null;
        OutputStreamWriter writer = null;
        try {
            //流对象的创建
            reader=new InputStreamReader(new FileInputStream(fromPath),"GBK");// 将字符转为字节
            writer=new OutputStreamWriter(new FileOutputStream(toPath),"UTF-8");// 将字节转为字符
            // 数据的操作
            char[]chars=new char[1024];
            int len=0;
            while((len=reader.read(chars))!=-1){
                writer.write(chars,0,len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 流的关闭
            try {
                if(reader!=null)
                  reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
