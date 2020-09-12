package day21.test;

public class ThreadTest01 {
    public static void main(String[] args) {
     /*  MyThread04 thread04=new MyThread04("小摩的");
       thread04.start();
       int priority=thread04.getPriority();
        System.out.println(priority);
        for (int i = 0; i < 100; i++) {
            if(i%2==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main"+i);
        }*/

        myRunnable02 myRunnable02=new myRunnable02();
        Thread thread=new Thread(myRunnable02);
        thread.start();

    }

}
class  MyThread04 extends Thread{

    public MyThread04() {
    }

    public MyThread04(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i+=2) {
            System.out.println(this.getName()+":hello word");
        }

    }
}
class myRunnable02 implements Runnable{

    @Override
    public void run() {
        int i=1;
        for (;i<=10;i++){
            System.out.println(i);
            try {
                if(i==10){
                    System.out.println("新年快乐");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}