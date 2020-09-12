package day20;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputStreamReaderTest {
    @Test
    public void test01(){
        try {
           Reader reader = new InputStreamReader(new FileInputStream("D:\\testIO\\mysql11.txt"),"GBK");
           int i=0;
           while((i=reader.read())!=-1){
               System.out.print((char)i);
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
