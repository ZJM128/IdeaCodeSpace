package day22.test;

public class InnerClassTest {
    public static void main(String[] args) {
        // 这个是继承Thread类 重写run方法
       new Thread(){
            public void run(){
                System.out.println("hello word");
            }
        }.start();


       //这个是实现Runnable接口 并实现run方法
       new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("good");
           }
       }).start();

       new Thread(()->{
           System.out.println("jdk8新特性");
       }).start();
    }

}
