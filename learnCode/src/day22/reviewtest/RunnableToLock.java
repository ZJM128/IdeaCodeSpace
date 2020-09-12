package day22.reviewtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableToLock {
    public static void main(String[] args) {
        LockToRunnable lockToRunnable=new LockToRunnable();
        Thread thread=new Thread(lockToRunnable);
        Thread thread1=new Thread(lockToRunnable);
        Thread thread2=new Thread(lockToRunnable);
        thread.start();
        thread1.start();
        thread2.start();

    }
}
class LockToRunnable implements Runnable{
    private int count=10000;
    private Lock lock=new ReentrantLock();
    @Override
    public void run() {
        while(true){
            try {
                lock.lock();
                if(count<=0){
                    return;
                }
                count--;
                System.out.println(Thread.currentThread().getName()+"卖出了1张票"+count);
            }finally {
                lock.unlock();
            }
        }
    }
}