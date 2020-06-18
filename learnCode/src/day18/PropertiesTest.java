package day18;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        /*Properties properties=new Properties();
        properties.load(new FileInputStream("D:/IdeaCodeSpace/learnCode/src/info.properties"));

        String userName = properties.getProperty("UserName");
        String password = properties.getProperty("Password");

        System.out.println(userName+" "+password);*/

        //1. 创建 Properties 的对象
        Properties props = new Properties();

        //2. 通过 load() 方法加载属性文件
        props.load(new FileInputStream("./learnCode/info.properties"));

        //3. 通过 getProperty() 方法根据 String类型key 获取 String类型的 value
        String username = props.getProperty("UserName");
        String password = props.getProperty("Password");

        System.out.println(username);
        System.out.println(password);

    }
}
