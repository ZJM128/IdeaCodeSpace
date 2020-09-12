package day22.test;

public class SynchronizedTest04 {
    public static void main(String[] args) {
        Sell04 sell04=new Sell04();
        Sell04 sell041=new Sell04();
        Sell04 sell042=new Sell04();
        sell04.start();
        sell041.start();
        sell042.start();
    }
}
class Sell04 extends Thread{
    private  static  int count1 = 100;
    @Override
    public void run() {
        while(true){
            boolean sell = sell();
            if(sell)
                return;
        }
    }
   public static synchronized boolean sell(){
        if(count1<=0){
            return true;
        }
       count1--;
       System.out.println(Thread.currentThread().getName()+"卖出1张票"+"剩余票数"+count1);
        return false;
   }
}