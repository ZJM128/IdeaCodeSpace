package day22.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {
    public static void main(String[] args) {
        SellTest2 sellTest2=new SellTest2();
        Thread thread=new Thread(sellTest2);
        Thread thread1=new Thread(sellTest2);
        Thread thread2=new Thread(sellTest2);
        thread.start();
        thread1.start();
        thread2.start();
    }
}
class SellTest2 implements Runnable{
   private Lock lock=new ReentrantLock(true);
   private int count=100;

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if(count<=0)
                    return;
                count--;
                System.out.println(Thread.currentThread().getName()+"卖出了1张票,剩余"+count);
            }finally {
                lock.unlock();
            }


        }
    }
}