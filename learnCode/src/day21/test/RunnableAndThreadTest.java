package day21.test;

public class RunnableAndThreadTest {
    public static void main(String[] args) {
        /*Thread thread=new Thread("hah"){
            @Test
            public void run(){
                System.out.println("还有这操作");
            }
        };
        thread.start();
        System.out.println(thread.getPriority());// 默认和父线程的优先级一样的*/

        Thread mainThread = Thread.currentThread();
        System.out.println("主线程的优先级"+mainThread.getPriority());
        MyRunnable01 runnable=new MyRunnable01();
        // 创建线程对象
        Thread thread=new Thread(runnable,"goodBoy");
        myThread02 myThread02=new myThread02("goodGirl");

        System.out.println("子线程1的优先级"+thread.getPriority());
        System.out.println("子线程2的优先级"+myThread02.getPriority());

        // 设置优先级
        thread.setPriority(8);
        myThread02.setPriority(2);
        thread.start();
        myThread02.start();


    }
}
class myThread02 extends Thread{
    public myThread02() {
    }

    public myThread02(String name) {
        super(name);
    }

    public void run(){
        for (int i = 0; i <100 ; i++) {
            System.out.println(this.getName()+":Hello Thread");
        }

    }
}
class  MyRunnable01 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":Hello Runnable");
        }

    }
}