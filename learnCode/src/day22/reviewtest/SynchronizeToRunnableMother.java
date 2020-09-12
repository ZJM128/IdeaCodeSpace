package day22.reviewtest;

public class SynchronizeToRunnableMother {
    public static void main(String[] args) {
        RunnableMother runnableMother=new RunnableMother();
        Thread thread=new Thread(runnableMother);
        Thread thread1=new Thread(runnableMother);
        Thread thread2=new Thread(runnableMother);
        thread.start();
        thread1.start();
        thread2.start();
    }


}
class RunnableMother implements Runnable{

    private int count=1000;
    @Override
    public void run() {
        while(true){
            if(sell())
                return ;
        }


    }
    private synchronized boolean sell(){
        if(count<=0){
            return true;
        }else{
            count--;
            System.out.println(ThreadTest.currentThread().getName()+"卖出了1张票,剩余"+count);
             return false;
        }
    }
}