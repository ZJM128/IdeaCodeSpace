package day22.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        SellTest test=new SellTest();
        SellTest test1=new SellTest();
        SellTest test2=new SellTest();
        test.start();
        test1.start();
        test2.start();
    }
}
class SellTest extends Thread{
    static Lock lock=new ReentrantLock();// 每个对象都会有一个份锁 所以要static锁的同一把锁
    private static int count=1000;
    @Override
    public void run() {
      while(true){
          try {
              lock.lock();
              if (count<=0){
                  return;
              }
              count--;
              System.out.println(Thread.currentThread().getName()+"卖出1张票,剩余"+count);
          }finally{
              lock.unlock();
          }
      }

    }
}