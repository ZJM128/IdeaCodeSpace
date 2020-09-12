package day21.mornWork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/*
*@Description:文件加密
*@author:zhijm
*@Date:2020/6/20 9:32
*/
public class Test02 {
    public static void main(String[] args) {
        /**
         * 加密
         */
        /*try(
                BufferedInputStream bufferedInputStream=
                        new BufferedInputStream(new FileInputStream("D:\\IOtest\\12.jpg"));
                BufferedOutputStream bufferedOutputStream=
                        new BufferedOutputStream(new FileOutputStream("D:\\IOtest\\15.jpg"))
        ){
            //byte[]bytes=new byte[1024];
            int len=0;
            while ((len=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(len^5);
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

        // 解密
        try(
                BufferedInputStream bufferedInputStream=
                        new BufferedInputStream(new FileInputStream("D:\\IOtest\\15.jpg"));
                BufferedOutputStream bufferedOutputStream=
                        new BufferedOutputStream(new FileOutputStream("D:\\IOtest\\16.jpg"))
        ){
            //byte[]bytes=new byte[1024];
            int len=0;
            while ((len=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(len^5);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
