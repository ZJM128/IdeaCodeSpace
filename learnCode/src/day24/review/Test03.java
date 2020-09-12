package day24.review;
/*
*@Description:案例：创建和启动2个子线程，一个打印奇数，一个打印偶数，
（1）要求实现交替打印。
（2）每个数打印间隔1秒
*@author:zhijm
*@Date:2020/6/24 7:54
*/
public class Test03 {
    public static void main(String[] args) {
        RunnablePrintNumber runnablePrintNumber=new RunnablePrintNumber();
        new Thread(runnablePrintNumber).start();
        new Thread(runnablePrintNumber).start();
    }
}
class RunnablePrintNumber implements Runnable{
   private int i=0;// 两条线程操作的是同一个资源
    @Override
    public void run() {
        while (i<100){
            synchronized (Thread.class){
                Thread.class.notify();
                System.out.println(Thread.currentThread().getName()+":"+(++i));
                try {
                    if(i<100) {
                        Thread.class.wait();// 等待唤醒 释放锁资源
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}