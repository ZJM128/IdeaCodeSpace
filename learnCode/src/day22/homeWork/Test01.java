package day22.homeWork;

/*
*@Description:创建和启动2个子线程，一个打印1-100之间奇数，一个打印1-100之间偶数，
（1）要求每个线程要么不打印，要么就连续打印5个数，每个数打印间隔500毫秒
（2）但两个线程不要求交替打印。
*@author:zhijm
*@Date:2020/6/22 21:43
*/
public class Test01 {
    public static void main(String[] args) {
        new PrintEven().start();
        new PrintOdd().start();

    }
}
class PrintEven extends Thread{
    private  int num = 1;
    public void run(){
        while(num<100){
            synchronized (Thread.class) {
                for (int i = 1; i <= 5; i++) {
                    num+=2;
                    if(num>100){
                        return;
                    }
                    System.out.println("奇数线程，第" + i + "个：" + num);

                    /*try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }

        }
    }
}
class PrintOdd extends Thread{
    private int num = 2;
    public void run(){
        while(num<100){
            synchronized (Thread.class) {

                for (int i = 1; i <= 5; i++) {
                    num+=2;
                    if(num>100){
                        return;
                    }
                    System.out.println("偶数线程，第" + i + "个：" + num);

                    /*try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }
    }
}