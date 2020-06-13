package day11.work;

public class Test04 extends Father04 {
    private String name = "test";

    public static void main(String[] args) {
        Test04 test = new Test04();
        System.out.println(test.getName());
    }
}
class Father04 {
    private String name = "father";

    public String getName() {
        return name;
    }
}