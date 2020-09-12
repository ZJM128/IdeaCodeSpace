package day23.threadReview;
/*
*@Description:创建和启动2个子线程，一个打印1-100之间奇数，一个打印1-100之间偶数，
（1）要求每个线程要么不打印，要么就连续打印5个数，每个数打印间隔500毫秒
（2）但两个线程不要求交替打印
*@author:zhijm
*@Date:2020/6/23 12:14
*/
public class Test01 {
    public static void main(String[] args) {
        EventRunnable eventRunnable = new EventRunnable();
        new Thread(eventRunnable).start();
        new Thread(eventRunnable).start();
        //new Thread(new OddRunnable()).start();
    }
}
class EventRunnable implements Runnable{
    private boolean even=true;
    private boolean odd=true;
    private int evenNum=2;
    private int oddNum=1;
    @Override
    public void run() {
        while(oddNum<100 || evenNum<100){
            synchronized ("A"){
                if(odd && oddNum<100) {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("奇数线程" + oddNum);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        oddNum += 2;
                    }
                    even=true;
                    odd=false;
                }else if(even){
                    for (int i = 0; i < 5; i++) {
                        System.out.println("偶数线程" + evenNum);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        evenNum += 2;
                        even=false;
                        odd=true;
                    }
                }
            }
        }
    }
}

class OddRunnable implements Runnable{
    @Override
    public void run() {
        int num=2;
        while(num<100){
            synchronized ("A"){
                for(int i=0;i<5;i++){
                    System.out.println("偶数线程"+num);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num+=2;
                }
            }
        }
    }
}