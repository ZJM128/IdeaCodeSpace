package day21.test;

public class RunnableTest {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        System.out.println("主线程的优先级"+Thread.currentThread().getPriority());

        // 创建实现的对象
        myRunnable runnabl=new myRunnable();
        // 创建线程对象
        myThread01 thread01=new myThread01("大威天龙");// 需要子类重写有参的构造器,初始化父类的有参的构造器
        Thread thread02=new Thread(runnabl,"李四");
        System.out.println("子线程1的优先级"+thread01.getPriority());
        System.out.println("子线程2的优先级"+thread02.getPriority());
        // 启动线程
        thread01.start();
        thread02.start();
        System.out.println("run()方法刚开始了线程是否还活着:"+(thread02.isAlive()?"是":"否"));

        Thread.sleep(2000);
        System.out.println("run()方法结束了线程是否还活着:"+(thread02.isAlive()?"是":"否"));
    }
}
class myRunnable implements Runnable{

    public myRunnable() {
    }
    @Override
    public void run() {
        for(int i=2;i<100;i+=2){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class myThread01 extends Thread{
    public myThread01(String name) {
        super(name);
    }

    public myThread01() {
    }

    @Override
    public void run(){
        for (int i = 1; i < 100; i+=2) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}