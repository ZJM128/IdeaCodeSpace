package day22.reviewtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockToThread {
    public static void main(String[] args) {
        ThreadToLock threadToLock=new ThreadToLock("窗口1");
        ThreadToLock threadToLock2=new ThreadToLock("窗口2");
        ThreadToLock threadToLock3=new ThreadToLock("窗口3");
        threadToLock.start();
        threadToLock2.start();
        threadToLock3.start();
    }
}
class ThreadToLock extends Thread{
    private static int count=1000;// 静态变量 多个线程共享一个同数据
    private static Lock lock= new ReentrantLock();// 多个线程共享一把锁

    public ThreadToLock(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            try{
                lock.lock();
                if(count<=0){
                    return;
                }
                count--;
                System.out.println(Thread.currentThread().getName()+"卖出了1张票,剩余"+count);
            }finally {
                lock.unlock();
            }
        }

    }
}