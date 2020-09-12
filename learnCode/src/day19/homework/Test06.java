package day19.homework;

import java.io.*;
/*
*@Description:serialVersionUID 如果没有显示赋值serialVersionUID的话 修改原对象但不重新写入的话 会报错 因为不显示赋值,每次改变对象的类都会改变serialVersionUID的值
* 如果显示赋值了就不会了
 *@author:zhijm
*@Date:2020/6/20 19:33
*/
public class Test06 {
    public static void main(String[] args) {
        String path="D:\\testoi\\message.dat";
       // objectWriteFile(path);
        objectReaderFile(path);
    }

    private static void objectWriteFile(String toPath){
        ObjectOutputStream objectOutputStream=null;
        try {
            Message message=new Message("李白",26);
            objectOutputStream=new ObjectOutputStream(new FileOutputStream(toPath));
            objectOutputStream.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objectOutputStream!=null)
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void objectReaderFile(String fromPath){
        ObjectInputStream inputStream=null;
        try {
            inputStream=new ObjectInputStream(new FileInputStream(fromPath));
            Message message= (Message) inputStream.readObject();
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream!=null)
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
class Message implements Serializable {
    private static final long serialVersionUID = 1742103478186448929L;
    private String name;
    private int age;

    public Message(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "message{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}