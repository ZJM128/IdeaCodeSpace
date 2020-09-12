package day20;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Demo04 {
    @Test
    public void test01() throws IOException {

        Writer writer=new FileWriter("D:\\IOtest\\99.txt");
        writer.write(23);
        writer.write("oo");
        writer.write("9999",0,2);
        writer.write(new char[]{'a','b'});
        writer.write(new char[]{'q','c'},0,1);
        //writer.flush(); 写入大数据文件的时候可以使用
        writer.close(); //  close包含了flush()方法了,先刷新 在关闭
    }
}
