package day22.test;

public class SynchronizedTest03 {
    public static void main(String[] args) {
        sell02 sell02=new sell02();
        Thread thread=new Thread(sell02);
        Thread thread1=new Thread(sell02);
        Thread thread2=new Thread(sell02);
        thread.start();
        thread1.start();
        thread2.start();
    }
}
class sell02 implements Runnable{
    private  int count=10000;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                    if (count <= 0) {
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + "买出1张票");
                    count--;
                }
        }
    }
}