package day21.homeWork;
/*
*@Description:在子线程中输出1-20之间的偶数，主线程输出1-20之间的奇数。
 * 当子线程打印到10的时候，主线程进行插队，直到主线程执行完毕！
*@author:zhijm
*@Date:2020/6/21 19:53
*/
public class Test03_1 {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread thread=new Thread(){
            @Override
            public void run() {
                for (int i = 2; i < 20; i+=2) {
                    System.out.println("子线程:"+i);
                    if(i==10){
                        try {
                            mainThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 20; i+=2) {
            System.out.println("main:"+i);
        }
    }
}
