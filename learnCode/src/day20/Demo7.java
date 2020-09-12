package day20;

import org.junit.Test;

import java.io.*;

public class Demo7 {
    @Test
    public void test01() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\IOtest\\11.data"));
        objectOutputStream.writeUTF("加密文件");
        objectOutputStream.writeInt(63);
        objectOutputStream.writeObject(new User(12,"李白"));
        objectOutputStream.close();
    }
    @Test
    public void test02() throws Exception {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("D:\\IOtest\\11.data"));
        String string = objectInputStream.readUTF();
        System.out.println(string);
        int i = objectInputStream.readInt();
        System.out.println(i);
        Object o = objectInputStream.readObject();
        System.out.println(o);
    }
}
class  User implements Serializable {
    private static final long serialVersionUID = -5607126320404133873L;
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}