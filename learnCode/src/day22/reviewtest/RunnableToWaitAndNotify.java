package day22.reviewtest;

public class RunnableToWaitAndNotify {
    public static void main(String[] args) {
        RunnableWait runnableWait=new RunnableWait();
        Thread thread=new Thread(runnableWait);
        thread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main"+i);
            if(i%5==0){
                synchronized (runnableWait){
                    runnableWait.notify();
                }
            }
        }

    }
}
class RunnableWait implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("子线程"+i);
            synchronized (this){
                if(i==5){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}