package day19.homework;

import java.io.*;

public class Test05 {
    public static void main(String[] args) {
        //ObjectToWriteFile("message.dat"); 写入文件
        objectReaderFile("message.dat");

    }

    private static void ObjectToWriteFile(String toPath){
        ObjectOutputStream outputStream=null;

        try {
            outputStream=new ObjectOutputStream(new FileOutputStream(toPath));
            outputStream.writeInt(10);
            outputStream.writeChar('a');
            outputStream.writeDouble(2.5);
            outputStream.writeBoolean(true);
            outputStream.writeUTF("植俊铭");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream!=null)
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static  void objectReaderFile(String fromPath) {
        ObjectInputStream objectInputStream=null;
        try {
            objectInputStream=new ObjectInputStream(new FileInputStream(fromPath));
            System.out.println(objectInputStream.readInt());
            System.out.println(objectInputStream.readChar());
            System.out.println(objectInputStream.readDouble());
            System.out.println(objectInputStream.readBoolean());
            System.out.println(objectInputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objectInputStream!=null)
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
