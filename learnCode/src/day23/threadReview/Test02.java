package day23.threadReview;
/*
*@Description:创建和启动2个子线程，一个打印1-10之间奇数，一个打印1-10之间偶数，
（1）要求每个线程要么不打印，要么就连续打印5个数，每个数打印间隔500毫秒
（2）但要求两个线程交替打印
*@author:zhijm
*@Date:2020/6/23 12:41
*/
public class Test02 {
    public static void main(String[] args) {
        new EvenThread().start();
        new OddThread().start();
    }
}
class EvenThread extends Thread{
    @Override
    public void run() {
        int i=1;
        while(i<100){
            synchronized (Thread.class) {
                for (int j = 0; j < 5; j++) {
                    System.out.println("奇数线程 第"+(j+1)+"个:" + i);
                    i += 2;
                }
                Thread.class.notify();
                if(i<100){
                    try {
                        Thread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
class OddThread extends Thread{
    @Override
    public void run() {
        int i=2;
        while(i<100){
            synchronized (Thread.class) {
                for (int j = 0; j < 5; j++) {
                    System.out.println("偶数线程 第"+(j+1)+"个:" + i);
                    i += 2;
                }
                Thread.class.notify();
                if(i<100){
                    try {
                        Thread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}