package day19.homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test03 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        //copyFileByOutputStream("D:\\大数据培训资料\\java基础资料\\day01\\视频\\9. 标识符.avi","D:\\testoi\\12.MP4");//1392
        copyFileByBuffered("D:\\大数据培训资料\\java基础资料\\day01\\视频\\9. 标识符.avi","D:\\testoi\\13.MP4");//269
        long end=System.currentTimeMillis();
        System.out.println("耗时"+(end-start));

    }

    public static void copyFileByOutputStream(String fromPath,String toPath){
        try( FileInputStream fileInputStream= new FileInputStream(fromPath);
             FileOutputStream fileOutputStrea=new FileOutputStream(toPath);
        ){
            byte[]bytes=new byte[1024];
            int len=0;
            while((len=fileInputStream.read(bytes))!=-1){
                fileOutputStrea.write(bytes,0,len);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static  void copyFileByBuffered(String fromPath,String toPath){
        try( BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(fromPath));
             BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(toPath))
        ){
            byte[]bytes=new byte[1024];
            int len=0;
            while((len=bufferedInputStream.read(bytes))!=-1){
                bufferedOutputStream.write(bytes,0,len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
