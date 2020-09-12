package day22.test;

public class AccountTest {
    public static void main(String[] args) {
        Account account=new Account();
        Thread thread=new Thread(account,"用户1");
        Thread thread1=new Thread(account,"用户2");
        Thread thread2=new Thread(account,"用户3");
        thread.start();
        thread1.start();
        thread2.start();
    }
}
class Account implements Runnable{
    private int balance=100000;
    @Override
    public void run() {

        while (true) {
                synchronized (this) {
                    if (balance <= 0) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "取了100");
                    balance -= 100;
                    System.out.println("剩余" + balance);
            }
        }
    }
}
