package day11;

public class OrderTest {
    public static void main(String[] args) {
        Order order=new Order(11,"11");
        Order order1=new Order(11,"11");
        System.out.println(order);// 默认调用了toString()方法
        System.out.println(order1.toString());
        System.out.println(order.equals(order1));
        Object o=new Object();
    }
}
