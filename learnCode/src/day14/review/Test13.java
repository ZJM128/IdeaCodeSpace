package day14.review;

public class Test13 {
    public static void main(String[] args) {
        String str = "1.hello2.world3.java4.string";
        String[] split = str.split("\\.");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
