package day21.homeWork;


/*
*@Description:银行有一个账户，账户中有10000块钱。
有两个储户一起从该账户取钱，每次取100，每次取完打印账户余额
*@author:zhijm
*@Date:2020/6/21 19:58
*/
public class Test04 {
    public static void main(String[] args) throws InterruptedException {

        Uesr user2=new Uesr();
        Thread thread1=new Thread(user2,"用户1");
        Thread thread2=new Thread(user2,"用户2");

        thread1.start();
        thread2.start();

    }
}
class  Uesr implements Runnable{
    private   double money=10000;
    @Override
    public void run() {
        while(money>0){
            money -= 100;
            /**
             * 余额:9800.0
             * 余额:9700.0
             * 余额:9900.0
             */
            System.out.println("余额:" + money);// 这种情况是 打印的方法线程不安全的,也有可能被后来者的线程先打印
        }
    }
}