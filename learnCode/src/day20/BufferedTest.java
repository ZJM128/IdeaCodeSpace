package day20;

import org.junit.Test;

import java.io.*;

public class BufferedTest {
    @Test
    public void test01() throws FileNotFoundException {
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        try{
            bufferedInputStream=new BufferedInputStream(new FileInputStream("D:\\IOtest\\mysql.txt"));
            bufferedOutputStream=new BufferedOutputStream(new FileOutputStream("D:\\testIO\\00.py"));
            int i=0;
            while((i=bufferedInputStream.read())!=-1){
                    bufferedOutputStream.write(i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(bufferedInputStream!=null){
                    bufferedInputStream.close();
                }
                if(bufferedOutputStream!=null){
                    bufferedOutputStream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test02(){
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try {
            bufferedReader=new BufferedReader(new FileReader("D:\\IOtest\\mysql.txt"));
            bufferedWriter=new BufferedWriter(new FileWriter("D:\\testIO\\88.go"));

            String len=null;
            while((len=bufferedReader.readLine())!=null){
                bufferedWriter.write(len);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(bufferedReader!=null){
                    bufferedReader.close();
                }

                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
