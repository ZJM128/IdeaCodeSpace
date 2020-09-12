package day21.test;

public class JoinAndSleep {
    public static void main(String[] args) {
        // 创建实现对象
        myRunnable03 myRunnable03=new myRunnable03();
        // 创建线程对象
        Thread thread=new Thread(myRunnable03,"李白");
        // 线程启动
        thread.start();
        for (int i=1;i<100;i+=2){

            if(i%5==0){
                try {
                    thread.join();// 插队等thread执行完了 main才执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main:"+i);
        }


    }
}
class myRunnable03 implements Runnable{

    @Override
    public void run() {
        for(int i=2;i<=100;i+=2){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}