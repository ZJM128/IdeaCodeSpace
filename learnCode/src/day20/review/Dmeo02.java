package day20.review;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class Dmeo02 {
    @Test
    public void test01() throws Exception {
        FileOutputStream fileOutputStream=new FileOutputStream("D:\\IOtest\\123.txt");
        fileOutputStream.write(98);
        fileOutputStream.write(99);
        fileOutputStream.close();
    }

    @Test
    public void test02() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\IOtest\\143.txt");
        fileOutputStream.write(102);
        byte[]bytes={97,98,99,100};

        fileOutputStream.write(bytes,0,2);
        fileOutputStream.close();// 没有关流 问题不大

    }
}
