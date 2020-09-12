package day24.morining;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test01 {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("day24.morining.Phone");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class, double.class);
            Object o = declaredConstructor.newInstance("华为p40", "华为", 4000);
            Method say = aClass.getDeclaredMethod("say");
            say.setAccessible(true);
            System.out.println(say.invoke(o));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
