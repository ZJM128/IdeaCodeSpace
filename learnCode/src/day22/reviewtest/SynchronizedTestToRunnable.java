package day22.reviewtest;

public class SynchronizedTestToRunnable {
    public static void main(String[] args) {
        RunnableTest1 runnableTest1=new RunnableTest1();
        Thread thread=new Thread(runnableTest1,"窗口1");
        Thread thread1=new Thread(runnableTest1,"窗口2");
        Thread thread2=new Thread(runnableTest1,"窗口3");
        thread.start();
        thread1.start();
        thread2.start();
    }
}
class RunnableTest1 implements Runnable{
    private int count=10000;
    @Override
    public void run() {
        while(true){
            synchronized (this){
                if(count<=0){
                    return;
                }
                count--;
                System.out.println(ThreadTest.currentThread().getName()+"卖了1张票,剩余"+count);

            }
        }
    }
}