package day26.train;

/*
*@Description:例题：使用两个线程打印 1-100。线程1, 线程2 交替打印
*@author:zhijm
*@Date:2020/6/26 14:08
*/
public class Test01 {
    public static void main(String[] args) {
        PrintNum num=new PrintNum();
        new Thread(num,"李白").start();
        new Thread(num,"白居易").start();
    }

}
class PrintNum implements Runnable{

    private int num=1;
    @Override
    public void run() {
       while(num<=100){
            synchronized (this) {
                notifyAll();
                ;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+num);
                num++;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}