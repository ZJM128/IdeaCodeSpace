package day22.homeWork;

/*
*@Description:创建和启动2个子线程，一个打印1-100之间奇数，一个打印1-100之间偶数，
（1）要求每个线程要么不打印，要么就连续打印5个数，每个数打印间隔500毫秒
（2）但要求两个线程交替打印
*@author:zhijm
*@Date:2020/6/22 22:21
*/
public class Test02 {

    public static void main(String[] args)  {
        new EvenThread().start();
        new oddThread().start();
    }
}

class EvenThread extends Thread {
  private int evenCount = 0;

    @Override
    public void run() {
        synchronized (Thread.class) {
            for (int i = 2; i <= 100; i += 2) {
                System.out.println("偶数线程:第" + (++evenCount) + "个" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (evenCount == 5) {
                    try {
                        evenCount = 0;
                        Thread.class.notify();
                        if(i<100){
                            Thread.class.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
class oddThread extends Thread{
    private int count=0;
    @Override
    public void run() {
        synchronized (Thread.class) {
            for (int i = 1; i <= 100; i += 2) {
                System.out.println("奇数线程:第" + (++count) + "个" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count == 5) {
                    count = 0;
                    try {
                        Thread.class.notify();
                        if(i<99) {
                            Thread.class.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}