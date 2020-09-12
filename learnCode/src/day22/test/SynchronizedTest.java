package day22.test;

public class SynchronizedTest {
    public static void main(String[] args) {
        SellCard sellCard=new SellCard();
        SellCard sellCard1=new SellCard();
        SellCard sellCard2=new SellCard();
        sellCard.start();
        sellCard1.start();
        sellCard2.start();

    }
}
class SellCard extends Thread{

    private static int count=1;
    @Override
    public void run() {
       while (true) {
           synchronized (this.getClass()) {
               if (count >= 100) {
                   return;
               }
               System.out.println(Thread.currentThread().getName() + "卖出第" + count + "票");
               count++;
           }
       }
    }
}