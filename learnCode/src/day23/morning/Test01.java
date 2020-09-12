package day23.morning;
/*
*@Description:一、请按要求编写多线程应用程序，模拟多个人(多个线程)通过一个山洞：
​	1、这个山洞每次只能通过一个人，每个人通过山洞的时间为5秒；
​	2、随机生成10个人(10个线程)，同时准备过此山洞
​	3、定义一个变量用于记录通过隧道的人数
​	4、显示每次通过山洞人的姓名(线程的名字)，和通过顺序；
​		显示信息：XXX已经通过隧道，TA是第n个通过的！
*@author:zhijm
*@Date:2020/6/23 8:35
*/
public class Test01 {
    public static void main(String[] args) {
        MyRunnable1 myRunnable1=new MyRunnable1();
        new Thread(myRunnable1,"小一").start();
        new Thread(myRunnable1,"小二").start();
        new Thread(myRunnable1,"小三").start();
        new Thread(myRunnable1,"小四").start();
        new Thread(myRunnable1,"小五").start();
        new Thread(myRunnable1,"小六").start();
        new Thread(myRunnable1,"小七").start();
        new Thread(myRunnable1,"小八").start();
        new Thread(myRunnable1,"小九").start();
        new Thread(myRunnable1,"大师").start();

    }
}
class MyRunnable1 implements Runnable{

    private  int count=0;
    @Override
    public void run() {
        synchronized (this){

            count++;
            System.out.println(Thread.currentThread().getName()+"已经通过隧道"+"TA是第"+count+"个通过的！");
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}