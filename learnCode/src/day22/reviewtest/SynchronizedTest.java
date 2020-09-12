package day22.reviewtest;

public class SynchronizedTest {
    public static void main(String[] args) {
        ThreadTest threadTest=new ThreadTest("窗口1");
        ThreadTest threadTest1=new ThreadTest("窗口2");
        ThreadTest threadTest2=new ThreadTest("窗口3");
        threadTest.start();
        threadTest1.start();
        threadTest2.start();
    }
}

class ThreadTest extends Thread{
    public ThreadTest(String name) {
        super(name);
    }

    private static  int count=10000;
    @Override
    public void run() {
        while (true){
            synchronized (this.getClass()){
                if(count<=0){
                    return;
                }
                count--;
                System.out.println(Thread.currentThread().getName()+"买了1张票,剩余"+count);
            }
        }
    }
}