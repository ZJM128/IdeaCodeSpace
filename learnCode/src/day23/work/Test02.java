package day23.work;

import java.io.FileInputStream;
import java.util.Properties;

public class Test02 {
    public static void main(String[] args) {
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream("./learnCode/config.properties"));
            String apple = properties.getProperty("Apple");
            Class<?> aClass = Class.forName(apple);
            Fruit fruit = (Fruit) aClass.newInstance();
            new Juicer().run(fruit);
            //System.out.println(apple);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
