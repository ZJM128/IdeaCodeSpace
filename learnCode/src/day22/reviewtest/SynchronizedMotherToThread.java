package day22.reviewtest;

public class SynchronizedMotherToThread {
    public static void main(String[] args) {
        ThreadTestMother threadTestMother=new ThreadTestMother();
        ThreadTestMother threadTestMother1=new ThreadTestMother();
        ThreadTestMother threadTestMother2=new ThreadTestMother();
        threadTestMother.start();
        threadTestMother1.start();
        threadTestMother2.start();
    }
}
class ThreadTestMother extends Thread{
    private static int count =1000;
    @Override
    public void run() {
        while(true){
            if(sell())
               return;
        }
    }

    public static synchronized boolean sell(){// 必须是静态的 因为不是静态的 锁对象不一样
        if(count<=0){
            return true;
        }else{
            count--;
            System.out.println(Thread.currentThread().getName()+"卖出了1张票,剩余"+count);
            return false;
        }
    }
}