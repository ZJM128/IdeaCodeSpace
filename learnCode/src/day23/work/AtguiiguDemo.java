package day23.work;

import java.io.Serializable;

public class AtguiiguDemo implements Serializable,Comparable<AtguiiguDemo> {
    private static String school="尚硅谷";
    public String className;

    public AtguiiguDemo() {
    }

    private AtguiiguDemo(String className) {
        this.className = className;
    }

    private static String getSchool() {
        return school;
    }

    public static void setSchool(String school) {
        AtguiiguDemo.school = school;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public int compareTo(AtguiiguDemo o) {
        return this.className.compareTo(o.className);
    }
}
