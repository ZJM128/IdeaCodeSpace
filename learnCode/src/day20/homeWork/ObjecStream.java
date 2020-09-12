package day20.homeWork;

import org.junit.Test;

import java.io.*;
import java.util.Date;

public class ObjecStream {
    @Test
    public void test01() throws Exception {
        ObjectOutputStream objectOutputStream=
                new ObjectOutputStream(new FileOutputStream("D:\\testIO\\message.dat"));
        Message message=new Message("张三","李四","你吃了么",new Date());
        objectOutputStream.writeObject(message);
        objectOutputStream.writeUTF("加密文件");
        objectOutputStream.close();
    }
    @Test
    public void test02() throws Exception {
        ObjectInputStream objectInputStream=
                new ObjectInputStream(new FileInputStream("D:\\testIO\\message.dat"));
        Object o = objectInputStream.readObject();
        System.out.println(o);
        String string = objectInputStream.readUTF();
        System.out.println(string);
        objectInputStream.close();
    }
}
class Message implements Serializable{
    private String sender;
    private String recipient;
    private String content;
    private Date time;

    public Message(String sender, String recipient, String content, Date time) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}