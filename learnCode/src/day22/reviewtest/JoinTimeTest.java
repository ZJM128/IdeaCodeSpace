package day22.reviewtest;

public class JoinTimeTest {
    public static void main(String[] args) {
        // 获取主线程
        Thread mainThread = Thread.currentThread();
        RunnableTest runnableTest=new RunnableTest();
        runnableTest.setThread(mainThread);
        Thread thread=new Thread(runnableTest,"子线程");
        thread.start();
        for (int j=2;j<100;j+=2){
            System.out.println("main"+j);
            if(j%10==0){
                runnableTest.setFlag(true);
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.isAlive());

    }
}

class RunnableTest implements Runnable{
    private  Thread thread;
    private boolean flag=false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {

        for(int i=1;i<100;i+=2){
            if(flag)
                return;
            System.out.println(Thread.currentThread().getName()+":"+i);
            if(i%5==0){
                // 执行主线程
                try {
                    thread.join(100);
                    //thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}