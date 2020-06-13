package day14.review;

public class TestException {
    public static void main(String[] args) {
        try {
            System.out.println("hello exception");
            int n1=Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("参数有误");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("hello exception");
        int n2=Integer.parseInt(args[1]);
    }
}
