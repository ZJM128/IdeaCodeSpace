package day22.mroning;

public class Test01 {
    public static void main(String[] args) {
        // 匿名内部类 1
        new Thread("线程1"){
            @Override
            public void run(){
                for (int i=2;i<100;i+=2){
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        }.start();


        // 匿名内部类2
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i+=2){
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        },"线程2").start();
    }
}
