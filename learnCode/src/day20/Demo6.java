package day20;

import org.junit.Test;

import java.io.*;

public class Demo6 {
    /**
     * 乱码
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        FileInputStream inputStream=new FileInputStream("D:\\IOtest\\mysql12.txt");
        byte[]bytes=new byte[1024];
        int len0;
        while ((len0 = inputStream.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len0));
        }
    }

    /**
     * 文件是什么编码格式 就用什么格式读
     * @throws Exception
     */
   // @Test
    /*public void test02() throws Exception {
        InputStreamReader reader=new InputStreamReader(new FileInputStream("D:\\IOtest\\mysql1.txt"),"UTF-8");

        char[]chars=new char[1024];
        int len=0;
        while ((len=reader.read(chars))!=-1){
            System.out.println(new String(chars,0,len));
        }
    }*/
    @Test
    public void test03() throws Exception {
        OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("D:\\IOtest\\23.txt"),"GBK");
        writer.write("g嗯呢");
        writer.close();
    }


}
