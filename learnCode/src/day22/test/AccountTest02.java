package day22.test;

public class AccountTest02 {
    public static void main(String[] args) {
        Account01 account01=new Account01("用户1");
        Account01 account02=new Account01("用户2");
        Account01 account03=new Account01("用户3");
        account01.start();
        account02.start();
        account03.start();
    }
}
class Account01 extends Thread{
    private  static double balance=100000;
    public Account01(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
                synchronized (this.getClass()) {
                if (balance <= 0) {
                    break;
                }
                balance -= 100;
                System.out.println(Thread.currentThread().getName() + "取出了" + 100);
                System.out.println("余额为:" + balance);
            }
        }
    }
}