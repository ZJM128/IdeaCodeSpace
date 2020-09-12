package day22.test;

public class SynchronizedTest02 {
    public static void main(String[] args) {
        SellCard01 sellCard01=new SellCard01();
        Thread thread=new Thread(sellCard01);
        Thread thread1=new Thread(sellCard01);
        Thread thread2=new Thread(sellCard01);
        thread.start();
        thread1.start();
        thread2.start();
    }
}
class SellCard01 implements Runnable{

    private int  count=1;
    @Override
    public void run() {
        while (true) {
            boolean sell = sell();
            if (sell)
                return;
        }
    }
    public synchronized boolean sell(){
        if(count>=100){
            return true;
        }
        System.out.println(Thread.currentThread().getName()+"卖出第"+count+"张票");
        count++;
        return false;
    }
}