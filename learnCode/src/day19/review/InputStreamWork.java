package day19.review;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InputStreamWork {
    @Test
    public void test01(){
        File file =new File("D:\\IOtest\\mysql.txt");
        FileInputStream fis=null;
        try {
            // 创建流对象
           fis=new FileInputStream(file);
           // 操作数据
            int i=0;
            while((i=fis.read())!=-1){
                System.out.print((char)i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try{
                if(fis!=null) {
                    fis.close();
                };
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test02(){
        FileInputStream fileInputStream=null;
        try{
            // 创建流对象
            fileInputStream=new FileInputStream("D:\\IOtest\\mysql.txt");
            // 数据操作
            byte[]bytes=new byte[1024];
            int len=0;
            // 1 read(bytes) 把文件中的字符十个读到数据中去,
            while((len=fileInputStream.read(bytes))!=-1){
                System.out.println(new String(bytes,0,len));

            }
        }catch(Exception e){
          e.printStackTrace();
        }finally{
            try{
                if(fileInputStream!=null){
                    fileInputStream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    @Test
    public void test03(){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream("D:\\IOtest\\mysql.txt");
            fos=new FileOutputStream("D:\\IOtest\\mysql1.txt");
            byte[]bytes=new byte[1024];
            int len=-0;
            while((len=fis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
               if(fis!=null){
                   fis.close();
               }

               if(fos!=null){
                   fos.close();
               }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
