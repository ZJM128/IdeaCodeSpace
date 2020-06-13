package day14;

import org.junit.Test;

public class Test01 {
    public static void main(String[] args) {
        Man man=new Man();
        System.out.println(man instanceof  Person);
    }
    @Test
    public void test(){
        Man man=new Man();
        System.out.println(man instanceof  Person);
    }
}
 class Person {

}
 class Man extends Person{

}