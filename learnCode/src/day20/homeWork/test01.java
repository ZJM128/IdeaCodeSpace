package day20.homeWork;

import org.junit.Test;

import java.io.*;

public class test01 {
    @Test
    public void test() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\IOtest\\mysql.txt"));
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("D:\\IOtest\\mysql.txt"));//  输出流创建的时候文件的内容会被清空
        String line=null;
        while((line=bufferedReader.readLine())!=null) {
            bufferedWriter.write(line);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
