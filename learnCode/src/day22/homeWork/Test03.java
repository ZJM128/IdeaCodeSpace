package day22.homeWork;

/*
*@Description:启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15.
接着再由线程1打印16,17,18,19,20….以此类推, 直到打印到75。

*@author:zhijm
*@Date:2020/6/22 23:20
*/
public class Test03 {
    public static void main(String[] args) {
        MyThread myThread1  =new MyThread("----------线程一----------",1);
        MyThread myThread2  =new MyThread("----------线程二----------",2);
        MyThread myThread3  =new MyThread("----------线程三----------",3);

        myThread1.start();
        myThread2.start();
        myThread3.start();


    }
}
class  MyThread extends Thread{
    private  static int count=1;
    private int sid;// 通过计算的标志位
    private int cid;// 每条线程的标志位
    public MyThread(String name,int cid) {
        super(name);
        this.cid=cid;
    }

    @Override
    public void run() {
        while(count<=75) {
            // 此处可能有三条线程在等待着
          synchronized (Thread.class){
              // 一条线程进来了 ,判断通过计算的sid是否和该线程的cid一样,不一样就等待,一样就进行,然后唤醒下一条线程
              sid=count/5%3+1;
              if(sid==cid){
                  System.out.println(Thread.currentThread().getName());
                  for (int i=0;i<5;i++){
                      System.out.print(count+" ");
                      count++;
                  }
                  Thread.class.notifyAll();// 唤醒等待的线程
                  System.out.println();
              }else{
                  try {
                      Thread.class.wait();// 不满足条件直接等待
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }

        }
    }
}