package day26.train;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    public static void main(String[] args) throws Exception {
        //propertiesTest();
        propertiesTest1();
    }
    private static void propertiesTest1() throws IOException {
        Properties properties=new Properties();
        InputStream resourceAsStream =
                ClassLoader.getSystemClassLoader().getResourceAsStream("info.properties");
        properties.load(resourceAsStream);
        System.out.println(properties.getProperty("Password"));
    }

    private static void propertiesTest() throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream("./learnCode/info.properties"));
        String password = properties.getProperty("Password");
        System.out.println(password);
        properties.setProperty("Password","456");
        System.out.println("修改成功");
    }
}
