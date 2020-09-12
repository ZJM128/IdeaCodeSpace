package day20.homeWork;

import org.junit.Test;

import java.io.*;

public class SteamToChange {
    @Test
    public void test01(){
        System.out.println(1+'a');
        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter=null;
        try {
            inputStreamReader=
                    new InputStreamReader(new FileInputStream("D:\\IOtest\\mysql2.txt"),"UTF-8");
            outputStreamWriter =
                    new OutputStreamWriter(new FileOutputStream("D:\\IOtest\\mysql13.txt"),"GBK");

            char[]chars=new char[1024];
            int len=0;
            while((len=inputStreamReader.read(chars))!=-1){
                outputStreamWriter.write(chars,0,len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStreamReader!=null)
                    inputStreamReader.close();
                if(outputStreamWriter!=null)
                    outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
