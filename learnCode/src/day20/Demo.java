package day20;

import org.junit.Test;

import java.io.*;

public class Demo {
    @Test
    public void test01() throws Exception {
        InputStream inputStream = new FileInputStream("D:\\IOtest\\99.txt");
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
      //  int read = bufferedInputStream.read();
       // System.out.println((char) read);
        byte[]bytes=new byte[2];
        bufferedInputStream.read(bytes);
        int len=0;
        while((len=bufferedInputStream.read(bytes))!=-1){
            for(int i=0;i<bytes.length;i++){
                System.out.println((char)bytes[i]);// 相当于输出去了
            }
        }
        bufferedInputStream.close();
    }
    @Test
    public void test02() throws Exception {
        BufferedOutputStream outputStream=
                new BufferedOutputStream(new FileOutputStream("D:\\IOtest\\6633.txt"));
        outputStream.write(96);
        outputStream.write(new byte[]{'1','a','6'});
        //outputStream.flush();
        outputStream.close();
    }
    @Test
    public void test03() throws Exception {
        BufferedReader reader=new BufferedReader(new FileReader("D:\\IOtest\\6633.txt"));

       // String string = reader.readLine();
        //System.out.println(string);
        int read = reader.read();
        System.out.println((char) read);
        char[]chars=new char[10];
        reader.read(chars);
        for (char aChar : chars) {
            System.out.println(aChar);
        }
        reader.close();
    }
    @Test
    public void test06() throws Exception {
        BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\IOtest\\test01.txt"));
        bw.write(97);
        bw.flush();
        bw.write("123456",0,3);
        bw.write("96");
        bw.write(new char[]{'g','h'},0,2);

        bw.close();
    }
}
