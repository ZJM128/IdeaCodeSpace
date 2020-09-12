package day22.test;

public class StopTest {
    public static void main(String[] args) {
        MyRunnable myRunnable=new MyRunnable();
        Thread thread=new Thread(myRunnable,"李白");
        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if(i%5==0){
                myRunnable.setFlag(true);
            }
        }
    }
}
class MyRunnable implements Runnable{
    private boolean flag=false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            if(flag)
                return;
            System.out.println(Thread.currentThread().getName()+":吃第"+(i+1)+"饭");
        }

    }
}