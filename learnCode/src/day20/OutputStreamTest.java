package day20;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamTest {
    @Test
    public void test01(){
        FileInputStream inputStream = null;
        FileOutputStream outputStream=null;

        try {
            // 创建流对象
            inputStream=new FileInputStream("D:\\IOtest\\mysql.txt");
            outputStream=new FileOutputStream("D:\\testIO\\mysql66.txt");
            int i=0;
            // 数据操作
            while ((i=inputStream.read())!=-1) {
                outputStream.write(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{

            // 关闭流
                try {
                    if(inputStream!=null) {
                        inputStream.close();
                    }
                    if(outputStream!=null){
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    @Test
    public void test02(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream=null;

        // 创建流对象
        try {
            fileInputStream=new FileInputStream("D:\\IOtest\\mysql.txt");
            fileOutputStream=new FileOutputStream("D:\\testIO\\mysql77.txt");
            // 操作数据
            byte[]bytes=new byte[1024];
            int len=0;
            while((len=fileInputStream.read(bytes))!=-1){
                 fileOutputStream.write(bytes,0,len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 关闭流对象
            try {
                if(fileInputStream!=null) {
                    fileInputStream.close();
                }
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test03(){
        FileInputStream fis= null;
        FileOutputStream fos = null;

        try {
            // 创建流对象
            fis=new FileInputStream("D:\\IOtest\\mysql.txt");
            fos=new FileOutputStream("D:\\testIO\\mysql66.txt",true);// 追加
            // 数据操作
            byte[]bytes=new byte[1024];
            int len=0;
            while((len=fis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
;        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(fis!=null){
                    fis.close();
                }
                if(fos!=null){
                 fos.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
