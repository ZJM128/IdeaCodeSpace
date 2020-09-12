package day20.homeWork;

import org.junit.Test;

import java.io.*;

public class CopyImg {
    @Test
    public void test01(){
        BufferedInputStream bfis =null;
        BufferedOutputStream bfos=null;
        try {
            bfis=new BufferedInputStream(new FileInputStream("D:\\IOtest\\12.jpg"));
            bfos=new BufferedOutputStream(new FileOutputStream("D:\\testIO\\13.jpg"));

            int i=0;
            while((i=bfis.read())!=-1){
                bfos.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bfis!=null) {
                    bfis.close();
                }
                if(bfos!=null){
                    bfos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 无需手动关闭流
     */
    @Test
    public void test02(){
        try(FileInputStream fileInputStream=new FileInputStream("D:\\IOtest\\12.jpg");
               FileOutputStream fileOutputStream=new FileOutputStream("D:\\testIO\\14.jpg")){
            byte[]bytes=new byte[1024];
            int len=0;
            while((len=fileInputStream.read(bytes))!=-1){
                 fileOutputStream.write(bytes,0,len);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 155
     */
    @Test
    public void test03(){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        long start = System.currentTimeMillis();
        try {
            fis=new FileInputStream("D:\\IOtest\\12.jpg");
            fos=new FileOutputStream("D:\\testIO\\15.jpg");
            int i=0;
            while((i=fis.read())!=-1){
                fos.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis!=null){
                  fis.close();
                }
                if(fos!=null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }

    /**
     * 5 1
     */
    @Test
    public void test04(){
        long start = System.currentTimeMillis();
        try(BufferedInputStream bufferedInputStream
                    =new BufferedInputStream(new FileInputStream("D:\\IOtest\\12.jpg"));
            BufferedOutputStream bufferedOutputStream=
                    new BufferedOutputStream(new FileOutputStream("D:\\testIO\\17.jpg"))
        ) {
            byte[]bytes=new byte[1024];
            int i=0;
            while((i=bufferedInputStream.read(bytes))!=-1){
                bufferedOutputStream.write(bytes,0,i);
            }
        }catch (Exception e){
            e.printStackTrace();;
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
