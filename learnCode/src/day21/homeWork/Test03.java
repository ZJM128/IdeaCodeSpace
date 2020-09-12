package day21.homeWork;
/*
*@Description:在子线程中输出1-20之间的偶数，主线程输出1-20之间的奇数。
* 当子线程打印到10的时候，主线程进行插队，直到主线程执行完毕！
*@author:zhijm
*@Date:2020/6/21 18:56
*/
public class Test03 {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        myRunnable runnable=new myRunnable(mainThread);
        runnable.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 20; i += 3) {
            System.out.println("main:"+i);
        }

    }
}
class myRunnable extends  Thread{
    Thread thread;
    public myRunnable() {
    }
    public  myRunnable(Thread thread){
        this.thread=thread;
    }
    @Override
    public void run() {
        for(int i=2;i<=20;i+=2){
            System.out.println("子线程"+":"+i);
            if(i==10){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}