package day20;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * 字符流
 */
public class FileReaderAndWriterTest {
    @Test
    public void test01(){
        FileReader fileReader=null;
        FileWriter fileWriter=null;
        try {
            // 创建流对象
            fileReader=new FileReader("D:\\IOtest\\mysql1.txt");
            fileWriter=new FileWriter("D:\\testIO\\66.html");
            // 操作数据
            int i=0;
            while((i=fileReader.read())!=-1){// 一次读取一个字符 存在i中
                fileWriter.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流对象
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test02(){
        FileReader fileReader=null;
        FileWriter fileWriter=null;
        try {
            // 创建流对象
            fileReader=new FileReader("D:\\IOtest\\mysql1.txt");
            fileWriter=new FileWriter("D:\\testIO\\99.html");

            char[]chars=new char[5];
            int len=0;
            while ((len=fileReader.read(chars))!=-1) {// 一次读多个字符
                fileWriter.write(chars,0,len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test03(){
        FileReader fileReader=null;
        FileWriter fileWriter=null;

        try {
            // 创建流对象
            fileReader=new FileReader("D:\\IOtest\\mysql1.txt");
            fileWriter=new FileWriter("D:\\testIO\\88.html");
            // 操作数据
            int i=0;
            while((i=fileReader.read())!=-1){
                fileWriter.write(String.valueOf((char) i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流对象
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test04(){
        FileReader fileReader=null;
        FileWriter fileWriter=null;
        try {
            // 流对象的创建
            fileReader=new FileReader("D:\\IOtest\\mysql1.txt");
            fileWriter=new FileWriter("D:\\testIO\\1010.html");
            // 数据的操作
            char[]chars=new char[1024];
            int len=0;
            while((len=fileReader.read(chars))!=-1){
                fileWriter.write(new String(chars),0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 流对象的关闭
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test05(){
        FileReader fileReader=null;
        FileWriter fileWriter=null;

        try {
            fileReader=new FileReader("D:\\IOtest\\mysql1.txt");
            fileWriter=new FileWriter("D:\\testIO\\22.html");
            char[]chars=new char[1024];
            int len=0;
            while((len=fileReader.read(chars))!=-1){
                fileWriter.write(new String(chars,0,len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
