package day24.review;

public class Test02 {


    public static void main(String[] args) {
        EvenPrint evenPrint=new EvenPrint();
        OddPrint oddPrint=new OddPrint();
        new Thread(evenPrint,"线程1").start();;
        new Thread(oddPrint,"线程2").start();;
    }
}
class  EvenPrint implements Runnable{
    @Override
    public void run() {
        int i=2;
        while (i<=100){
            synchronized (Thread.class){
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName()+" 第"+(j+1)+"个:"+i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i+=2;
                }
            }
        }
    }
}
class OddPrint implements Runnable{
    @Override
    public void run() {
        int i=1;
        while(i<100){
            synchronized (Thread.class){
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName()+" 第"+(j+1)+"个:"+i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i+=2;

                }
            }

        }

    }
}