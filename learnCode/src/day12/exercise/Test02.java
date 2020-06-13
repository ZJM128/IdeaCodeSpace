package day12.exercise;

public class Test02 {
    public static void print(){
       new Object(){
            public void test(){
                System.out.println("尚硅谷");
            }
        }.test();
    }

    public static void main(String[] args) {
       print();
    }
}
