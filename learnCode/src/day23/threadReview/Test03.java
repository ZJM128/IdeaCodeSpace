package day23.threadReview;

/*
*@Description:启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15.
接着再由线程1打印16,17,18,19,20….以此类推, 直到打印到75。
*@author:zhijm
*@Date:2020/6/23 12:49
*/
public class Test03 {
    public static void main(String[] args) {
        PrintNumber printNumber=new PrintNumber(1);
        PrintNumber printNumber1=new PrintNumber(2);
        PrintNumber printNumber2=new PrintNumber(3);
        new Thread(printNumber,"线程一").start();
        new Thread(printNumber1,"线程二").start();
        new Thread(printNumber2,"线程三").start();

    }
}
class PrintNumber implements Runnable{
    private int cid;
    private int sid;
    private static int count=1;
    public PrintNumber(int sid) {
        this.sid = sid;

    }
    @Override
    public void run() {
        while (count<=75){
           synchronized ("A"){
               cid=count/5%3+1;
               if(cid==sid){
                   System.out.println(Thread.currentThread().getName());
                   for(int i=0;i<5;i++){
                       System.out.print(count+" ");
                       count++;
                   }
                   System.out.println();
                   "A".notifyAll();
               }else{
                   try {
                       "A".wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

           }
        }
    }
}