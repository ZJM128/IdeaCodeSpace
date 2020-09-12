package day24.review;

public class Test01 {
    public static void main(String[] args) {
        MyRunnable myRunnable=new MyRunnable();
        Thread thread=new Thread(myRunnable,"子线程");
        thread.start();
        for (int i = 1; i < 100; i+=2) {
            System.out.println("主线程:"+i);
        }
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 2; i <= 100; i+=2) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}